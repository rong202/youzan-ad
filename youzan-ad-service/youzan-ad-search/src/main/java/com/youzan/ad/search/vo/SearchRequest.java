package com.youzan.ad.search.vo;

import com.youzan.ad.search.vo.media.AdSlot;
import com.youzan.ad.search.vo.media.App;
import com.youzan.ad.search.vo.media.Device;
import com.youzan.ad.search.vo.media.Geo;
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
public class SearchRequest {

    /**
     * 媒体方标识id
     */
    private String mediaId;

    /**
     * 请求基本信息
     */
    private RequestInfo requestInfo;

    /**
     * 请求匹配信息
     */
    private FeatureInfo FeatureInfo;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo{
        private String requestId;
        private List<AdSlot> adSlots;
        private App app;
        private Geo geo;
        private Device device;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeatureInfo{

    }


}
