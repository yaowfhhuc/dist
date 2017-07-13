package com.eastcom.ipnet.orders.util;

public class PasswordCryptUtil {
	private static PasswordCryptUtil self = null;

	public static String PASSWORD_CRYPT_CLASS = "com.eastcom.ipnet.orders.util.PasswordCryptMD5";

	public static PasswordCryptUtil getInstance() {
		if (self == null)
			try {
				init();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return self;
	}

	private static final int READY = 0;

	private static final int UNINITED = 1;

	private int status = UNINITED;

	private PasswordCrypt crypt = null;

	private PasswordCryptUtil() {

	}

	public static void init() throws Exception {
		self = new PasswordCryptUtil();
		self.crypt = (PasswordCrypt) Class.forName(PASSWORD_CRYPT_CLASS)
				.newInstance();
		self.crypt.init();
		self.status = READY;
	}

	public String ENCODE(String deviceName, String accountId, String password)
			throws Exception {
		return encode(deviceName, accountId, (password == null ? "" : password)
				.getBytes());
	}

	public String encode(String deviceName, String accountId, byte[] text)
			throws Exception {
		if (status == UNINITED)
			throw new Exception("加密器未初始化");

		return crypt.encode(deviceName, accountId, text);
	}

	public String DECODE(String deviceName, String accountId, String password)
			throws Exception {
		return new String(decode(deviceName, accountId, password));
	}

	public byte[] decode(String deviceName, String accountId, String text)
			throws Exception {
		if (status == UNINITED)
			throw new Exception("加密器未初始化");
		return crypt.decode(deviceName, accountId, text);
	}

	public static void main(String[] args) throws Exception {
		PasswordCryptUtil.init();

		String string = PasswordCryptUtil.getInstance().ENCODE("dad", "daffdf", "Znxj123!");
		System.out.println(string);
		String pwd = FakeDataMaker.getSystemIn("输入密码串", string);
		System.out.println(new String(PasswordCryptUtil.getInstance().decode(
				null, null, pwd)));
		
	}
}
