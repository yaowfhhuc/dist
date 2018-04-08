package orders.plugin.ssh.impl;

import java.io.IOException;
import java.util.HashMap;

import orders.plugin.util.LoginParam;
import orders.protocol.ssh.SshWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.ssh.SshWrapper;

public class SshDevice extends AbstractTerminalPlugin {

	private static Log logger = LogFactory.getLog(SshDevice.class);

	public SshDevice(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;

		wrapper = new SshWrapper();
	}

	@Override
	public void setLogin(String user) {
		((SshWrapper) wrapper).setLogin(user);
	}

	@Override
	public void setPassword(String passwd) {
		((SshWrapper) wrapper).setPassword(passwd);
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

		try {
			wrapper.sendNoWait("");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}

		return true;
	}
}
