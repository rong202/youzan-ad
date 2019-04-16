package com.youzan.ad.mysql.listenter;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.youzan.ad.mysql.constant.Constant;
import com.youzan.ad.mysql.constant.OpType;
import com.youzan.ad.mysql.dto.BinLogRowData;
import com.youzan.ad.mysql.dto.MySqlRowData;
import com.youzan.ad.mysql.dto.TableTemplate;
import com.youzan.ad.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baimugudu on 2019/4/16
 * @author
 */
@Component
public class IncrementListener implements Ilistener {

    @Resource(name="")
    ISender sender;


    @Autowired
    AggregationListener aggregationListener;


    @Override
    @PostConstruct
    public void register() {

        Constant.table2db.forEach(
                (k,v)->
                        aggregationListener.register(v,k,this)
        );
    }

    @Override
    public void onEvent(BinLogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();
        //组装要投递得数据
        MySqlRowData rowData = new MySqlRowData();
        rowData.setTableName(table.getTableName());
        rowData.setLevel(eventData.getTable().getLevel());
        rowData.setOpType(OpType.to(eventType));
        //取出模板中操作对应得字段
        List<String> fieldList = table.getOpTypeFieldMap()
                .get(OpType.to(eventType));
        if(null==fieldList){
            return ;
        }
        for(Map<String,String> afterMap :eventData.getAfter()){

            Map<String,String>  _afterMap = new HashMap<>();
            for(Map.Entry<String,String> entry:afterMap.entrySet()){
               //colName
                String key = entry.getKey();
                //colValue
                String value = entry.getValue();
                _afterMap.put(key,value);
            }

            rowData.getFieldValueMap().add(_afterMap);


        }

        sender.sender(rowData);
    }
}
