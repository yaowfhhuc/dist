package orders.protocol.ssh2;

class RequestPtyReq implements Request {
	void setCode(String cookie) {
	}

	public void request(Session session, Channel channel) throws Exception {
		Buffer buf = new Buffer();
		Packet packet = new Packet(buf);

		packet.reset();
		buf.putByte((byte) Session.SSH_MSG_CHANNEL_REQUEST);
		buf.putInt(channel.getRecipient());
		buf.putString("pty-req".getBytes());
		buf.putByte((byte) (waitForReply() ? 1 : 0));
		buf.putString("vt100".getBytes());
		buf.putInt(80);
		buf.putInt(24);
		buf.putInt(640);
		buf.putInt(480);
		buf.putString("".getBytes());
		session.write(packet);
	}

	public boolean waitForReply() {
		return false;
	}
}
