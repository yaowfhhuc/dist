package me.test.util.unix.ssl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordCryptUtil {
	private static PasswordCryptUtil self = null;
	
	public static PasswordCryptUtil getInstance(){
		if (self == null)
			self = new PasswordCryptUtil();
		return self;
	}
	
	private static int READY = 0;
	private static int UNINITED = 1;
	
	private int status = UNINITED;
	private int count = 20;
	private String keyString = "Joysiren!@#";
	private Cipher pbeCipher, deCipher;
	private byte[] salt = {
	        (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
	        (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
	    };
	private char[] hexChars;
	private static final String PREFIX = "!.!";
	
	private PasswordCryptUtil(){
//		 Create PBE parameter set
		try{
			PBEKeySpec pbeKeySpec;
			PBEParameterSpec pbeParamSpec;
			SecretKeyFactory keyFac;
			
		    pbeParamSpec = new PBEParameterSpec(salt, count);
		    pbeKeySpec = new PBEKeySpec(keyString.toCharArray());
		    
		    keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		    SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
	
		    // Create PBE Cipher
		    pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		    deCipher = Cipher.getInstance("PBEWithMD5AndDES");
		    // Initialize PBE Cipher with key and parameters
		    pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
		    deCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
		    
		    hexChars = "QDU!$JOY*%PMA@#V".toCharArray();
		    status = READY;
		}catch(Exception e){
			status = UNINITED;
		}
	}
	public String encode(String password) throws Exception{
		if (status == UNINITED) throw new Exception("加密器未初始化");
		if (password == null) throw new NullPointerException("传入空参数");
		if (password.startsWith(PREFIX)) return password;
		
	    byte[] cleartext = password.getBytes();
	    // Encrypt the cleartext
	    byte[] ciphertext = pbeCipher.doFinal(cleartext);
	    
//	    for (int i = 0; i < ciphertext.length; i++)
//	    	System.out.print((int)ciphertext[i] + ".");
	    return PREFIX + bytes2String(ciphertext);

	}
	
	public String decode(String password) throws Exception{
		if (status == UNINITED) throw new Exception("加密器未初始化");
		if (password == null) throw new NullPointerException("传入空参数");
		if (!password.startsWith(PREFIX)) return password;
		
	    byte[] cleartext = string2bytes(password.substring(PREFIX.length()));
//	    for (int i = 0; i < cleartext.length; i++)
//	    	System.out.print((int)cleartext[i] + ".");
	    // Encrypt the cleartext
	    byte[] ciphertext = deCipher.doFinal(cleartext);
	    
	    return new String(ciphertext);
	}
	
	private void byte2hex(byte b, StringBuffer buf) {
//        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
//                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//		char[] hexChars = { 'Q', 'D', 'J', '!', '$', 'R', 'Y', 'O', 'P',
//                'M', '*', '#', 'V', 'C', 'E', '@' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

	private String bytes2String(byte[] buffer){
		StringBuffer buf = new StringBuffer();

        int len = buffer.length;

        for (int i = 0; i < len; i++) {
             byte2hex(buffer[i], buf);
        } 
        return buf.toString();
	}
	
	private byte[] string2bytes(String word) throws Exception{
		byte[] ret = new byte[word.length()/2];
		for (int i = 0; i < ret.length*2; i+=2){
			char a = word.charAt(i);
			char b = word.charAt(i+1);
			
			int high = findChar(a);
			int low = findChar(b);
			if (high < 0 || low < 0) throw new Exception("[" + word + "]是非法密码串");
			
			ret[i/2] = (byte)(((high & 0xf) << 4) | (low & 0xf)); 
		}
		return ret;
	}
	
	private int findChar(char c){
		for (int j = 0; j < hexChars.length; j++){
			if (hexChars[j] == c)
				return j;
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.print("输入口令：");
		System.out.flush();
		int c;
		StringBuffer sb = new StringBuffer();
		
		while(true){
			switch(c = System.in.read()){
			case -1:
			case '\r':
			case '\n':
//				System.out.println("break" + c);
				c = -1;				
				break;
			default:
//				System.out.println(c);
				sb.append((char)c);
			}
			if (c == -1)
				break;
		}
		String enp = PasswordCryptUtil.getInstance().encode(sb.toString());
		System.out.println("待加密字符串：[" + sb.toString() + "]");
		System.out.println("加密后：" + enp);
		System.out.println("解密后：" + PasswordCryptUtil.getInstance().decode(enp));
		
	}
}
