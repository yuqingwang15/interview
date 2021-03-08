import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

public class TestChatJob {

    //process time 不够准确。join的时间不一致
    //需要是eventtime或者injest time
    //这样join的时间一致

    //或者指定消息中的time为eventtime，据此设置window
    private static Time intervalWindowSize = Time.seconds(10);

    public static void main(String[] args) throws Exception {

        //kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic test
        //kafka-console-producer --broker-list localhost:9092 --topic test
   
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "testw");
//
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
//
        final FlinkKafkaConsumer<String> chatConsumer = new
                FlinkKafkaConsumer<>("test", new SimpleStringSchema(), props);

        chatConsumer.setCommitOffsetsOnCheckpoints(false);//不忽略自动提交
        DataStream<String> chatStream = env.addSource(chatConsumer)
                .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<String>() {
                             @Override
                              public long extractTimestamp(String s) {
                                                               return 0;
                                                           }
                                                       })
                ;

        DataStream<String> chatBeanStreamCopy = chatStream;
//        chatStream.print();
//        System.out.println();
//        chatBeanStreamCopy.print();

        DataStream<Tuple2<String, Integer>> counts =
                // split up the lines in pairs (2-tuples) containing: (word,1)
                chatStream.flatMap(new Tokenizer())
                        // group by the tuple field "0" and sum up tuple field "1"
                        .keyBy(0).window(TumblingProcessingTimeWindows.of(intervalWindowSize))
                        .reduce(new TestFunc1());

        DataStream<Tuple2<String, Integer>> counts2 =
                // split up the lines in pairs (2-tuples) containing: (word,1)
                chatBeanStreamCopy.flatMap(new Tokenizer())
                        // group by the tuple field "0" and sum up tuple field "1"
                        .keyBy(0).window(TumblingProcessingTimeWindows.of(intervalWindowSize))
                        .reduce(new TestFunc2());
//
//        counts.print();
//        counts2.print();

//        chatBeanStream.print();

        DataStream<JSONObject> res = counts.join(counts2).where(r -> r.f0).equalTo(r -> r.f0).window(TumblingProcessingTimeWindows.of(intervalWindowSize))
                .apply(new JoinFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, JSONObject>() {
                    @Override
                    public JSONObject join(Tuple2<String, Integer> time1, Tuple2<String, Integer> num1) throws Exception {
                        JSONObject jobRes = new JSONObject();
                        jobRes.put("word", time1.f0);
                        jobRes.put("time", time1.f1);
                        jobRes.put("num", num1.f1);
                        jobRes.put("now",System.currentTimeMillis()/1000);
                        return jobRes;
                    }
                });

        res.print().setParallelism(1);

        env.execute();

    }

    public static final class Tokenizer
            implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // normalize and split the line
            String[] tokens = value.toLowerCase().split("\\W+");

            // emit the pairs
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }
}
