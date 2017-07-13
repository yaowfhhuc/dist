package com.eastcom.ipnet.orders.message;



public class HeartBeatMessage extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String proxyID = null;

	public HeartBeatMessage() {
		super.setMsgType(Constants.HEART_BEAT);
	}

	public String getProxyID() {
		return proxyID;
	}

	public void setProxyID(String proxyID) {
		this.proxyID = proxyID;
	}
	
	
	
}
