package com.youzan.ad.handler;

import com.youzan.ad.dump.table.AdPlanTable;
import com.youzan.ad.index.DataTable;
import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adplan.AdPlanIndex;
import com.youzan.ad.index.adplan.AdPlanObject;
import com.youzan.ad.mysql.constant.OpType;

/**
 * Created by baimugudu on 2019/4/3
 */
public class AdLevelDataHandler {



    public static void handleLevel2(AdPlanTable adPlanTable,OpType type){
        AdPlanObject adPlanObject = new AdPlanObject(
                adPlanTable.getPlanId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate()
        );
        handleBinLongEvent(
                DataTable.of(AdPlanIndex.class),
                adPlanObject.getPlanId(),
                adPlanObject,
                type
        );


    }



    private static <K,V> void handleBinLongEvent(
            IndexAware<K,V> index,
            K key,
            V value,
            OpType type
    ){

        switch (type){
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }

    }
}
