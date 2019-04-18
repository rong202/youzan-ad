package com.youzan.ad.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.youzan.ad.mysql.listenter.AggregationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by baimugudu on 2019/4/16
 */
@Component
public class BinlogClient {

    @Autowired
    BinlogConfig config;
    @Autowired
    AggregationListener listener;

    private  BinaryLogClient client;


    public void connect(){
       // new Thread(this).start();
        new Thread(
                ()-> {
                    client =  new BinaryLogClient(
                            config.getHost(),
                            config.getPort(),
                            config.getUsername(),
                            config.getPassword()
                    );

                    client.registerEventListener(
                            listener
                    );

                    try {
                        client.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }


/*    public void run() {
        BinaryLogClient client =  new BinaryLogClient(
                config.getHost(),
                config.getPort(),
                config.getUsername(),
                config.getPassword()
        );

        client.registerEventListener(
                listener
        );

        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
