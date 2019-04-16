package com.youzan.ad.sender;

import com.youzan.ad.mysql.dto.MySqlRowData;

/**
 * Created by baimugudu on 2019/4/16
 */
public interface ISender {

    void sender(MySqlRowData rowData);

}
