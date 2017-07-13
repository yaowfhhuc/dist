package com.eastcom.ipnet.orders.front.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.eastcom.ipnet.orders.entry.DeviceEntryRmi;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * @author xiazy<br>
 * @e-mail: xiazy@east-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2012-6-1 上午10:30:38 <br>
 *RmiService接口
 */
public interface IOrdersRmiService extends Remote
{
    /**
     * 通过业务名查询
     * @param bussName
     * @return
     */
    List<DeviceEntryRmi> getAccountByBussionName(String bussName)throws RemoteException;
    /**
     * 通过IP
     * @param IP
     * @return
     */
    List<DeviceEntryRmi> getAccountByIP(String IP)throws RemoteException;
    /**
     * 通过设备名得到账号信息
     * @param deviceName
     * @return
     */
    List<DeviceEntryRmi>   getAccountByDeviceName(String deviceName)throws RemoteException;
    /**
     * 
     * @param fName 产家
     * @param cName 地市
     * @return
     */
    List<DeviceEntryRmi>   getAccountByFactoryCity(String fName,String cName)throws RemoteException;
    /**
     * 
     * @return
     */
    //DeviceEntryRmi  getAllAccount();
}
