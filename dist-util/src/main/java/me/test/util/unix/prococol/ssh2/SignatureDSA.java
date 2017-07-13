package me.test.util.unix.prococol.ssh2;

public interface SignatureDSA {
	void init() throws Exception;

	void setPubKey(byte[] y, byte[] p, byte[] q, byte[] g) throws Exception;

	void setPrvKey(byte[] x, byte[] p, byte[] q, byte[] g) throws Exception;

	void update(byte[] H) throws Exception;

	boolean verify(byte[] sig) throws Exception;

	byte[] sign() throws Exception;
}
