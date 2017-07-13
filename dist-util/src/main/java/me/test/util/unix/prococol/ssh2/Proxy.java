package me.test.util.unix.prococol.ssh2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface Proxy {
	void connect(SocketFactory socket_factory, String host, int port,
			int timeout) throws Exception;

	InputStream getInputStream();

	OutputStream getOutputStream();

	Socket getSocket();

	void close();
}
