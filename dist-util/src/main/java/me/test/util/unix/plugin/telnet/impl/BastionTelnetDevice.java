package me.test.util.unix.plugin.telnet.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.test.util.unix.LoginParam;
import me.test.util.unix.plugin.BastionAbstractTerminalPlugin;


public class BastionTelnetDevice extends BastionAbstractTerminalPlugin {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(BastionTelnetDevice.class);

	public BastionTelnetDevice(int terminal_type, String sessionid) {
		super(TELNET);
		this.sessionid = sessionid;
		this.terminalType = terminal_type;		
	}

	public boolean autoLogin(HashMap<String, Object> map) {
		return false;
	}

	@Override
	public boolean standardLogin(HashMap<String, Object> map) {
		
		wrapper.startBastionQuitListener();
		
		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}
		return true;
	}
}
