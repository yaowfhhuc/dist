package orders.protocol.ssh2;

public interface KeyPairGenDSA {
	void init(int key_size) throws Exception;

	byte[] getX();

	byte[] getY();

	byte[] getP();

	byte[] getQ();

	byte[] getG();
}
