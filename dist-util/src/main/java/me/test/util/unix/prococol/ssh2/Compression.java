package me.test.util.unix.prococol.ssh2;

public interface Compression {
	static public final int INFLATER = 0;
	static public final int DEFLATER = 1;

	void init(int type, int level);

	int compress(byte[] buf, int start, int len);

	byte[] uncompress(byte[] buf, int start, int[] len);
}
