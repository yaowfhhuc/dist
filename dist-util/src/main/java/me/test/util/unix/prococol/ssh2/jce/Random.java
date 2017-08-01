package me.test.util.unix.prococol.ssh2.jce;

import java.security.SecureRandom;

public class Random implements me.test.util.unix.prococol.ssh2.Random {
	private byte[] tmp = new byte[16];
	private SecureRandom random;

	public Random() {
		random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (java.security.NoSuchAlgorithmException e) {
			// System.out.println(e);

			// The following code is for IBM's JCE
			try {
				random = SecureRandom.getInstance("IBMSecureRandom");
			} catch (java.security.NoSuchAlgorithmException ee) {
				System.out.println(ee);
			}
		}
	}

	public void fill(byte[] foo, int start, int len) {
		if (len > tmp.length) {
			tmp = new byte[len];
		}
		random.nextBytes(tmp);
		System.arraycopy(tmp, 0, foo, start, len);
	}
}
