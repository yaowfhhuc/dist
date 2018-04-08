package orders.protocol;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Pattern;

import orders.protocol.telnet.ScriptHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.message.Instruction;
import com.eastcom.ipnet.orders.plugin.IDataReceive;
import com.eastcom.ipnet.orders.plugin.util.Bastion;
import com.eastcom.ipnet.orders.protocol.telnet.ScriptHandler;

public class Wrapper {
	/** debugging level */
	private final static int debug = 0;

	final private static Log logger = LogFactory.getLog(Wrapper.class);

	protected ScriptHandler scriptHandler = new ScriptHandler();

	protected TerminalReadThread readThread = null;

	protected InputStream in;

	protected OutputStream out;

	protected Socket socket;

	protected String host = null;

	protected String prompt = null;

	protected int port = 23;

	protected int netDelay = 1;

	protected String user = null;

	protected String password = null;

	// protected Vector script = new Vector();

	protected IDataReceive listener = null;
	private IDataReceive bastionlistener = null;
	
	private String[] defaultMatchMoreTagList = null;

	private String[] matchMoreTagList = null;

	private String[] loginConfirmPatternList = null;

	private String[] defaultLoginConfirmPatternList = null;

	public Wrapper() {
		defaultMatchMoreTagList = new String[5];
		defaultMatchMoreTagList[0] = "--More--";
		defaultMatchMoreTagList[1] = "-- More --";
		defaultMatchMoreTagList[2] = "--还有--";
		defaultMatchMoreTagList[3] = "-- 还有 --";
		defaultMatchMoreTagList[4] = "--- more ---";

		defaultLoginConfirmPatternList = new String[1];
		defaultLoginConfirmPatternList[0] = ".*(([>,#,$,%,@,\\]](\\s)?)$)";

	}

	protected void setLogin(String user) {
	}

	protected void setPassword(String passwd) {
	}

	public int getNetdelay() {
		return netDelay;
	}

	public void setNetDelay(int net_delay) {
		this.netDelay = net_delay;
	}

	public IDataReceive getListener() {
		return listener;
	}

	public void setListener(IDataReceive listener) {
		this.listener = listener;
	}

	public void startBastionQuitListener(){
		if (Bastion.on){
			this.bastionlistener = new BastionQuitListener();
		}
	}
	
	public IDataReceive getBastionQuitListener(){
		return bastionlistener;
	}
	
	/** Connect the socket and open the connection. */
	public void connect(String host, int port, int net_delay) throws Exception {

		if (logger.isDebugEnabled())
			logger.debug("Wrapper: connect(" + host + "," + port + ") ");
		try {
			socket = new java.net.Socket(host, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (Exception e) {
			logger.error("[E]Wrapper: connect(" + host + "," + port
					+ ") has exception :" + e);
			disconnect();
			throw ((IOException) e);
		}
	}

	/** Disconnect the socket and close the connection. */
	public void disconnect() throws IOException {
		logger.debug("Wrapper: disconnect(" + host + "," + port + ") "+socket);
		if (socket != null) {
			socket.close();
		}

		if (readThread != null && readThread.isAlive()) {
			stopReaderThread();
		}

	}

	/**
	 * Login into remote host. This is a convenience method and only works if
	 * the prompts are "login:" and "Password:".
	 * 
	 * @param user
	 *            the user name
	 * @param pwd
	 *            the password
	 */
	public void login(String user, String pwd, String user_prompt,
			String pwd_prompt, int net_delay) throws IOException {

		waitfor(user_prompt, net_delay * 1000); // throw output away
		send(user);
		waitfor(pwd_prompt, net_delay * 1000); // throw output away
		send(pwd);

	}

	/**
	 * 改变登录用户的操作级别，或特权等级，只适用于网络设备
	 * 
	 * @param pwd
	 * @param pwd_prompt
	 * @param net_delay
	 * @throws IOException
	 */
	public void changeLevel(String pwd, String pwd_prompt, int net_delay)
			throws IOException {
		waitfor(pwd_prompt, net_delay * 1000); // throw output away
		send(pwd);
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String searchPrompt(long timeout) {
		return null;
	}

	public String getPrompt() {
		return prompt;
	}

	public String send(String command) throws IOException {
		return null;
	}

	public void sendNoWait(String cmd) throws IOException {
		return;
	}

	public void sendChars(String cmd) throws IOException {
		return;
	}

	/**
	 * Send a command to the remote host. A newline is appended and if a prompt
	 * is set it will return the resulting data until the prompt is encountered.
	 * 
	 * @param cmd
	 *            the command
	 * @return output of the command or null if no prompt is set
	 */
	public String sendCmd(Instruction instruction) throws IOException {
		return null;
	}

	public void sendCmdNoWait(Instruction instruction) throws IOException {
		return;
	}

	public void sendRawBytes(byte[] content) throws IOException {
		return;
	}

	/**
	 * Wait for a string to come from the remote host and return all that
	 * characters that are received until that happens (including the string
	 * being waited for).
	 * 
	 * @param match
	 *            the string to look for
	 * @return skipped characters
	 */
	public String waitfor(String[] searchElements, int timeout) {

		ScriptHandler promptHandler = new ScriptHandler();
		promptHandler.setupExactMatch(searchElements);

		ScriptHandler moreHandler = new ScriptHandler();

		if (matchMoreTagList != null)
			moreHandler.setupExactMatch(matchMoreTagList);
		else
			moreHandler.setupExactMatch(defaultMatchMoreTagList);

		byte[] b1 = new byte[256];
		int n = 0;
		StringBuffer ret = new StringBuffer();
		String current;

		TimeoutThread t_thread = new TimeoutThread(timeout, socket);
		t_thread.start();

		while (n >= 0) {
			try {
				n = read(b1);
			} catch (IOException e) {
				e.printStackTrace();
				return ret.toString();
			}
			if (n > 0) {
				current = new String(b1, 0, n);
				ret.append(current);
				if (promptHandler.match(ret.toString())) {
					t_thread.terminate();
					return ret.toString();
				}

				if (moreHandler.match(ret.toString())) {
					try {
						sendChars(" ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // if
		} // while

		return null; // should never happen
	}

	public String waitfor(String match, int net_delay) throws IOException {
		String[] matches = new String[1];

		matches[0] = match;

		return waitfor(matches, net_delay);
	}

	/**
	 * 该方法仅使用于仿真终端模式
	 * 
	 */
	public void startReaderThread() {
		readThread = new TerminalReadThread(this);
		readThread.start();
	}

	/**
	 * 该方法仅使用于仿真终端模式
	 * 
	 */
	public void stopReaderThread() {
		if (readThread != null && readThread.isAlive())
			readThread.terminate();
	}

	/**
	 * Read data from the socket and use telnet negotiation before returning the
	 * data read.
	 * 
	 * @param b
	 *            the input buffer to read in
	 * @return the amount of bytes read
	 */
	public int read(byte[] b) throws IOException {
		return -1;
	}

	/**
	 * Write data to the socket.
	 * 
	 * @param b
	 *            the buffer to be written
	 */
	public void write(byte[] b) throws IOException {
		out.write(b);
	}

	public String getTerminalType() {
		return "dumb";
	}

	public Dimension getWindowSize() {
		return new Dimension(80, 24);
	}

	public void setLocalEcho(boolean echo) {
		if (debug > 0) {
			System.err.println("local echo " + (echo ? "on" : "off"));
		}
	}

	/**
	 * @param matchMoreTagList
	 *            the matchMoreTagList to set
	 */
	public void setMatchMoreTagList(String[] matchMoreTagList) {
		this.matchMoreTagList = matchMoreTagList;
	}

	/**
	 * @return the matchMoreTagList
	 */
	public String[] getMatchMoreTagList() {
		return matchMoreTagList;
	}

	public void setLoginConfirmPatternList(String[] confirmPatternList) {
		this.loginConfirmPatternList = confirmPatternList;
	}

	public String[] getLoginConfirmPatternList() {
		return loginConfirmPatternList;
	}

	/**
	 * 确认 login 是否成功
	 * 
	 * @return
	 */
	public boolean confirmLogin(String expect) {

		boolean isMatch = false;
		String[] patterns = null;
		if (loginConfirmPatternList != null
				&& loginConfirmPatternList.length > 0) {
			patterns = loginConfirmPatternList;
		} else
			patterns = defaultLoginConfirmPatternList;
		for (String pattern : patterns) {
			// System.err.printf("[I] pattern = %s , prompt = %s HEX string = "
			// , pattern , expect);
			// byte[] bb = expect.getBytes();
			// for ( int i = 0 ;i< bb.length ; i++)
			// System.err.printf("%x " ,bb[i]);
			// System.err.printf("\n" );

			Pattern p = Pattern.compile(pattern);
			if (p.matcher(expect).lookingAt())
				return true;
		}
		return isMatch;
	}

	class BastionQuitListener implements IDataReceive{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		public void onDataReceived(byte[] msg) {
			for (byte b:msg){
				if (b == 13){
					try{
						byte[] bs = out.toByteArray();
						if (bs.length == 0) continue;
						int start = 0, len = bs.length;
						if (bs[0] == 10){ 
							start = 1;
							len--;
						}
						if (bs[bs.length-1] == 10){
							len--;
						}
						String str = new String(bs, start, len);
//						System.out.println("[R]" + str);
						if (str != null){
							if (str.trim().equals(Bastion.BastionPromptLogin)){
								sendRawBytes((Bastion.ScriptCommandLogout + "\n").getBytes());
								disconnect();
								logger.info("检测到堡垒主机提示符，主动断开连接");
								return;
							}
						}
						out.reset();
					} catch(Exception e){
						logger.error("判断堡垒主机提示符失败, " + e.getMessage(), e);
					}
				} else {
					out.write(b);
				}
			}			
		}
		public void onDataReceived(byte[] cmd, byte[] msg) {
			
		}
	}
}
