import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;

@Slf4j
public class TestFunc2 implements ReduceFunction<Tuple2<String, Integer>> {
//    @Override
//    public Tuple2<Tuple2, Long> reduce(Tuple2<Tuple2, Long> o, Tuple2<Tuple2, Long> o2) throws Exception {
//
//        if(o.f0.f0.equals(o2.f0.f0) && o.f0.f1.equals(o2.f0.f1)){
//            return new Tuple2<>(o.f0,o.f1+o2.f1);
//        }else{
//            //error
//            Cat.logError("ChatMessReduceFunc_NotSameKdy",
//                    new Exception(o.toString().concat(o2.toString())));
//            return null;
//        }
//    }

    @Override
    public Tuple2<String,Integer> reduce(Tuple2<String,Integer> o, Tuple2<String,Integer> o2) {

        if(o.f0.equals(o2.f0)){
            return new Tuple2<String,Integer>(o.f0,o.f1-o2.f1);
        }else{

            return null;
        }
    }
}