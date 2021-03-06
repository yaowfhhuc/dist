package orders.plugin.ssh.impl;

import java.util.HashMap;

import com.eastcom.ipnet.orders.plugin.BastionAbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import orders.plugin.util.LoginParam;

public class BastionSshDevice extends BastionAbstractTerminalPlugin{

	public BastionSshDevice(int terminal_type, String sessionid) {
		super(SSH);
		this.sessionid = sessionid;
		this.terminalType = terminal_type;		
	}
	
	@Override
	public boolean autoLogin(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
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
