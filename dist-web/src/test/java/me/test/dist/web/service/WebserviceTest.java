/**   
* @Title: WebserviceTest.java 
* @Package me.test.dist.web.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月27日 下午3:51:35 
* @version V1.0   
*/
package me.test.dist.web.service;

import java.rmi.RemoteException;

import me.test.dist.webservice.util.IJSNWEOMSServiceProxy;

/** 
* @ClassName: WebserviceTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月27日 下午3:51:35 
*  
*/
public class WebserviceTest {

	public static void main(String[] args) {
		
		IJSNWEOMSServiceProxy proxy  = new IJSNWEOMSServiceProxy();
		try {
			proxy.alarmOrder("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
