package orders.front.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.eastcom.ipnet.orders.entry.DeviceEntry;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * Description: <br>
 * Copyright: eastcom Copyright (C) 2012 <br>
 * 
 * @author xiazy<br>
 * @creatdate 2012-6-26 下午02:39:06 <br>
 *
 */
public interface IOrderRmiDevice extends Remote
{
    /**
     * 根据设备名与账号类型查找
     * @param devName
     * @param accType
     * @return
     */
    DeviceEntry  getDeviceByNameAccType(String devName,String accType)throws RemoteException;
    /**
     * 根据产家、地市查找  (针对GPON设备)
     * @param bussName  
     * @param factory
     * @param city
     * @return
     */
    DeviceEntry getDeviceByFactoryCity(String bussName,String factory,String city)throws RemoteException;
    
    /**
     *  查找设备关联的跳转设备
     * @param deviceId
     * @return
     * @throws RemoteException
     */
    List<DeviceEntry> getSwipeDeviceEntry(String deviceId)throws RemoteException;
    
}
