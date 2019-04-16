package com.youzan.ad.mysql.listenter;

import com.youzan.ad.mysql.dto.BinLogRowData;

/**
 * Created by baimugudu on 2019/4/12
 */
public interface Ilistener {

     void  register();

     void onEvent(BinLogRowData eventData);


}
