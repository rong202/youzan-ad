package com.youzan.ad.search.vo;

import com.youzan.ad.index.creative.CreativeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baimugudu on 2019/4/18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private Map<String,List<Creative>> adSlotAds = new HashMap<>();


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class  Creative{
        private Long adId;

        private String adUrl;

        private Integer height;
        private Integer width;

        private Integer type;

        private Integer materialType;

        //展示检测url
        private List<String> showMonitorUrl =
                Arrays.asList("www.baidu.com/add","www.youzan.com/add");

        //点击监测url
        private List<String> clickMonitor =
                Arrays.asList("www.baidu.com/click","www.youzan.com/click");





    }

    public static Creative convert(CreativeObject object){
        Creative creative = new Creative();
        creative.setAdId(object.getAdId()
        );
        creative.setAdUrl(object.getAdUrl());
        creative.setHeight(object.getHeight());
        creative.setMaterialType(object.getMaterialType());
        creative.setType(object.getType());
        creative.setWidth(object.getWidth());
        return creative;
    }
}
