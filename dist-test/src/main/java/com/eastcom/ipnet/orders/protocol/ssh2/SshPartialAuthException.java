package com.eastcom.ipnet.orders.protocol.ssh2;

public class SshPartialAuthException extends SshException {
	
		/**
		 * serialVersionUID:TODO
		 *
		 * @since Ver 1.1
		 */
		
	private static final long serialVersionUID = 1L;
	String methods;

	public SshPartialAuthException() {
		super();
	}

	public SshPartialAuthException(String s) {
		super(s);
		this.methods = s;
	}

	public String getMethods() {
		return methods;
	}
}
