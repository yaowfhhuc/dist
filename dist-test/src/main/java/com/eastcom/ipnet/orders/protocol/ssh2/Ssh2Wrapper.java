package com.eastcom.ipnet.orders.protocol.ssh2;

import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.message.Instruction;
import com.eastcom.ipnet.orders.protocol.Wrapper;

public class Ssh2Wrapper extends Wrapper {

	private static Log logger = LogFactory.getLog(Ssh2Wrapper.class);

	private Session session = null;
	private Proxy proxy = null;
	private int compression = 0;

	private JSch jsch = null;

	/** debugging level */
	@SuppressWarnings("unused")
	private final static int debug = 0;

	public Ssh2Wrapper() {
		super();
		try {
			jsch = new JSch();
			session = new Session(jsch);
		} catch (SshException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLogin(String user) {
		this.user = user;
		session.setLogin(user);
	}

	public void setPassword(String passwd) {
		this.password = passwd;
		session.setPassword(passwd);
	}

	
	public String send(String cmd, int net_delay) throws IOException {
		byte arr[];
		arr = (cmd + "\n").getBytes();
		// no write until authorization is done
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
			case 10: /* \n -> \r */
				arr[i] = 13;
				break;
			}
		}
		out.write(arr);
		if (getPrompt() != null)
			return waitfor(getPrompt(), net_delay * 1000);
		return null;
	}

	public void sendNoWait(String cmd) throws IOException {
		byte arr[];
		arr = (cmd + "\n").getBytes();
		// no write until authorization is done
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
			case 10: /* \n -> \r */
				arr[i] = 13;
				break;
			}
		}
		out.write(arr);
		return;
	}

	/**
	 * 等待timeout毫秒的时间,获取shell提示符
	 * 
	 * @param timeout
	 * @return
	 */
	public String searchPrompt(long timeout) {
		byte[] b = new byte[2048];
		int n = 0;
		String result = null;

		try {
			Thread.sleep(timeout);
			n = read(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.err.printf("HEX string = " );
		// for ( int i = 0 ;i< n ; i++)
		// System.err.printf("%x " ,b[i]);
		// System.err.printf("\n" );

		String info = new String(b, 0, n);
		// System.err.printf(info);

		StringTokenizer st = new StringTokenizer(info, "\n");

		while (st.hasMoreTokens()) {
			result = st.nextToken();
		}

		return result;
	}

	public void sendChars(String cmd) throws IOException {
		out.write(cmd.getBytes());
	}

	public void sendRawBytes(byte[] content) throws IOException {
		out.write(content);
	}

	public String sendCmd(Instruction instruction) throws IOException {
		// TODO Auto-generated method stub
		String cmd = instruction.getCommand();
		String cmd_prompt = instruction.getPrompt();
		int cmd_net_delay = instruction.getNet_delay() * 1000;

		byte[] arr;
		arr = (cmd + "\n").getBytes();
		out.write(arr);

		if (cmd_prompt == null || cmd_prompt.equals("")) {
			if (getPrompt() != null) {
				return waitfor(getPrompt(), cmd_net_delay);
			}
		} else {
			return waitfor(cmd_prompt, cmd_net_delay);
		}

		return null;
	}

	/**
	 * 该方法适用于仿真终端的命令发送
	 * 
	 * @param instruction
	 * @throws IOException
	 */
	public void sendCmdNoWait(Instruction instruction) throws IOException {

		String cmd = instruction.getCommand();
		String striped = null;
		byte[] arr;
		if (cmd.endsWith("\r\n")) {
			striped = cmd.substring(0, cmd.lastIndexOf("\r\n"));
		} else if (cmd.endsWith("\r")) {
			striped = cmd.substring(0, cmd.lastIndexOf("\r"));
		} else if (cmd.endsWith("\n")) {
			striped = cmd.substring(0, cmd.lastIndexOf("\n"));
		} else
			striped = cmd;

		arr = (striped + "\n").getBytes();
		out.write(arr);
		return;
	}

	public void connect(String host, int port, int net_delay) throws Exception {
		this.host = host;
		this.port = port;
		session = jsch.getSession(user, host, port);
		session.setProxy(proxy);

		DumbAuthUserInfo ui = new DumbAuthUserInfo();
		ui.setPassword(password);
		session.setUserInfo(ui);

		Properties config = new Properties();
		if (compression == 0) {
			config.put("compression.s2c", "none");
			config.put("compression.c2s", "none");
		} else {
			config.put("compression.s2c", "zlib,none");
			config.put("compression.c2s", "zlib,none");
		}
		session.setConfig(config);

		session.setTimeout(net_delay * 1000);
		session.connect();
		session.setTimeout(0);

		Channel channel = null;
		channel = session.openChannel("shell");
		out = channel.getOutputStream();
		in = channel.getInputStream();
		channel.connect();
	}

	/** Disconnect the socket and close the connection. */
	public void disconnect() throws IOException {
		logger.debug("Ssh2Wrapper: disconnect(" + host + "," + port + ") ");
		session.disconnect();

		if (readThread != null && readThread.isAlive()) {
			stopReaderThread();
		}

	}

	public int read(byte[] b) throws IOException {
		return in.read(b);
	}

}
