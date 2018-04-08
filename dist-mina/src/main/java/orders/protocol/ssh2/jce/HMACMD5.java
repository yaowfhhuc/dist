package orders.protocol.ssh2.jce;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.eastcom.ipnet.orders.protocol.ssh2.MAC;

public class HMACMD5 implements MAC {
	private static final String name = "hmac-md5";
	private static final int bsize = 16;
	private Mac mac;

	public int getBlockSize() {
		return bsize;
	};

	public void init(byte[] key) throws Exception {
		if (key.length > bsize) {
			byte[] tmp = new byte[bsize];
			System.arraycopy(key, 0, tmp, 0, bsize);
			key = tmp;
		}
		SecretKeySpec skey = new SecretKeySpec(key, "HmacMD5");
		mac = Mac.getInstance("HmacMD5");
		mac.init(skey);
	}

	private final byte[] tmp = new byte[4];

	public void update(int i) {
		tmp[0] = (byte) (i >>> 24);
		tmp[1] = (byte) (i >>> 16);
		tmp[2] = (byte) (i >>> 8);
		tmp[3] = (byte) i;
		update(tmp, 0, 4);
	}

	public void update(byte foo[], int s, int l) {
		mac.update(foo, s, l);
	}

	public byte[] doFinal() {
		return mac.doFinal();
	}

	public String getName() {
		return name;
	}
}
