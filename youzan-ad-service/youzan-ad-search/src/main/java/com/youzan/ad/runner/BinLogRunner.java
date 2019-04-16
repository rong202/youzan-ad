package com.youzan.ad.runner;

import com.youzan.ad.mysql.BinlogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by baimugudu on 2019/4/16
 */
public class BinLogRunner implements CommandLineRunner {

    @Autowired
    BinlogClient client;

    @Override
    public void run(String... args) throws Exception {
        client.connect();
    }
}
