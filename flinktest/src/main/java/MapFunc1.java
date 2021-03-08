import com.alibaba.fastjson.JSON;
import com.yupaopao.bigdata.common.rec.gd.bean.tablestore.TableStoreBean;
import com.yupaopao.bigdata.common.rec.gd.bean.tablestore.TableStoreValueBean;
import com.yupaopao.bigdata.common.rec.gd.constant.TablestoreConstant;
import com.yupaopao.bigdata.common.rec.gd.enums.TableStoreDataTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.LinkedHashMap;
import java.util.List;

public class MapFunc1 implements MapFunction<String, MessBean> {

    @Override
    public MessBean map(String mess) {
        TableStoreBean tableStoreBean = JSON.parseObject(mess, TableStoreBean.class);
        if (null != tableStoreBean && TablestoreConstant.USER_IM.equals(tableStoreBean.getTableName())) {
            LinkedHashMap<String, String> pkMap = tableStoreBean.getPkMap();
            String fromUid = pkMap.get(TablestoreConstant.USER_IM_KEY_FROM_ID);
            String toUid = pkMap.get(TablestoreConstant.USER_IM_KEY_TO_ID);
            if (fromUid == null || toUid == null) {
                return null;
            }
            List<TableStoreValueBean> data = tableStoreBean.getData();
            if (CollectionUtils.isEmpty(data)) {
                return null;
            }

            for (TableStoreValueBean tableStoreValueBean : data) {
                if ("update_time".equals(tableStoreValueBean.getName())) {
                    if (TableStoreDataTypeEnum.INTEGER.getType().equals(tableStoreValueBean.getType())) {
                        Long time = Long.parseLong(tableStoreValueBean.getValue().toString());
                        MessBean chatKafkaMess = new MessBean(fromUid, toUid, time);
                        return chatKafkaMess;
                    }
                }
            }
        }
        return null;
    }
}