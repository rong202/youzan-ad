package com.youzan.ad.index.creativeunit;

import com.youzan.ad.index.IndexAware;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by baimugudu on 2019/4/2
 * String:adId-unitId
 */

public class CreativeUnitIndex implements IndexAware<String,CreativeUnitObject> {

    private static Map<String,CreativeUnitObject> objectMap;

   // <adId,unit set>
    private static Map<Long, Set<Long>> creativeUnitMap;

    // <unitId,adId set>
    private static Map<Long, Set<Long>> unitCraetiveMap;

    static{
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCraetiveMap = new ConcurrentHashMap<>();
    }



    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        objectMap.put(key,value);
        Set unitSet =  creativeUnitMap.get(value.getAdId());

        if(CollectionUtils.isEmpty(unitSet)){
            unitSet = new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getUnitId(),unitSet);
        }

        unitSet.add(value.getUnitId());

        Set adIdSet = unitCraetiveMap.get(value.getUnitId());
        if(CollectionUtils.isEmpty(adIdSet)){
            adIdSet = new ConcurrentSkipListSet<>();
            unitCraetiveMap.put(value.getAdId(),adIdSet);
        }

        adIdSet.add(value.getAdId());



    }

    @Override
    public void update(String key, CreativeUnitObject value) {

    }

    @Override
    public void delete(String key, CreativeUnitObject value) {

    }
}
