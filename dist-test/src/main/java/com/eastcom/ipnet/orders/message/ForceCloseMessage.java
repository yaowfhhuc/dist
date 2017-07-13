package com.eastcom.ipnet.orders.message;

public class ForceCloseMessage extends AbstractMessage{


	private static final long serialVersionUID = 3688506575899604273L;
	
	private String sessionid;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
	

}
