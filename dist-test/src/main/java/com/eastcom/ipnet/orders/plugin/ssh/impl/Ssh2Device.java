package com.eastcom.ipnet.orders.plugin.ssh.impl;

import java.util.HashMap;

import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.ssh2.Ssh2Wrapper;

public class Ssh2Device extends AbstractTerminalPlugin {

	public Ssh2Device(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;
		wrapper = new Ssh2Wrapper();
	}

	@Override
	public void setLogin(String user) {
		((Ssh2Wrapper) wrapper).setLogin(user);
	}

	@Override
	public void setPassword(String passwd) {
		((Ssh2Wrapper) wrapper).setPassword(passwd);
	}

	public boolean autoLogin(HashMap<String, Object> map) {
		return false;
	}

	@Override
	public boolean standardLogin(HashMap<String, Object> map) {

		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}
		return true;
	}
}
