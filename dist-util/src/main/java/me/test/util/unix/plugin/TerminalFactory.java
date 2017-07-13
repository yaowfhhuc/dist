package me.test.util.unix.plugin;

import me.test.util.unix.Bastion;
import me.test.util.unix.message.Constants;
import me.test.util.unix.plugin.ssh.impl.BastionSshDevice;
import me.test.util.unix.plugin.ssh.impl.Ssh2Device;
import me.test.util.unix.plugin.ssh.impl.SshDevice;
import me.test.util.unix.plugin.telnet.impl.BastionTelnetDevice;
import me.test.util.unix.plugin.telnet.impl.TelnetDevice;

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

		if (transport ==Constants.PROTOCOL_SSH_NUMBER) {
			if (Bastion.on)
				plugin = new BastionSshDevice(terminalType, sessionId);
			else
				plugin = new SshDevice(terminalType, sessionId);
		} else if (transport == Constants.PROTOCOL_SSH2_NUMBER) {
			if (Bastion.on)
				plugin = new BastionSshDevice(terminalType, sessionId);
			else
				plugin = new Ssh2Device(terminalType, sessionId);
		} else {
			if (Bastion.on)
				plugin = new BastionTelnetDevice(terminalType, sessionId);
			else
				plugin = new TelnetDevice(terminalType, sessionId);
		}

		return plugin;

	}
}
