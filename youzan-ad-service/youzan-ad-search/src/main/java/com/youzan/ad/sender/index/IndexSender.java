package com.youzan.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.dump.table.*;
import com.youzan.ad.handler.AdLevelDataHandler;
import com.youzan.ad.index.DataLevel;
import com.youzan.ad.mysql.constant.Constant;
import com.youzan.ad.mysql.dto.MySqlRowData;
import com.youzan.ad.sender.ISender;
import com.youzan.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by baimugudu on 2019/4/16
 */

@Component("indexSender")
@Slf4j
public class IndexSender implements ISender {


    @Autowired
    AdLevelDataHandler adLevelDataHandler;

    @Override
    public void sender(MySqlRowData rowData) {
        String level = rowData.getLevel();
        if (level.equals(DataLevel.LEVEL2.getLevel())) {
            level2RowData(rowData);
        }
        else if(level.equals(DataLevel.LEVEL3.getLevel())){
            level3RowData(rowData);
        }
      else  if(level.equals(DataLevel.LEVEL4.getLevel())){
            level4RowData(rowData);
        }else {
          log.info("MySqlRowData ERROE :{}",
                  JSON.toJSONString(rowData));
        }

    }

    private void level4RowData(MySqlRowData rowData){
        if(rowData.getTableName().equals(
                Constant.AD_UNIT_DISTRICT_TABLE_INFO.TABLE_NAME
        )){
            ArrayList<AdUnitDistrictTable> adUnitDistrictTables
                    = new ArrayList<>();
            for (Map<String, String> stringStringMap
                    : rowData.getFieldValueMap()) {
                AdUnitDistrictTable adUnitDistrictTable
                        = new AdUnitDistrictTable();

                stringStringMap.forEach((k,v)->{
                    switch (k){
                        case Constant.AD_UNIT_DISTRICT_TABLE_INFO
                                .COLUMN_UNIT_ID:
                            adUnitDistrictTable.setUnitId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_DISTRICT_TABLE_INFO
                                .COLUMN_CITY:
                            adUnitDistrictTable.setCity(v);
                            break;
                        case  Constant.AD_UNIT_DISTRICT_TABLE_INFO
                                .COLUMN_PROVINCE:
                            adUnitDistrictTable.setProvince(v);
                            break;
                    }

                });
                adUnitDistrictTables.add(adUnitDistrictTable);

            }

            adUnitDistrictTables.forEach(o->
                    AdLevelDataHandler.handleLevel4(o,rowData.getOpType()));
        }else if(
                rowData.getTableName().equals(Constant.AD_UNIT_IT_TABLE_INFO.TABLE_NAME)
        ){
            ArrayList<AdUnitItTable> adUnitItTables = new ArrayList<>();
            for (Map<String, String> stringStringMap : rowData.getFieldValueMap()) {
                AdUnitItTable adUnitItTable = new AdUnitItTable();
                stringStringMap.forEach((k,v)->{
                    switch (k){
                        case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_UNIT_ID:
                            adUnitItTable.setUnitId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_IT_TAG:
                            adUnitItTable.setItTag(v);
                            break;
                    }
                });

                adUnitItTables.add(adUnitItTable);
            }
            adUnitItTables.forEach(h->AdLevelDataHandler.handlLevel4(h,rowData.getOpType()));
        } else if (
                rowData.getTableName().equals(
                        Constant.AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME
                )
        ) {
            ArrayList<AdUnitKeywordTable> adUnitKeywordTables = new ArrayList<>();
            for (Map<String, String> stringStringMap : rowData.getFieldValueMap()) {

                AdUnitKeywordTable adUnitKeywordTable = new AdUnitKeywordTable();
                stringStringMap.forEach((k,v)->{
                    switch(k){
                        case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_UNIT_ID:
                            adUnitKeywordTable.setUnitId(Long.valueOf(v));
                            break;
                        case  Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_KEYWORD:
                            adUnitKeywordTable.setKeyword(v);
                            break;
                    }
                });
                adUnitKeywordTables.add(adUnitKeywordTable);
            }
            adUnitKeywordTables.forEach(d->AdLevelDataHandler.handllevel4(d,rowData.getOpType()));
        }
        }





    private void level3RowData(MySqlRowData rowData){
        if(rowData.getTableName().equals(
                Constant.AD_UNIT_TABLE_INFO.TABLE_NAME)){
            ArrayList<AdUnitTable> adUnitTables = new ArrayList<>();
            for (Map<String, String> stringStringMap
                    : rowData.getFieldValueMap()) {
                AdUnitTable adUnitTable = new AdUnitTable();
                stringStringMap.forEach((k,v)->{
                    switch (k){
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_ID:
                            adUnitTable.setUnitId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_PLAN_ID:
                            adUnitTable.setPlanId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_POSITION_TYPE:
                            adUnitTable.setPositionType(Integer.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_UNIT_STATUS:
                            adUnitTable.setUnitStatus(Integer.valueOf(v));
                            break;
                    }
                });
                adUnitTables.add(adUnitTable);
            }
            adUnitTables.forEach(i->
                    AdLevelDataHandler.handlelevel3(i,rowData.getOpType()));
        } else if (rowData.getTableName().equals(
                Constant.AD_CREATIVE_UNIT_TABLE_INFO.TABLE_NAME)) {

            ArrayList<AdCreativeUnitTbale> adCreativeUnitTbales = new ArrayList<>();
            for (Map<String, String> stringStringMap
                    : rowData.getFieldValueMap()) {

                AdCreativeUnitTbale adCreativeUnitTbale = new AdCreativeUnitTbale();
                stringStringMap.forEach((k,v)->{
                    switch (k){
                        case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_UNIT_ID:
                            adCreativeUnitTbale.setUnitId(Long.valueOf(v));
                            break;
                        case  Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_CREATIVE_ID:
                            adCreativeUnitTbale.setAdId(Long.valueOf(v));
                            break;
                    }
                });

                adCreativeUnitTbales.add(adCreativeUnitTbale);
            }

            adCreativeUnitTbales.forEach(j->AdLevelDataHandler.handlevel3(j,rowData.getOpType()));


        }
    }




    private void level2RowData(MySqlRowData rowData) {
        if (rowData.getTableName().equals(
                Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
            ArrayList<AdPlanTable> adPlanTables = new ArrayList<>();

            for (Map<String, String> field : rowData.getFieldValueMap()) {
                AdPlanTable adPlanTable = new AdPlanTable();
                field.forEach((K, V) -> {
                            if (K.equals(Constant.AD_PLAN_TABLE_INFO.COLUMN_ID)) {
                                adPlanTable.setPlanId(Long.valueOf(V));
                            }

                            if (K.equals(Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID)) {
                                adPlanTable.setUserId(Long.valueOf(V));
                            }

                            if (K.equals(Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS)) {
                                adPlanTable.setPlanStatus(Integer.valueOf(V));
                            }

                            if (K.equals(Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE)) {
                                adPlanTable.setEndDate(CommonUtils.parseStringDate(V));
                            }

                         // AdLevelDataHandler.handleLevel2(adPlanTable,rowData.getOpType());

                        }

                );

                adPlanTables.add(adPlanTable);
            }

            adPlanTables.forEach(p->AdLevelDataHandler.handleLevel2(p,rowData.getOpType()));

        }else if(rowData.getTableName().equals(Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME)){
            ArrayList<AdCreativeTbale> adCreativeTbales = new ArrayList<>();
            for (Map<String, String> stringStringMap : rowData.getFieldValueMap()) {
                AdCreativeTbale adCreativeTbale = new AdCreativeTbale();
                stringStringMap.forEach((k,v)->{
                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID)){
                        adCreativeTbale.setAdId(Long.valueOf(v));
                    }

                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_AUDIT_STATUS)){
                        adCreativeTbale.setAuditStatus(Integer.valueOf(v));
                    }
                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT)){
                        adCreativeTbale.setHeight(Integer.valueOf(v));

                    }
                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE)){
                        adCreativeTbale.setMaterialType(Integer.valueOf(v));
                    }
                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE)){
                        adCreativeTbale.setType(Integer.valueOf(v));
                    }
                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_URL)){
                       // adCreativeTbale.set
                    }

                    if(k.equals(Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH)){
                        adCreativeTbale.setWidth(Integer.valueOf(v));
                    }
                });

                adCreativeTbales.add(adCreativeTbale);

            }
            adCreativeTbales.forEach(u->AdLevelDataHandler.handlevel2(u,rowData.getOpType()));

        }

    }

}
