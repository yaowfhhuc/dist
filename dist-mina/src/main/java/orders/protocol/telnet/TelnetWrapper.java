/*
 * This file is part of "JTA - Telnet/SSH for the JAVA(tm) platform".
 *
 * (c) Matthias L. Jugel, Marcus Mei脽ner 1996-2005. All Rights Reserved.
 *
 * Please visit http://javatelnet.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * --LICENSE NOTICE--
 *
 */
package orders.protocol.telnet;

import java.awt.Dimension;
import java.io.IOException;
import java.util.StringTokenizer;

import com.eastcom.ipnet.orders.message.Instruction;
import com.eastcom.ipnet.orders.protocol.Wrapper;

/**
 * The telnet wrapper is a sample class for how to use the telnet protocol
 * handler of the JTA source package. To write a program using the wrapper you
 * may use the following piece of code as an example:
 * 
 * <PRE>
 * 
 * TelnetWrapper telnet = new TelnetWrapper();
 * try {
 * 	telnet.connect(args[0], 23);
 * 	telnet.login(&quot;user&quot;, &quot;password&quot;);
 * 	telnet.setPrompt(&quot;user@host&quot;);
 * 	telnet.waitfor(&quot;Terminal type?&quot;);
 * 	telnet.send(&quot;dumb&quot;);
 * 	System.out.println(telnet.send(&quot;ls -l&quot;));
 * } catch (java.io.IOException e) {
 * 	e.printStackTrace();
 * }
 * 
 * </PRE>
 * 
 * Please keep in mind that the password is visible for anyone who can download
 * the class file. So use this only for public accounts or if you are absolutely
 * sure nobody can see the file.
 * <P>
 * <B>Maintainer:</B> Matthias L. Jugel
 * 
 */
public class TelnetWrapper extends Wrapper {
	/** debugging level */
	@SuppressWarnings("unused")
	private final static int debug = 0;

	protected TelnetProtocolHandler handler;

	public void sendChars(String cmd) {
		// TODO Auto-generated method stub
		byte[] arr;
		arr = cmd.getBytes();
		try {
			handler.transpose(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TelnetWrapper() {
		super();
		handler = new TelnetProtocolHandler() {
			/** get the current wrapper type */
			public String getTerminalType() {
				// return "vt320";
				return "vt100";

			}

			/** get the current window size */
			public Dimension getWindowSize() {
				// return new Dimension(80, 25);
				return new Dimension(132, 40);
			}

			/** notify about local echo */
			public void setLocalEcho(boolean echo) {
				/* EMPTY */
			}

			/** write data to our back end */
			public void write(byte[] b) throws IOException {
				out.write(b);
			}

			/** sent on IAC EOR (prompt terminator for remote access systems). */
			public void notifyEndOfRecord() {
			}
		};
	}

	public void connect(String host, int port, int netDelay) throws Exception {
		this.setNetDelay(netDelay * 1000);
		super.connect(host, port, netDelay);
		handler.reset();
	}

	public String send(String command) throws IOException {
		// TODO Auto-generated method stub
		byte[] arr;
		arr = (command + "\n").getBytes();
		handler.transpose(arr);

		// if (getPrompt() != null) {
		// return waitfor(getPrompt(), netDelay);
		// }

		return null;
	}

	public void sendRawBytes(byte[] content) throws IOException {
		handler.transpose(content);
	}

	public String sendCmd(Instruction instruction) throws IOException {

		String cmd = instruction.getCommand();
		String cmd_prompt = instruction.getPrompt();
		int cmd_net_delay = instruction.getNet_delay() * 1000;

		byte[] arr;
		arr = (cmd + "\n").getBytes();
		handler.transpose(arr);
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
			arr = (striped + "\n").getBytes();
		} else if (cmd.endsWith("\r")) {
			striped = cmd.substring(0, cmd.lastIndexOf("\r"));
			arr = (striped + "\n").getBytes();
		} else if (cmd.endsWith("\n")) {
			striped = cmd.substring(0, cmd.lastIndexOf("\n"));
			arr = (striped + "\n").getBytes();
		} else {
			striped = cmd;
			arr = striped.getBytes();
		}
		handler.transpose(arr);
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
			n = in.read(b);
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

	/**
	 * Read data from the socket and use telnet negotiation before returning the
	 * data read.
	 * 
	 * @param b
	 *            the input buffer to read in
	 * @return the amount of bytes read
	 */
	public int read(byte[] b) throws IOException {
		/* process all already read bytes */
		int n;

		do {
			n = handler.negotiate(b);
			if (n > 0) {
				return n;
			}
		} while (n == 0);

		while (n <= 0) {
			do {
				n = handler.negotiate(b);
				if (n > 0) {
					// if (listener != null) {
					// byte[] send_buf = new byte[n];
					// System.arraycopy(b, 0, send_buf, 0, n);
					// listener.onDataReceived(send_buf);
					// // System.out.println(new String(send_buf));
					// }
					return n;
				}
			} while (n == 0);

			n = in.read(b);
			if (n < 0) {
				return n;
			}

			handler.inputfeed(b, n);
			n = handler.negotiate(b);
		}
		return n;
	}
}
