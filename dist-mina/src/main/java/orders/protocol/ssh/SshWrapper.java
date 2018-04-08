package orders.protocol.ssh;

import java.awt.Dimension;
import java.io.IOException;
import java.util.StringTokenizer;

import com.eastcom.ipnet.orders.message.Instruction;
import com.eastcom.ipnet.orders.protocol.Wrapper;

public class SshWrapper extends Wrapper {
	private SshIO handler;

	/** debugging level */
	private final static int debug = 0;

	public SshWrapper() {
		super();

		handler = new SshIO() {
			/** get the current wrapper type */
			public String getTerminalType() {
				// return "vt320";
				return "vt100";
			}

			/** get the current window size */
			@SuppressWarnings("unused")
			public Dimension getWindowSize() {
				// return new Dimension(80, 25);
				return new Dimension(132, 40);
			}

			/** notify about local echo */
			@SuppressWarnings("unused")
			public void setLocalEcho(boolean echo) {
				/* EMPTY */
			}

			/** write data to our back end */
			public void write(byte[] b) throws IOException {
				out.write(b);
			}
		};
	}
	
	
	public void setLogin(String user) {
		this.user = user;
		handler.setLogin(user);
	}

	public void setPassword(String passwd) {
		this.password = passwd;
		handler.setPassword(passwd);
	}
	

	/**
	 * Send a command to the remote host. A newline is appended and if a prompt
	 * is set it will return the resulting data until the prompt is encountered.
	 * 
	 * @param cmd
	 *            the command
	 * @return output of the command or null if no prompt is set
	 */
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
		handler.sendData(new String(arr));
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
		handler.sendData(new String(arr));
		return;
	}

	/**
	 * �ȴ�timeout�����ʱ��,��ȡshell��ʾ��
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
		handler.sendData(cmd);

	}

	public void sendRawBytes(byte[] content) throws IOException {
		handler.sendData(new String(content));
	}

	public String sendCmd(Instruction instruction) throws IOException {
		// TODO Auto-generated method stub
		String cmd = instruction.getCommand();
		String cmd_prompt = instruction.getPrompt();
		int cmd_net_delay = instruction.getNet_delay() * 1000;

		byte[] arr;
		arr = (cmd + "\n").getBytes();

		handler.sendData(new String(arr));

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
	 * �÷��������ڷ����ն˵������
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
		handler.sendData(new String(arr));
		return;
	}

	/** Buffer for SSH input */
	private byte[] buffer;

	/** Position in SSH input buffer */
	private int pos;

	/**
	 * Read data from the backend and decrypt it. This is a buffering read as
	 * the encrypted information is usually smaller than its decrypted pendant.
	 * So it will not read from the backend as long as there is data in the
	 * buffer.
	 * 
	 * @param b
	 *            the buffer where to read the decrypted data in
	 * @return the amount of bytes actually read.
	 */
	public int read(byte[] b) throws IOException {
		// Empty the buffer before we do anything else
		if (buffer != null) {
			int amount = ((buffer.length - pos) <= b.length) ? buffer.length
					- pos : b.length;
			System.arraycopy(buffer, pos, b, 0, amount);
			if (pos + amount < buffer.length) {
				pos += amount;
			} else
				buffer = null;
			return amount;
		}

		// now that the buffer is empty let's read more data and decrypt it
		int n = in.read(b);
		if (n > 0) {
			byte[] tmp = new byte[n];
			System.arraycopy(b, 0, tmp, 0, n);
			pos = 0;
			buffer = handler.handleSSH(tmp);
			if (debug > 0 && buffer != null && buffer.length > 0)
				System.err.println("ssh: " + buffer);

			if (buffer != null && buffer.length > 0) {
				if (debug > 0)
					System.err.println("ssh: incoming=" + n + " now="
							+ buffer.length);
				int amount = buffer.length <= b.length ? buffer.length
						: b.length;
				System.arraycopy(buffer, 0, b, 0, amount);
				pos = n = amount;
				if (amount == buffer.length) {
					buffer = null;
					pos = 0;
				}
			} else
				return 0;
		}
		return n;
	}
}
