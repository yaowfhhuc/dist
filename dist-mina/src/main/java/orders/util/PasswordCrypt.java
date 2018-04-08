package orders.util;

public interface PasswordCrypt {
	public void init() throws Exception;
	public String encode(String deviceName, String accountId, byte[] text) throws Exception;
	public byte[] decode(String deviceName, String accountId, String text) throws Exception;
}
