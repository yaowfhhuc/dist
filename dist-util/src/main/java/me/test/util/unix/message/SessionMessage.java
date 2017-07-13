package me.test.util.unix.message;

public class SessionMessage extends AbstractMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3256726186553192752L;
	
	
	private String device_ip;
	
	private String proxy_id;
	
	private String login_use;

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(String proxy_id) {
		this.proxy_id = proxy_id;
	}

	public String getLogin_use() {
		return login_use;
	}

	public void setLogin_use(String login_use) {
		this.login_use = login_use;
	}
	
	

}
