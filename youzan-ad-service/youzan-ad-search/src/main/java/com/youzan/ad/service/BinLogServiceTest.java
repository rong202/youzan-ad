package com.youzan.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by baimugudu on 2019/4/11
 */


/**
 * 10:57:11.247 [main] INFO com.youzan.ad.service.BinLogServiceTest - insert--------------------------
 * 10:57:11.259 [main] INFO com.youzan.ad.service.BinLogServiceTest - WriteRowsEventData{tableId=438, includedColumns={0, 1, 2, 3, 4, 5, 6, 7},
 * rows=[
 *     [1, 123, 宝马计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]
 * ]}
 * 10:57:11.262 [main] INFO com.youzan.ad.service.BinLogServiceTest - update--------------------
 * 10:57:11.262 [main] INFO com.youzan.ad.service.BinLogServiceTest - UpdateRowsEventData{tableId=438, includedColumnsBeforeUpdate={0, 1, 2, 3, 4, 5, 6, 7}, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     {before=[1, 123, 宝马计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00
 *     CST 1970],
 *     after=[1, 123, 宝贝计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:0
 *     0:00 CST 1970]}
 * ]}
 * 10:57:11.263 [main] INFO com.youzan.ad.service.BinLogServiceTest - update--------------------
 * 10:57:11.263 [main] INFO com.youzan.ad.service.BinLogServiceTest - UpdateRowsEventData{tableId=438, includedColumnsBeforeUpdate={0, 1, 2, 3, 4, 5, 6, 7}, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     {before=[1, 123, 宝贝计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970], after=[1, 123, 宝马计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]}
 * ]}
 * 10:57:11.263 [main] INFO com.youzan.ad.service.BinLogServiceTest - update--------------------
 * 10:57:11.264 [main] INFO com.youzan.ad.service.BinLogServiceTest - UpdateRowsEventData{tableId=438, includedColumnsBeforeUpdate={0, 1, 2, 3, 4, 5, 6, 7}, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     {before=[1, 123, 宝马计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970], after=[1, 123, 奥迪计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]}
 * ]}
 * 10:57:11.264 [main] INFO com.youzan.ad.service.BinLogServiceTest - insert--------------------------
 * 10:57:11.264 [main] INFO com.youzan.ad.service.BinLogServiceTest - WriteRowsEventData{tableId=438, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     [2, 234, 5G时代, 1, Mon Mar 11 21:40:53 CST 2019, Thu Jul 11 21:41:02 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]
 * ]}
 * 10:57:11.265 [main] INFO com.youzan.ad.service.BinLogServiceTest - insert--------------------------
 * 10:57:11.266 [main] INFO com.youzan.ad.service.BinLogServiceTest - WriteRowsEventData{tableId=438, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     [3, 345, 五一劳动节, 0, Tue Apr 16 21:42:32 CST 2019, Thu Apr 11 21:42:36 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]
 * ]}
 * 10:57:11.266 [main] INFO com.youzan.ad.service.BinLogServiceTest - delete-------------------
 * 10:57:11.266 [main] INFO com.youzan.ad.service.BinLogServiceTest - DeleteRowsEventData{tableId=438, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
 *     [1, 123, 奥迪计划, 0, Tue Apr 02 19:42:36 CST 2019, Thu Apr 11 19:42:41 CST 2019, Thu Jan 01 08:00:00 CST 1970, Thu Jan 01 08:00:00 CST 1970]
 * ]}
 */

@Slf4j
public class BinLogServiceTest {
    public static void main(String[] args) throws IOException {
        BinaryLogClient client =  new BinaryLogClient(
                "127.0.0.1",
                3306,
                "root",
                "root"
        );
       // client.setBinlogFilename("mysql-bin.000006");

        client.registerEventListener(
                event ->
                {
                    final EventData data = event.getData();

                    if(data instanceof UpdateRowsEventData){
                        log.info("update--------------------");
                        log.info(data.toString());

                    }

                    if(data instanceof WriteRowsEventData){
                        log.info("insert--------------------------");
                        log.info(data.toString());
                    }

                    if(data instanceof DeleteRowsEventData){
                        log.info("delete-------------------");
                        log.info(data.toString());
                    }
                }
        );

        client.connect();

    }
}
