package orders.plugin;

import com.eastcom.ipnet.orders.message.Constants;
import com.eastcom.ipnet.orders.plugin.db.impl.DbDevice;
import com.eastcom.ipnet.orders.plugin.ssh.impl.BastionSshDevice;
import com.eastcom.ipnet.orders.plugin.ssh.impl.Ssh2Device;
import com.eastcom.ipnet.orders.plugin.ssh.impl.SshDevice;
import com.eastcom.ipnet.orders.plugin.telnet.impl.BastionTelnetDevice;
import com.eastcom.ipnet.orders.plugin.telnet.impl.TelnetDevice;
import com.eastcom.ipnet.orders.plugin.util.Bastion;
import orders.message.Constants;
import orders.plugin.db.impl.DbDevice;
import orders.plugin.ssh.impl.BastionSshDevice;
import orders.plugin.ssh.impl.Ssh2Device;
import orders.plugin.ssh.impl.SshDevice;
import orders.plugin.telnet.impl.BastionTelnetDevice;
import orders.plugin.telnet.impl.TelnetDevice;
import orders.plugin.util.Bastion;

public class TerminalFactory {

	/**
	 * 创建访问设备的终端
	 * 
	 * @param transport
	 *            终端连接协议 ssh/ssh2 或者 telnet
	 * @param terminalType
	 *            终端操作类型 batch command 或者 simulate terminal
	 * @param sessionId
	 *            绑定的会话ID
	 * @return
	 */
	public static IPlugin createTerminal(Integer transport, int terminalType,
			String sessionId) {

		IPlugin plugin = null;

		if (transport == Constants.PROTOCOL_SSH_NUMBER) {
			if (Bastion.on)
				plugin = new BastionSshDevice(terminalType, sessionId);
			else
				plugin = new SshDevice(terminalType, sessionId);
		} else if (transport == Constants.PROTOCOL_SSH2_NUMBER) {
			if (Bastion.on)
				plugin = new BastionSshDevice(terminalType, sessionId);
			else
				plugin = new Ssh2Device(terminalType, sessionId);
		} else if (transport == Constants.PROTOCOL_DB_NUMBER) {
			plugin = new DbDevice(terminalType, sessionId);
		} else {
			if (Bastion.on)
				plugin = new BastionTelnetDevice(terminalType, sessionId);
			else
				plugin = new TelnetDevice(terminalType, sessionId);
		}

		return plugin;

	}
}
