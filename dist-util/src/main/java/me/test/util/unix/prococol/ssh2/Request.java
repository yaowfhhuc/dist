package me.test.util.unix.prococol.ssh2;

interface Request {
	boolean waitForReply();

	void request(Session session, Channel channel) throws Exception;
}
