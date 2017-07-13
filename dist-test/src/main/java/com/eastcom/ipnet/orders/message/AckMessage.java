package com.eastcom.ipnet.orders.message;


/**
 * 该类定义为 dispatch server 到 proxy client 或 dispatch server 到 proxy server 应答/通知信息
 * 消息类型可能为： 
 * 		SIMTERM_SESSION_ACK: 通知proxy client 仿真终端通道是否建立
 * 		CONTROL_SESSION_ACK ：通知proxy server 控制连接是否建立 （从控制连接发回） 
 * 		DATA_SESSION_ACK  ：通知proxy server 数据连接是否建立（从数据连接发回）
 * 
 * @author 
 */
public class AckMessage extends AbstractMessage {

	private static final long serialVersionUID = -6214584975356941196L;

	/**
	 * 错误信息描述
	 */
	private String msg;
	
	/**
	 * 返回码
	 */
	private int resultCode;
	
	private String sessionid;

	
	
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	 * Gets 错误信息描述.
	 * 
	 * @return 错误信息描述
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets 错误信息描述.
	 * 
	 * @param msg
	 *            错误信息描述
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	/**
	 * Gets 返回码.
	 * 
	 * @return 返回码
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * Sets 返回码.
	 * 
	 * @param resultCode
	 *            返回码
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
}