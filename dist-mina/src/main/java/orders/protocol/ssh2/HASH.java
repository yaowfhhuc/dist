package orders.protocol.ssh2;

public interface HASH {
	void init() throws Exception;

	int getBlockSize();

	void update(byte[] foo, int start, int len) throws Exception;

	byte[] digest() throws Exception;
}
