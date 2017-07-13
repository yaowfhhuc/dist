package com.eastcom.ipnet.orders.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeviceEntryRmi implements Serializable
{
     /**
     * 
     */
    private static final long serialVersionUID = 1L;
    //  设备名                             登录IP
    private String deviceName,loginIp;
    //所属设备的账号信息
     private List<AccountEntryRmi> accs;
     
     public DeviceEntryRmi(){
         accs=new ArrayList<AccountEntryRmi>();
     }
     
    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public List<AccountEntryRmi> getAccs()
    {
        return accs;
    }

    public void setAccountEntity(AccountEntryRmi  ac)
    {
        this.accs.add(ac);
    }
   
}
