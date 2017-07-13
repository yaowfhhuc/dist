package com.eastcom.ipnet.orders.entry;


public class LogEntry {
	private String sessionId;

	private String deviceName;

	private String loginUser;

	private String startTime;

	private String endTime;

	private String loginIp;

	private Integer loginType;

	private String proxyIp;

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String name) {
		this.deviceName = name;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String session_id) {
		this.sessionId = session_id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String start_time) {
		this.startTime = start_time;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String user) {
		this.loginUser = user;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
}
