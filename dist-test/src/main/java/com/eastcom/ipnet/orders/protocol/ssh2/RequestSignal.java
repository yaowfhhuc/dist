package com.eastcom.ipnet.orders.protocol.ssh2;

class RequestSignal implements Request {
	String signal = "KILL";

	public void setSignal(String foo) {
		signal = foo;
	}

	public void request(Session session, Channel channel) throws Exception {
		Buffer buf = new Buffer();
		Packet packet = new Packet(buf);

		packet.reset();
		buf.putByte((byte) Session.SSH_MSG_CHANNEL_REQUEST);
		buf.putInt(channel.getRecipient());
		buf.putString("signal".getBytes());
		buf.putByte((byte) (waitForReply() ? 1 : 0));
		buf.putString(signal.getBytes());
		session.write(packet);
	}

	public boolean waitForReply() {
		return false;
	}
}
