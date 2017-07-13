package com.eastcom.ipnet.orders.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static MD5 self = null;
	private MessageDigest md;
	
	private MD5(){
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}	    
	}
	
	public static MD5 getInstance(){
		if (self == null)
			self = new MD5();
		return self;	
	}
	
	public String getMd5Code(byte[] buffer){
		md.update(buffer);
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest()) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(MD5.getInstance().getMd5Code("admin".getBytes()));
	}
}
