package me.test.util.unix.prococol.ssh2;

public interface Cipher {
	static int ENCRYPT_MODE = 0;
	static int DECRYPT_MODE = 1;

	int getIVSize();

	int getBlockSize();

	void init(int mode, byte[] key, byte[] iv) throws Exception;

	void update(byte[] foo, int s1, int len, byte[] bar, int s2)
			throws Exception;
}
