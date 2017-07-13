package me.test.util.unix.command;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordCryptMD5 {
	private int count;
	private String keyString;
	private Cipher pbeCipher;
	private Cipher deCipher;
	private byte[] salt;
	private char[] hexChars;

	public PasswordCryptMD5() {
		this.count = 20;
		this.keyString = "Joysiren!@#";

		this.salt = new byte[]{-57, 115, 33, -116, 126, -56, -18, -103};
	}

	public byte[] decode(String text) throws Exception {
		return decode("", "", text);
	}

	public byte[] decode(String deviceName, String accountId, String text) throws Exception {
		if (text == null)
			throw new NullPointerException("传入空参数");

		byte[] cleartext = string2bytes(text);

		byte[] ciphertext = this.deCipher.doFinal(cleartext);

		return ciphertext;
	}

	public String encode(byte[] text) throws Exception {
		return encode("", "", text);
	}

	public String encode(String deviceName, String accountId, byte[] text) throws Exception {
		if (text == null)
			throw new NullPointerException("传入空参数");

		byte[] ciphertext = this.pbeCipher.doFinal(text);

		return bytes2String(ciphertext);
	}

	public void init() throws Exception {
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(this.salt, this.count);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(this.keyString.toCharArray());

		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

		this.pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		this.deCipher = Cipher.getInstance("PBEWithMD5AndDES");

		this.pbeCipher.init(1, pbeKey, pbeParamSpec);
		this.deCipher.init(2, pbeKey, pbeParamSpec);

		this.hexChars = "890A1B2C3D4E5F67".toCharArray();
	}

	private void byte2hex(byte b, StringBuffer buf) {
		int high = (b & 0xF0) >> 4;
		int low = b & 0xF;
		buf.append(this.hexChars[high]);
		buf.append(this.hexChars[low]);
	}

	private String bytes2String(byte[] buffer) {
		StringBuffer buf = new StringBuffer();

		int len = buffer.length;

		for (int i = 0; i < len; ++i) {
			byte2hex(buffer[i], buf);
		}
		return buf.toString();
	}

	private byte[] string2bytes(String word) throws Exception {
		byte[] ret = new byte[word.length() / 2];
		for (int i = 0; i < ret.length * 2; i += 2) {
			char a = word.charAt(i);
			char b = word.charAt(i + 1);

			int high = findChar(a);
			int low = findChar(b);
			if ((high < 0) || (low < 0))
				throw new Exception("[" + word + "]是非法密码串");

			ret[(i / 2)] = (byte) ((high & 0xF) << 4 | low & 0xF);
		}
		return ret;
	}

	private int findChar(char c) {
		for (int j = 0; j < this.hexChars.length; ++j) {
			if (this.hexChars[j] == c)
				return j;
		}
		return -1;
	}
}
