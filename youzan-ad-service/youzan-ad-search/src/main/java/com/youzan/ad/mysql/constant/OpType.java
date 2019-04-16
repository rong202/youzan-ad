package com.youzan.ad.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * Created by baimugudu on 2019/4/3
 */
public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    /**
     * 将binlog中得事件类型转化成自定义得时间类型
     * PS:根据开发者mysql得版本取值
     * @param type
     * @return
     */
    public  static  OpType to(EventType type){
        switch (type){
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;
                default:
                    return OTHER;

        }
    }


}
