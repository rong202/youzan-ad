package com.youzan.ad.search;

import com.youzan.ad.search.vo.SearchRequest;
import com.youzan.ad.search.vo.SearchResponse;

/**
 * Created by baimugudu on 2019/4/18
 */
public interface ISearch {

    SearchResponse search(SearchRequest request);
}
