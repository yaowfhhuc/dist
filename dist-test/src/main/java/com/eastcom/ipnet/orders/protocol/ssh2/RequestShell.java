package com.eastcom.ipnet.orders.protocol.ssh2;

class RequestShell implements Request {
	public void request(Session session, Channel channel) throws Exception {
		Buffer buf = new Buffer();
		Packet packet = new Packet(buf);

		// send
		// byte SSH_MSG_CHANNEL_REQUEST(98)
		// uint32 recipient channel
		// string request type // "shell"
		// boolean want reply // 0
		packet.reset();
		buf.putByte((byte) Session.SSH_MSG_CHANNEL_REQUEST);
		buf.putInt(channel.getRecipient());
		buf.putString("shell".getBytes());
		buf.putByte((byte) (waitForReply() ? 1 : 0));
		session.write(packet);
	}

	public boolean waitForReply() {
		return false;
	}
}
