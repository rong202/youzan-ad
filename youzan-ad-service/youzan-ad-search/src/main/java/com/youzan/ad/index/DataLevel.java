package com.youzan.ad.index;

import lombok.Getter;

/**
 * Created by baimugudu on 2019/4/16
 */
@Getter
public enum DataLevel {

    LEVEL2("2","level 2"),

    LEVEL3("3","level 3"),
    LEVEL4("4","level 4");


    private String level;
    private String desc;

    DataLevel(String level,String desc){
        this.desc = desc;
        this.level = level;
    }



}
