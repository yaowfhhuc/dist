package orders.protocol.ssh2.jce;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class SignatureRSA implements com.eastcom.ipnet.orders.protocol.ssh2.SignatureRSA {

	java.security.Signature signature;
	KeyFactory keyFactory;

	public void init() throws Exception {
		signature = java.security.Signature.getInstance("SHA1withRSA");
		keyFactory = KeyFactory.getInstance("RSA");
	}

	public void setPubKey(byte[] e, byte[] n) throws Exception {
		RSAPublicKeySpec rsaPubKeySpec = new RSAPublicKeySpec(
				new BigInteger(n), new BigInteger(e));
		PublicKey pubKey = keyFactory.generatePublic(rsaPubKeySpec);
		signature.initVerify(pubKey);
	}

	public void setPrvKey(byte[] d, byte[] n) throws Exception {
		RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(
				new BigInteger(n), new BigInteger(d));
		PrivateKey prvKey = keyFactory.generatePrivate(rsaPrivKeySpec);
		signature.initSign(prvKey);
	}

	public byte[] sign() throws Exception {
		byte[] sig = signature.sign();
		return sig;
	}

	public void update(byte[] foo) throws Exception {
		signature.update(foo);
	}

	public boolean verify(byte[] sig) throws Exception {
		int i = 0;
		int j = 0;
		byte[] tmp;

		if (sig[0] == 0 && sig[1] == 0 && sig[2] == 0) {
			j = ((sig[i++] << 24) & 0xff000000)
					| ((sig[i++] << 16) & 0x00ff0000)
					| ((sig[i++] << 8) & 0x0000ff00)
					| ((sig[i++]) & 0x000000ff);
			i += j;
			j = ((sig[i++] << 24) & 0xff000000)
					| ((sig[i++] << 16) & 0x00ff0000)
					| ((sig[i++] << 8) & 0x0000ff00)
					| ((sig[i++]) & 0x000000ff);
			tmp = new byte[j];
			System.arraycopy(sig, i, tmp, 0, j);
			sig = tmp;
		}
		// System.out.println("j="+j+" "+Integer.toHexString(sig[0]&0xff));
		return signature.verify(sig);
	}
}
