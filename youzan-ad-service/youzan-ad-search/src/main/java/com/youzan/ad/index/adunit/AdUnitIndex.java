package com.youzan.ad.index.adunit;

import com.youzan.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by baimugudu on 2019/4/2
 * 推广单元：正向索引
 */

@Component
@Slf4j
public class AdUnitIndex implements IndexAware<Long,AdUnitObject> {

    private static Map<Long,AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }


//    public List<AdUnitObject> fetch(Collection<Long> unitids){
//
//        if(CollectionUtils.isEmpty(unitids))
//        {
//            return new ArrayList<>();
//        }
//
//        ArrayList<AdUnitObject> objects = new ArrayList<>();
//
//
//        unitids.forEach(
//                i->{
//                    AdUnitObject adUnitObject = objectMap.get(i);
//                    objects.add(adUnitObject);
//                }
//        );
//
//        return objects;
//
//    }
//






    public Set<Long> match(Integer positionType){
        log.info("AdUnitIndex positionType-{}",positionType);
        log.info("AdUnitIndex objectMap-{}",objectMap);
        HashSet<Long> adUnitIdSet = new HashSet<>();

        objectMap.forEach((k,v)->{
            if(AdUnitObject.isAdSlotTypeOK(positionType,v.getPositionType())){
                log.info("----------------------------");
                adUnitIdSet.add(k);
            }
        });
         return adUnitIdSet;
    }



    public List<AdUnitObject>  fetch(Collection<Long> adUnitIds){
        if(CollectionUtils.isEmpty(adUnitIds)){
            return null;
        }
        ArrayList<AdUnitObject> adUnitObjects = new ArrayList<>();
        adUnitIds.forEach(
                u->{
                    AdUnitObject adUnitObject = get(u);
                    if(null==adUnitObject){
                        return ;
                    }

                    adUnitObjects.add(adUnitObject);
                }
        );
        return adUnitObjects;

    }




    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        objectMap.put(key,value);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        AdUnitObject adUnitObject =  objectMap.get(key);
        if(null==adUnitObject){
            objectMap.put(key,value);
        }else{
            adUnitObject.update(value);
        }


    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        objectMap.remove(key);
    }
}
