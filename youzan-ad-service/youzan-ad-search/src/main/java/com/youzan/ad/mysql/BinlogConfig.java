package com.youzan.ad.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by baimugudu on 2019/4/16
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix="adconf.mysql")
public class BinlogConfig {


    private int port;


    private String host;


    private String username;


    private String password;
}
