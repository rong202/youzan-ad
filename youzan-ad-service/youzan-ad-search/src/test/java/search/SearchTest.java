package search;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.search.vo.SearchRequest;
import com.youzan.ad.search.vo.feature.DistrictFeature;
import com.youzan.ad.search.vo.feature.FeatureRelation;
import com.youzan.ad.search.vo.feature.ItFeature;
import com.youzan.ad.search.vo.feature.KeywordFeature;
import com.youzan.ad.search.vo.media.AdSlot;
import com.youzan.ad.search.vo.media.App;
import com.youzan.ad.search.vo.media.Device;
import com.youzan.ad.search.vo.media.Geo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baimugudu on 2019/4/26
 */
public class SearchTest {
    public static void main(String[] args) {


        ArrayList<DistrictFeature.ProviceAndCity> arrayList = new ArrayList();
        DistrictFeature.ProviceAndCity proviceAndCity = new DistrictFeature.ProviceAndCity();
        proviceAndCity.setCity("邯郸市");
        proviceAndCity.setProvice("河北省");
        arrayList.add(proviceAndCity);



        DistrictFeature districtFeature = new DistrictFeature();
        districtFeature.setProviceAndCities(arrayList);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("台球");
        ItFeature itFeature = new ItFeature();
        itFeature.setIts(strings);


        List<String> lists = new ArrayList<String>();
        lists.add("奥迪");

        KeywordFeature keywordFeature = new KeywordFeature();
        keywordFeature.setKeywords(lists);

        SearchRequest.FeatureInfo featureInfo = new SearchRequest.FeatureInfo();
        featureInfo.setDistrictFeature(districtFeature);
        featureInfo.setFeatureRelation(FeatureRelation.AND);
        featureInfo.setItFeature(itFeature);
        featureInfo.setKeywordFeature(keywordFeature);


        App app = new App();
        app.setActivityName("indexActivity");
        app.setAppCode("a102");
        app.setAppName("抖音");
        app.setPackageName("com.douyin");


        Device device = new Device();
       device.setDeviceCode("fhdjfjkd");
       device.setDisplaySize("100");
       device.setIp("127.0.0.1");
       device.setMac("dshg-378n-reyujn");
       device.setModel("index");
       device.setScreenSize("5400");
       device.setSerialName("index-show");

        Geo geo = new Geo();
        geo.setCity("邯郸市");
        geo.setLatitude((long) 110);
        geo.setLongitude((long) 89.90);
        geo.setProvince("河北省");

        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(2);

        List<AdSlot> list= new ArrayList();
        AdSlot adSlot = new AdSlot();
        adSlot.setAdSlotCode("0012");
        adSlot.setHeight(100);
        adSlot.setNimCpm(200);
        adSlot.setPositionType(2);
        adSlot.setWidth(320);
        adSlot.setType(objects);
        list.add(adSlot);

        SearchRequest.RequestInfo requestInfo = new SearchRequest.RequestInfo();
        requestInfo.setRequestId("001");
        requestInfo.setApp(app);
        requestInfo.setDevice(device);
        requestInfo.setGeo(geo);
        requestInfo.setAdSlots(list);



        SearchRequest request = new SearchRequest();
        request.setMediaId("001");
        request.setFeatureInfo(featureInfo);
        request.setRequestInfo(requestInfo);

        System.out.println(JSON.toJSONString(request));


    }

}
