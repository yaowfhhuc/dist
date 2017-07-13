package com.eastcom.ipnet.orders.front.service;

import java.io.Serializable;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * 
 * @author xiazy<br>
 * @e-mail: xiazy@east-sw.com <br>
 * @creatdate 2012-9-6 上午10:31:33 <br>
 *           指令平台设备模型
 */
public class DeviceCommon implements Serializable
{
    private String deviceIp, deviceName, deviceUuid,deviceAlias;

    public String getDeviceIp()
    {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp)
    {
        this.deviceIp = deviceIp;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceUuid()
    {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid)
    {
        this.deviceUuid = deviceUuid;
    }

    public String getDeviceAlias()
    {
        return deviceAlias;
    }

    public void setDeviceAlias(String deviceAlias)
    {
        this.deviceAlias = deviceAlias;
    }

}
