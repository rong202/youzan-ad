package com.youzan.ad.sender.index;

import com.youzan.ad.dump.table.AdPlanTable;
import com.youzan.ad.handler.AdLevelDataHandler;
import com.youzan.ad.index.DataLevel;
import com.youzan.ad.mysql.constant.Constant;
import com.youzan.ad.mysql.dto.MySqlRowData;
import com.youzan.ad.sender.ISender;
import com.youzan.ad.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by baimugudu on 2019/4/16
 */

@Component("indexSender")
public class IndexSender implements ISender {


    @Autowired
    AdLevelDataHandler adLevelDataHandler;

    @Override
    public void sender(MySqlRowData rowData) {
        String level = rowData.getLevel();
        if (level.equals(DataLevel.LEVEL2.getLevel())) {
            level2RowData(rowData);
        }
    }

    private void level2RowData(MySqlRowData rowData) {
        if (rowData.getTableName().equals(
                Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
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

                        }

                );


            }
        }


    }

}
