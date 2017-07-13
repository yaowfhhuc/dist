package me.test.util.unix.prococol.ssh2;

class RequestEnv implements Request {
	String name = null;
	String value = null;

	void setEnv(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public void request(Session session, Channel channel) throws Exception {
		Buffer buf = new Buffer();
		Packet packet = new Packet(buf);

		packet.reset();
		buf.putByte((byte) Session.SSH_MSG_CHANNEL_REQUEST);
		buf.putInt(channel.getRecipient());
		buf.putString("env".getBytes());
		buf.putByte((byte) (waitForReply() ? 1 : 0));
		buf.putString(name.getBytes());
		buf.putString(value.getBytes());
		session.write(packet);
	}

	public boolean waitForReply() {
		return false;
	}
}
