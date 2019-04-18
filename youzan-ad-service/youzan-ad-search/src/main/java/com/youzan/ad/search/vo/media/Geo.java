package com.youzan.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by baimugudu on 2019/4/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    /**
     * 纬度
     */
    private Long latitude;

    /**
     * 经度
     */
    private Long longitude;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

}
