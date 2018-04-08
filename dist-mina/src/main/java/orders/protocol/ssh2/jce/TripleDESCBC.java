package orders.protocol.ssh2.jce;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.eastcom.ipnet.orders.protocol.ssh2.Cipher;

public class TripleDESCBC implements Cipher {
	private static final int ivsize = 8;
	private static final int bsize = 24;
	private javax.crypto.Cipher cipher;

	public int getIVSize() {
		return ivsize;
	}

	public int getBlockSize() {
		return bsize;
	}

	public void init(int mode, byte[] key, byte[] iv) throws Exception {
		String pad = "NoPadding";
		// if(padding) pad="PKCS5Padding";
		byte[] tmp;
		if (iv.length > ivsize) {
			tmp = new byte[ivsize];
			System.arraycopy(iv, 0, tmp, 0, tmp.length);
			iv = tmp;
		}
		if (key.length > bsize) {
			tmp = new byte[bsize];
			System.arraycopy(key, 0, tmp, 0, tmp.length);
			key = tmp;
		}

		try {
			cipher = javax.crypto.Cipher.getInstance("DESede/CBC/" + pad);
			/*
			 * // The following code does not work on IBM's JDK 1.4.1
			 * SecretKeySpec skeySpec = new SecretKeySpec(key, "DESede");
			 * cipher.init((mode==ENCRYPT_MODE?
			 * javax.crypto.Cipher.ENCRYPT_MODE:
			 * javax.crypto.Cipher.DECRYPT_MODE), skeySpec, new
			 * IvParameterSpec(iv));
			 */
			DESedeKeySpec keyspec = new DESedeKeySpec(key);
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey _key = keyfactory.generateSecret(keyspec);
			cipher.init(
					(mode == ENCRYPT_MODE ? javax.crypto.Cipher.ENCRYPT_MODE
							: javax.crypto.Cipher.DECRYPT_MODE), _key,
					new IvParameterSpec(iv));
		} catch (Exception e) {
			System.out.println(e);
			cipher = null;
		}
	}

	public void update(byte[] foo, int s1, int len, byte[] bar, int s2)
			throws Exception {
		cipher.update(foo, s1, len, bar, s2);
	}
}
