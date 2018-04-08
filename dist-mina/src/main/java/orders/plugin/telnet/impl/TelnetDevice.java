package orders.plugin.telnet.impl;

import java.util.HashMap;

import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.telnet.TelnetWrapper;
import orders.protocol.telnet.TelnetWrapper;

public class TelnetDevice extends AbstractTerminalPlugin {


	public TelnetDevice(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;
		wrapper = new TelnetWrapper();
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
//		try {
//			if (map.get(Constants.ATTR_LOGIN_COMMAND) == null) {
//				wrapper.login(map.get(Constants.ATTR_DEV_USER),
//						Constants.ATTR_DEV_PASSWORD, "user", "pass", 5);
//				wrapper.waitfor(map.get(Constants.ATTR_LOGIN_PROMPT), 5);
//			} else {
//				wrapper.send(map.get(Constants.ATTR_LOGIN_COMMAND));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return true;
	}

	@Override
	public void setLogin(String user) {

	}

	@Override
	public void setPassword(String passwd) {

	}

}
