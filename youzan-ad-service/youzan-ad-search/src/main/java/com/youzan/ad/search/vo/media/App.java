package com.youzan.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by baimugudu on 2019/4/18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用得报名
     */
    private String packageName;

    /**
     * 应用得activity 名字
     */
    private String activityName;
}
