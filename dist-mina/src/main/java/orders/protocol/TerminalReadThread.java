package orders.protocol;

import java.io.IOException;

import com.eastcom.ipnet.orders.plugin.IDataReceive;
import com.eastcom.ipnet.orders.plugin.util.Bastion;

public class TerminalReadThread extends Thread {

	private boolean running = true;

	private Wrapper wrapper = null;

	private IDataReceive listener = null;

	public TerminalReadThread(Wrapper wrapper) {
		this.wrapper = wrapper;
		listener = wrapper.getListener();
	}

	public void run() {

		byte[] b = new byte[256];
		int n = 0;
		while (running && n >= 0) {
			try {
				n = wrapper.read(b);
				if (n > 0) {
					byte[] send_buf = new byte[n];
					System.arraycopy(b, 0, send_buf, 0, n);
//					System.out.println("send to bastion " + Bastion.on + " " + wrapper.getBastionQuitListener() + " " + send_buf.length);
					if (Bastion.on){
						if (wrapper.getBastionQuitListener() != null){
							
							wrapper.getBastionQuitListener().onDataReceived(send_buf);
						}
					}
//					System.out.println("send to bastion " + Bastion.on + " " + listener + " " + send_buf.length);
					listener.onDataReceived(send_buf);
				}
			} catch (IOException e) {
				running = false;
				listener.onDataReceived("\n\t\t<<  Telnet代理连接异常终止  >>\n\n"
						.getBytes());
				return;

			}
		}
	}

	public void terminate() {
		running = false;
	}
}
