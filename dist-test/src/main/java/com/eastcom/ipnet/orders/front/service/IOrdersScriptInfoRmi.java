package com.eastcom.ipnet.orders.front.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.eastcom.ipnet.orders.entry.ScriptEntry;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * 
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-6-26 下午02:45:37 <br>
 * 
 */
public interface IOrdersScriptInfoRmi extends Remote
{
    /**
     * 通过设备名与相应指令得到匹配的脚本信息
     * 
     * @param deviceName
     * @param command
     * @return
     */
    ScriptEntry getScriptInfoByDeviceNameCommand(String deviceName,
            String command) throws RemoteException;

}
