package me.test.util.unix.prococol;

import java.io.IOException;
import java.net.Socket;

public class TimeoutThread extends Thread {

	private Object lock;

	private int waitTimeOut;

	private Socket socket = null;

	private boolean timeOutFlag = true;

	public TimeoutThread(int timeout, Socket socket) {
		lock = new Object();
		this.waitTimeOut = timeout;
		this.socket = socket;
	}

	public int getWaitTimeOut() {
		return waitTimeOut;
	}

	public void setWaitTimeOut(int timeout) {
		this.waitTimeOut = timeout;
	}

	public void run() {
		timeOutFlag = true;
		synchronized (lock) {
			try {
				lock.wait(waitTimeOut);

				if (timeOutFlag) {
//					System.out
//							.println("[I] Timeout thread terminate because time reached!");
					if (socket != null)
						socket.close();
					return;
				}
//				System.out
//						.println("[I] Timeout thread terminate because wrapper notify!");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void terminate() {
		timeOutFlag = false;
		synchronized (lock) {
			lock.notify();
		}
	}
}
