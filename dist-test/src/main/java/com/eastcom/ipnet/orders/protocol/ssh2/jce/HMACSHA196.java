package com.eastcom.ipnet.orders.protocol.ssh2.jce;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.eastcom.ipnet.orders.protocol.ssh2.MAC;

public class HMACSHA196 implements MAC {
	private static final String name = "hmac-sha1-96";
	private static final int bsize = 12;
	private Mac mac;

	public int getBlockSize() {
		return bsize;
	};

	public void init(byte[] key) throws Exception {
		if (key.length > 20) {
			byte[] tmp = new byte[20];
			System.arraycopy(key, 0, tmp, 0, 20);
			key = tmp;
		}
		SecretKeySpec skey = new SecretKeySpec(key, "HmacSHA1");
		mac = Mac.getInstance("HmacSHA1");
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

	private final byte[] buf = new byte[12];

	public byte[] doFinal() {
		System.arraycopy(mac.doFinal(), 0, buf, 0, 12);
		return buf;
	}

	public String getName() {
		return name;
	}
}
