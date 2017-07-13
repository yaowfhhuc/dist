package com.eastcom.ipnet.orders.message;

/**
 * 该类定义 proxy server 向dispatch server 的控制消息 type 可能取值为： 
 * DATA_SESSION_REQUEST ：   proxy server 向dispatch server请求建立数据连接
 * CONTROL_SESSION_REQUEST： proxy server 向dispatch server请求建立控制连接
 * 
 * @author localadmin
 */
public class S2DRequestMessage extends AbstractMessage {

	
	private static final long serialVersionUID = 5362284831359117253L;

	/**
	 * proxy 标志，暂定为所在主机的ip地址
	 */
	private String ProxyID = null;

	/**
	 * 用以建立数据通道的sessionid ，只在（从request消息中获取）
	 */
	private String sessionid = null;
	
	private String device_ip = null;
	
	private String login_user = null;
	
	@SuppressWarnings("unused")
	private Integer clientType = new Integer(1);
	
	/**
	 * 最大会话数
	 */
	private Integer sessionCount = null;
	
	private String deviceName;
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public Integer getSessionCount() {
		return sessionCount;
	}
	
	public void setSessionCount(Integer sessionCount) {
		this.sessionCount = sessionCount;
	}
	
	

	/**
	 * Gets proxy 标志，暂定为所在主机的ip地址.
	 * 
	 * @return proxy 标志，暂定为所在主机的ip地址
	 */
	public String getProxyID() {
		return ProxyID;
	}
	

	/**
	 * Sets proxy 标志，暂定为所在主机的ip地址.
	 * 
	 * @param proxyID
	 *            proxy 标志，暂定为所在主机的ip地址
	 */
	public void setProxyID(String proxyID) {
		ProxyID = proxyID;
	}

	/**
	 * @param sessionid
	 *            The sessionid to set.
	 */
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	 * @return Returns the sessionid.
	 */
	public String getSessionid() {
		return sessionid;
	}


	public String getDevice_ip() {
		return device_ip;
	}


	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}


	public String getLogin_user() {
		return login_user;
	}


	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}

}