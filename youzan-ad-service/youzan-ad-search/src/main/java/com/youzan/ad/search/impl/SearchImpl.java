package com.youzan.ad.search.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.youzan.ad.index.CommonStatus;
import com.youzan.ad.index.DataTable;
import com.youzan.ad.index.adunit.AdUnitIndex;
import com.youzan.ad.index.adunit.AdUnitObject;
import com.youzan.ad.index.creative.CreativeIndex;
import com.youzan.ad.index.creative.CreativeObject;
import com.youzan.ad.index.creativeunit.CreativeUnitIndex;
import com.youzan.ad.index.district.UnitDistrictIndex;
import com.youzan.ad.index.interest.UnitItIndex;
import com.youzan.ad.index.keyword.UnitKeyWordIndex;
import com.youzan.ad.search.ISearch;
import com.youzan.ad.search.vo.SearchRequest;
import com.youzan.ad.search.vo.SearchResponse;
import com.youzan.ad.search.vo.feature.DistrictFeature;
import com.youzan.ad.search.vo.feature.FeatureRelation;
import com.youzan.ad.search.vo.feature.ItFeature;
import com.youzan.ad.search.vo.feature.KeywordFeature;
import com.youzan.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * Created by baimugudu on 2019/4/18
 */

@Slf4j
public class SearchImpl implements ISearch {


    public SearchResponse fallback(SearchRequest request, Throwable e) {
        return null;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public SearchResponse search(SearchRequest request) {

        // 请求的广告位信息
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();

        // 三个 Feature
        KeywordFeature keywordFeature =
                request.getFeatureInfo().getKeywordFeature();
        DistrictFeature districtFeature =
                request.getFeatureInfo().getDistrictFeature();
        ItFeature itFeature =
                request.getFeatureInfo().getItFeature();

        FeatureRelation relation = request.getFeatureInfo().getFeatureRelation();

        // 构造响应对象
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads =
                response.getAdSlotAds();

        for (AdSlot adSlot : adSlots) {

            Set<Long> targetUnitIdSet;

            // 根据流量类型获取初始 AdUnit
            Set<Long> adUnitIdSet = DataTable.of(
                    AdUnitIndex.class
            ).match(adSlot.getPositionType());

            if (relation == FeatureRelation.AND) {

                filterKeywordFeature(adUnitIdSet, keywordFeature);
                filterDistrictFeature(adUnitIdSet, districtFeature);
                filterItTagFeature(adUnitIdSet, itFeature);

                targetUnitIdSet = adUnitIdSet;

            } else {
                targetUnitIdSet = getORRelationUnitIds(
                        adUnitIdSet,
                        keywordFeature,
                        districtFeature,
                        itFeature
                );
            }

            List<AdUnitObject> unitObjects =
                    DataTable.of(AdUnitIndex.class).fetch(targetUnitIdSet);

            filterAdUnitAndPlanStatus(unitObjects, CommonStatus.VALID);

            List<Long> adIds = DataTable.of(CreativeUnitIndex.class)
                    .selectAds(unitObjects);
            List<CreativeObject> creatives = DataTable.of(CreativeIndex.class)
                    .fetch(adIds);

            // 通过 AdSlot 实现对 CreativeObject 的过滤
            filterCreativeByAdSlot(
                    creatives,
                    adSlot.getWidth(),
                    adSlot.getHeight(),
                    adSlot.getType()
            );

            adSlot2Ads.put(
                    adSlot.getAdSlotCode(), buildCreativeResponse(creatives)
            );
        }

        log.info("fetchAds: {}-{}",
                JSON.toJSONString(request),
                JSON.toJSONString(response));

        return response;
    }

    private Set<Long> getORRelationUnitIds(Set<Long> adUnitIdSet,
                                           KeywordFeature keywordFeature,
                                           DistrictFeature districtFeature,
                                           ItFeature itFeature) {

        if (CollectionUtils.isEmpty(adUnitIdSet)) {
            return Collections.emptySet();
        }

        Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);

        filterKeywordFeature(keywordUnitIdSet, keywordFeature);
        filterDistrictFeature(districtUnitIdSet, districtFeature);
        filterItTagFeature(itUnitIdSet, itFeature);

        return new HashSet<>(
                CollectionUtils.union(
                        CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet),
                        itUnitIdSet
                )
        );
    }

    private void filterKeywordFeature(
            Collection<Long> adUnitIds, KeywordFeature keywordFeature) {

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitKeyWordIndex.class)
                                    .match(adUnitId,
                                            keywordFeature.getKeywords())
            );
        }
    }

    private void filterDistrictFeature(
            Collection<Long> adUnitIds, DistrictFeature districtFeature
    ) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(districtFeature.getProviceAndCities())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitDistrictIndex.class)
                                    .match(adUnitId,
                                            districtFeature.getProviceAndCities())
            );
        }
    }

    private void filterItTagFeature(Collection<Long> adUnitIds,
                                    ItFeature itFeature) {

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(itFeature.getIts())) {

            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitItIndex.class)
                                    .match(adUnitId,
                                            itFeature.getIts())
            );
        }
    }

    private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects,
                                           CommonStatus status) {

        if (CollectionUtils.isEmpty(unitObjects)) {
            return;
        }

        CollectionUtils.filter(
                unitObjects,
                object -> object.getUnitStatus().equals(status.getStatus())
                        && object.getAdPlanObject().getPlanStatus().equals(status.getStatus())
        );
    }

    private void filterCreativeByAdSlot(List<CreativeObject> creatives,
                                        Integer width,
                                        Integer height,
                                        List<Integer> type) {

        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }

        CollectionUtils.filter(
                creatives,
                creative ->
                        creative.getAuditStatus().equals(CommonStatus.VALID.getStatus())
                                && creative.getWidth().equals(width)
                                && creative.getHeight().equals(height)
                                && type.contains(creative.getType())
        );
    }

    private List<SearchResponse.Creative> buildCreativeResponse(
            List<CreativeObject> creatives
    ) {

        if (CollectionUtils.isEmpty(creatives)) {
            return Collections.emptyList();
        }

        CreativeObject randomObject = creatives.get(
                Math.abs(new Random().nextInt()) % creatives.size()
        );

        return Collections.singletonList(
                SearchResponse.convert(randomObject)
        );
    }


}
