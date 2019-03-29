package com.youzan.ad.service.impl;

import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUnitRepository;
import com.youzan.ad.dao.unit_condition.AdUnitItRepository;
import com.youzan.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.entity.unit_condition.AdUnitIt;
import com.youzan.ad.entity.unit_condition.AdUnitKeyword;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdUnitService;
import com.youzan.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by baimugudu on 2019/3/29
 */

@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository planRepository;


    @Autowired
    private AdUnitRepository unitRepository;


    @Autowired
    private AdUnitKeywordRepository         unitKeywordRepository;

    @Autowired
    AdUnitItRepository unitItRepository;



    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest unitRequest)
            throws  AdException{
        if(!unitRequest.createvalidate()){
            throw  new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }
         Optional<AdPlan> adplan = planRepository.findById(unitRequest.getPlanId());
        if(!adplan.isPresent()){
            throw  new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        AdUnit unit = unitRepository.findByPlanIdAndUnitName(unitRequest.getPlanId(),
                unitRequest.getUnitName());

        if(null!=unit){
            throw  new AdException(Constants.ErrorMsg.THE_UNIT_ALREADY_EXISTS);
        }

        AdUnit newUnit =  unitRepository.save(
                new AdUnit(
                        unitRequest.getPlanId(),
                        unitRequest.getUnitName(),
                        unitRequest.getPositionType(),
                        unitRequest.getBudget()
                )
        );


        return new AdUnitResponse(
                newUnit.getPlanId(),
                newUnit.getUnitName()
        );
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyWord(AdUnitKeyWordRequest unitKeyWordRequest) {
      //  List<Long> unitIds = Collections.emptyList();
       /* final List<AdUnitKeyWordRequest.UnitKeyWords> unitKeyWord = unitKeyWordRequest.getUnitKeyWord();
        for(int i=0;i<unitKeyWord.size();i++){
           Long unitId =  unitKeyWord.get(i).getUnitId();
            unitIds.add(unitId);
        }*/
        //JDK1.8

        List<Long> unitIds =  unitKeyWordRequest.getUnitKeyWord().stream().
                map(AdUnitKeyWordRequest.UnitKeyWords::getUnitId).
                collect(Collectors.toList());

        //然后拿着 unitIds遍历，AdUnit   只要有一个不存在，则抛异常  参数不合法

        List<AdUnitKeyword> unitKeywordList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();


        if(!CollectionUtils.isEmpty(unitKeyWordRequest.getUnitKeyWord())){
            //JDK1.8
            unitKeyWordRequest.getUnitKeyWord().forEach(
                    i -> unitKeywordList.add(
                            new AdUnitKeyword(i.getUnitId(),i.getKeyword())
                    )
            );
            //JDK1.8
            ids = unitKeywordRepository.saveAll(unitKeywordList).stream().map(
                    AdUnitKeyword::getId
            ).collect(Collectors.toList());

        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest unitItRequest) {

       List<Long> unitIds =  unitItRequest.getUnitIts().stream().map(
                AdUnitItRequest.UnitId::getUnitId
        ).collect(Collectors.toList());

        //然后拿着 unitIds遍历，AdUnit   只要有一个不存在，则抛异常  参数不合法

        List<AdUnitIt> list = Collections.emptyList();
        List<Long> ids = Collections.emptyList();
        if(!CollectionUtils.isEmpty(unitItRequest.getUnitIts())){
            unitItRequest.getUnitIts().forEach(
                    i ->list.add(
                            new AdUnitIt(i.getUnitId(),i.getItTag())
                    )
            );
            ids = unitItRepository.saveAll(list).stream().map(
                    AdUnitIt::getId
            ).collect(Collectors.toList());
        }
        return new AdUnitItResponse(ids);
    }
}
