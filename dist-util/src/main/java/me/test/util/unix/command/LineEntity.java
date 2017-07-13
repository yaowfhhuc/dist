package me.test.util.unix.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LineEntity implements Serializable{
	
	private static final long serialVersionUID = 2656406824462376865L;
	private String deviceName;
	private String businessSystem;
	private String deviceIp;
	private String deviceType;
	private String telnetType;
	private String port;
	private String proxyId;
	private String loginId;
	private String loginPassword;
	private String prompt;
	private String loginResult;
	private String loginMark;
	private String traceRouteResult;
	private String traceRouteMark;
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("deviceName",deviceName );
		map.put("businessSystem",businessSystem );
		map.put("deviceIp", deviceIp);
		map.put("deviceType",deviceType );
		map.put("telnetType",telnetType );
		map.put("port", port);
		map.put("proxyId",proxyId );
		map.put("loginId",loginId);
		map.put("loginPassword",loginPassword );
		map.put("prompt", prompt);
		map.put("loginResult",loginResult );
		map.put("loginMark",loginMark );
		map.put("traceRouteResult", traceRouteResult);
		map.put("traceRouteMark",traceRouteMark );
		return map;
	}
	
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the businessSystem
	 */
	public String getBusinessSystem() {
		return businessSystem;
	}
	/**
	 * @param businessSystem the businessSystem to set
	 */
	public void setBusinessSystem(String businessSystem) {
		this.businessSystem = businessSystem;
	}
	/**
	 * @return the deviceIp
	 */
	public String getDeviceIp() {
		return deviceIp;
	}
	/**
	 * @param deviceIp the deviceIp to set
	 */
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the telnetType
	 */
	public String getTelnetType() {
		return telnetType;
	}
	/**
	 * @param telnetType the telnetType to set
	 */
	public void setTelnetType(String telnetType) {
		this.telnetType = telnetType;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the proxyId
	 */
	public String getProxyId() {
		return proxyId;
	}
	/**
	 * @param proxyId the proxyId to set
	 */
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}
	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}
	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * @return the loginResult
	 */
	public String getLoginResult() {
		return loginResult;
	}

	/**
	 * @param loginResult the loginResult to set
	 */
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	/**
	 * @return the loginMark
	 */
	public String getLoginMark() {
		return loginMark;
	}

	/**
	 * @param loginMark the loginMark to set
	 */
	public void setLoginMark(String loginMark) {
		this.loginMark = loginMark;
	}

	/**
	 * @return the traceRouteResult
	 */
	public String getTraceRouteResult() {
		return traceRouteResult;
	}

	/**
	 * @param traceRouteResult the traceRouteResult to set
	 */
	public void setTraceRouteResult(String traceRouteResult) {
		this.traceRouteResult = traceRouteResult;
	}

	/**
	 * @return the traceRouteMark
	 */
	public String getTraceRouteMark() {
		return traceRouteMark;
	}

	/**
	 * @param traceRouteMark the traceRouteMark to set
	 */
	public void setTraceRouteMark(String traceRouteMark) {
		this.traceRouteMark = traceRouteMark;
	}
	
}