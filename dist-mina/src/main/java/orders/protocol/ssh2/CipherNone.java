package orders.protocol.ssh2;

public class CipherNone implements Cipher {
	private static final int ivsize = 8;
	private static final int bsize = 16;

	public int getIVSize() {
		return ivsize;
	}

	public int getBlockSize() {
		return bsize;
	}

	public void init(int mode, byte[] key, byte[] iv) throws Exception {
	}

	public void update(byte[] foo, int s1, int len, byte[] bar, int s2)
			throws Exception {
	}
}
