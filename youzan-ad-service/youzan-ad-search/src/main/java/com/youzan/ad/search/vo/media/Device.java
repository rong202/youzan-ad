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
public class Device {

    /**
     * 设备编码（id）
     */
    private String deviceCode;

    /**
     * mac地址
     */
    private String mac;

    /**
     * ip
     */
    private String ip;

    /**
     * 机型
     */
    private String model;

    /**
     * 分辨率得尺寸
     */
    private String displaySize;

    /**
     * 屏幕得尺寸
     */
    private String screenSize;
    /**
     * 设备序列号
     */
    private String serialName;
}
