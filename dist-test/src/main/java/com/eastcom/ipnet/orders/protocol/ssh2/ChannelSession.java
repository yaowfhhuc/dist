package com.eastcom.ipnet.orders.protocol.ssh2;


class ChannelSession extends Channel {
	private static byte[] _session = "session".getBytes();

	ChannelSession() {
		super();
		type = _session;
		io = new IO();
	}

	public void run() {
		// System.out.println(this+":run >");
		/*
		 * if(thread!=null){ return; } thread=Thread.currentThread();
		 */

		// Buffer buf=new Buffer();
		Buffer buf = new Buffer(rmpsize);
		Packet packet = new Packet(buf);
		int i = -1;
		try {
			while (isConnected() && thread != null && io != null
					&& io.in != null) {
				i = io.in.read(buf.buffer, 14, buf.buffer.length - 14 - 32 - 20 // padding
																				// and
																				// mac
						);
				if (i == 0)
					continue;
				if (i == -1) {
					eof();
					break;
				}
				if (close)
					break;
				packet.reset();
				buf.putByte((byte) Session.SSH_MSG_CHANNEL_DATA);
				buf.putInt(recipient);
				buf.putInt(i);
				buf.skip(i);
				session.write(packet, this, i);
			}
		} catch (Exception e) {
			// System.out.println("# ChannelExec.run");
			// e.printStackTrace();
		}
		if (thread != null) {
			synchronized (thread) {
				thread.notifyAll();
			}
		}
		thread = null;
		// System.out.println(this+":run <");
	}
}
