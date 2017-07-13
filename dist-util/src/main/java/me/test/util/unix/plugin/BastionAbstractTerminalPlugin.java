package me.test.util.unix.plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.test.util.unix.Bastion;
import me.test.util.unix.prococol.ssh.SshWrapper;
import me.test.util.unix.prococol.ssh2.Ssh2Wrapper;
import me.test.util.unix.prococol.telnet.TelnetWrapper;


public abstract class BastionAbstractTerminalPlugin extends AbstractTerminalPlugin{
	
	protected static final int TELNET = 0;
	protected static final int SSH = 1;
	protected static final int SSHv2 = 2;
	
	private static Log logger = LogFactory.getLog(BastionAbstractTerminalPlugin.class);

	private int terminalProtocol;
	private String user, password;
	
	public BastionAbstractTerminalPlugin(int terminalProtocol){
		
		this.terminalProtocol = terminalProtocol;
		if (Bastion.BastionProtocol.equalsIgnoreCase("telnet")){
			wrapper = new TelnetWrapper();
		} else if (Bastion.BastionProtocol.equalsIgnoreCase("ssh")){
			wrapper = new SshWrapper();
		} else if (Bastion.BastionProtocol.equalsIgnoreCase("sshv2")){
			wrapper = new Ssh2Wrapper();
		} else {
			throw new IllegalArgumentException("[E]Error Terminal protocol " + Bastion.BastionProtocol);
		}
	}
	@Override
	public boolean connect(String ip, int port, int net_delay) {
		try {
			connectToBastion();
		} catch (Exception e) {
			logger.error("[E] Connect to BastionHost failure", e);
			return false;
		}
		if (terminalProtocol == TELNET){
			try {
				telnet(ip, port, net_delay);
			} catch (Exception e) {
				logger.error("[E] Telnet Destination failure " + ip + ":" + port, e);
				return false;
			}
		} else {
			try {
				ssh(ip, port, net_delay);
			} catch (Exception e) {
				logger.error("[E] SSH Destination failure " + ip + ":" + port, e);
				return false;
			}
		}
		return true;
	}

	private void telnet(String ip, int port, int net_delay) throws Exception{
		String telnetcmd = Bastion.ScriptCommandTelnet.replaceAll("\\$\\{ip\\}", ip);
		telnetcmd = telnetcmd.replaceAll("\\$\\{port\\}", String.valueOf(port));
		logger.debug("[D] telnet command " + telnetcmd);
		wrapper.sendRawBytes((telnetcmd + "\n").getBytes());

	}
	
	private void ssh(String ip, int port, int net_delay) throws Exception {
		String sshcmd = Bastion.ScriptCommandSsh.replaceAll("\\$\\{ip\\}", ip);
		sshcmd = sshcmd.replaceAll("\\$\\{port\\}", String.valueOf(port));
		sshcmd = sshcmd.replaceAll("\\$\\{user\\}", this.user);
		
		logger.debug("[D] SSH command " + sshcmd);
		wrapper.sendRawBytes((sshcmd + "\n").getBytes());
		wrapper.waitfor(Bastion.ScriptCommandSshPrompt, net_delay);
		wrapper.sendRawBytes((this.password + "\n").getBytes());
	}
	
	private void connectToBastion() throws Exception{
		logger.debug("[D] connect to bastion");
		if (Bastion.BastionProtocol.equalsIgnoreCase("telnet")){
			wrapper.connect(Bastion.BastionHost, Bastion.BastionPort, Bastion.ScriptCommandTimeout);
			if (Bastion.BastionUser != null && !Bastion.BastionUser.trim().equals("")){
				wrapper.waitfor(Bastion.BastionPromptUser, Bastion.ScriptCommandTimeout);
				wrapper.sendRawBytes((Bastion.BastionUser + "\n").getBytes());
			}
			wrapper.waitfor(Bastion.BastionPromptPassword, Bastion.ScriptCommandTimeout);
			wrapper.sendRawBytes((Bastion.BastionPassword + "\n").getBytes());
			
			wrapper.waitfor(Bastion.BastionPromptLogin, Bastion.ScriptCommandTimeout);
		} else {
			((SshWrapper)wrapper).setLogin(Bastion.BastionUser);
			((SshWrapper)wrapper).setPassword(Bastion.BastionPassword);
			wrapper.connect(Bastion.BastionHost, Bastion.BastionPort, Bastion.ScriptCommandTimeout);			
		}
	}
	@Override
	public void setLogin(String user) {
		this.user = user;
	}

	@Override
	public void setPassword(String passwd) {
		this.password = passwd;
	}

}
