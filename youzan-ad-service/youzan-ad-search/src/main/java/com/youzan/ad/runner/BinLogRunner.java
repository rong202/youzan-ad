package com.youzan.ad.runner;

import com.youzan.ad.mysql.BinlogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by baimugudu on 2019/4/16
 */
@Slf4j
@Component
public class BinLogRunner implements CommandLineRunner {

    @Autowired
    BinlogClient client;

    @Override
    public void run(String... args) throws Exception {
        log.info("run-----------------------------");
        client.connect();
    }
}
