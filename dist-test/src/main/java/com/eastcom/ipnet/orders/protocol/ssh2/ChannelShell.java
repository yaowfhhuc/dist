package com.eastcom.ipnet.orders.protocol.ssh2;

import java.util.Enumeration;
import java.util.Hashtable;

public class ChannelShell extends ChannelSession {
	boolean xforwading = false;
	boolean pty = true;
	Hashtable env = null;

	public void setXForwarding(boolean foo) {
		xforwading = foo;
	}

	public void setPty(boolean foo) {
		pty = foo;
	}

	public void setEnv(Hashtable foo) {
		env = foo;
	}

	public void start() throws SshException {
		try {
			Request request;
			// if(xforwading){
			// request=new RequestX11();
			// request.request(session, this);
			// }

			if (pty) {
				request = new RequestPtyReq();
				request.request(session, this);
			}

			if (env != null) {
				for (Enumeration _env = env.keys(); _env.hasMoreElements();) {
					String name = (String) (_env.nextElement());
					String value = (String) (env.get(name));
					request = new RequestEnv();
					((RequestEnv) request).setEnv(name, value);
					request.request(session, this);
				}
			}

			request = new RequestShell();
			request.request(session, this);
		} catch (Exception e) {
			throw new SshException("ChannelShell");
		}
		thread = new Thread(this);
		thread.setName("Shell for " + session.host);
		thread.start();
	}

	// public void finalize() throws Throwable{ super.finalize(); }
	public void init() {
		io.setInputStream(session.in);
		io.setOutputStream(session.out);
	}

	public void setPtySize(int col, int row, int wp, int hp) {
		// if(thread==null) return;
		try {
			RequestWindowChange request = new RequestWindowChange();
			request.setSize(col, row, wp, hp);
			request.request(session, this);
		} catch (Exception e) {
			System.out.println("ChannelShell.setPtySize: " + e);
		}
	}
}
