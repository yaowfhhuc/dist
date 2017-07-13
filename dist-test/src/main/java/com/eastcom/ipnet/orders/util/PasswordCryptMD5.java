package com.eastcom.ipnet.orders.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordCryptMD5 implements PasswordCrypt{
	private int count = 20;
	private String keyString = "Joysiren!@#";
	private Cipher pbeCipher, deCipher;
	private byte[] salt = {
	        (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
	        (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
	    };
	private char[] hexChars;
	
	public byte[] decode(String deviceName, String accountId, String text) throws Exception {
		if (text == null) throw new NullPointerException("传入空参数");
		
	    byte[] cleartext = string2bytes(text);

	    byte[] ciphertext = deCipher.doFinal(cleartext);
	    
	    return ciphertext;
	}

	public String encode(String deviceName, String accountId, byte[] text) throws Exception {
		if (text == null) throw new NullPointerException("传入空参数");
			    
	    byte[] ciphertext = pbeCipher.doFinal(text);
	    
	    return bytes2String(ciphertext);
	}

	public void init() throws Exception {		
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
		    
		hexChars = "890A1B2C3D4E5F67".toCharArray();		    
	}

	private void byte2hex(byte b, StringBuffer buf) {
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
}

