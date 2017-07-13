package me.test.util.unix.prococol.ssh2;

public interface KeyPairGenRSA {
	void init(int key_size) throws Exception;

	byte[] getD();

	byte[] getE();

	byte[] getN();

	byte[] getC();

	byte[] getEP();

	byte[] getEQ();

	byte[] getP();

	byte[] getQ();
}
