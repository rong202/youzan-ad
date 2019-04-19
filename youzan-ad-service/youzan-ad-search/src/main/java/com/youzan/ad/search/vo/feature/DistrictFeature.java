package com.youzan.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by baimugudu on 2019/4/18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictFeature {
    private List<ProviceAndCity> proviceAndCities;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProviceAndCity{
    private String provice;
    private String city;
    }



}
