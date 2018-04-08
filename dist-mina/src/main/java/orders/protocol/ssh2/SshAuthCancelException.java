package orders.protocol.ssh2;

class SshAuthCancelException extends SshException {
	
		/**
		 * serialVersionUID:TODO
		 *
		 * @since Ver 1.1
		 */
		
	private static final long serialVersionUID = 1L;
	String method;

	SshAuthCancelException() {
		super();
	}

	SshAuthCancelException(String s) {
		super(s);
		this.method = s;
	}

	public String getMethod() {
		return method;
	}
}
