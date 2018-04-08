package orders.protocol.ssh2;

public interface MAC {
	String getName();

	int getBlockSize();

	void init(byte[] key) throws Exception;

	void update(byte[] foo, int start, int len);

	void update(int foo);

	byte[] doFinal();
}
