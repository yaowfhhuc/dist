package com.eastcom.ipnet.orders.util;

import java.io.ByteArrayOutputStream;

public class FakeDataMaker {
	public static String getSystemIn(String msg, String defaultvalue){
		System.out.print(msg + "[" + defaultvalue + "]:");
		String ret = null;
		try{
			int b;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			while((b = System.in.read()) != -1){
				if (b == 0x0D) continue;
				if (b == 0x0D || b == 0x0A) break;
				bout.write(b);
			}
			bout.close();
			ret = new String(bout.toByteArray());
		}catch(Exception e){
			e.printStackTrace();
			ret = "";
		}
		if (ret.trim().equals("")) ret = defaultvalue;
		return ret;
	}
}
