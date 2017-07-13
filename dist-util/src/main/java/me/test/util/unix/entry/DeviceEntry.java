package me.test.util.unix.entry;

import java.io.Serializable;


public class DeviceEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 200825872805471524L;

	private String deviceName,deviceAlias, proxyIp, userName, password, deviceIP,loginCmd,prompt,commandPrompt,endMask,deviceCode;

	private Integer sessionCount,telnetType,port;
	
	private String swipDeviceId, beforeCommand, afterCommand;
	
	public String getDeviceAlias() {
		return deviceAlias;
	}

	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	
	public void setCommandPrompt(String commandPrompt) {
		this.commandPrompt = commandPrompt;
	}
	
	public String getCommandPrompt() {
		return commandPrompt;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getSwipDeviceId() {
		return swipDeviceId;
	}
	
	public void setSwipDeviceId(String swipDeviceId) {
		this.swipDeviceId = swipDeviceId;
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	public String getLoginCmd() {
		return loginCmd;
	}
	
	public void setLoginCmd(String loginCmd) {
		this.loginCmd = loginCmd;
	}
	
	public Integer getTelnetType() {
		return telnetType;
	}
	
	public void setTelnetType(Integer telnetType) {
		this.telnetType = telnetType;
	}

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

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEndMask() {
		return endMask;
	}

	public void setEndMask(String endMask) {
		this.endMask = endMask;
	}

    public String getDeviceCode()
    {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }

	public String getBeforeCommand() {
		return beforeCommand;
	}

	public void setBeforeCommand(String beforeCommand) {
		this.beforeCommand = beforeCommand;
	}

	public String getAfterCommand() {
		return afterCommand;
	}

	public void setAfterCommand(String afterCommand) {
		this.afterCommand = afterCommand;
	}
	
	

}