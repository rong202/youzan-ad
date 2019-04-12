package com.youzan.ad.mysql.listenter;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.youzan.ad.mysql.TemplateHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by baimugudu on 2019/4/12
 */
@Component
public class AggregationListener implements BinaryLogClient.EventListener {

    private String dbName;
    private String tableName;


    @Autowired
    TemplateHolder templateHolder;

    @Override
    public void onEvent(Event event) {
        EventType type = event.getHeader().getEventType();

        if(type==EventType.TABLE_MAP){
            TableMapEventData data = event.getData();
            this.dbName =  data.getDatabase();
            this.tableName =  data.getTable();
            return ;
        }


    }
}
