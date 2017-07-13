package com.eastcom.ipnet.orders.util;

public class ExceptionUtil {
	public static String getResourceMessage(Exception e) {
		Throwable t = e;
		while (t.getCause() != null)
			t = t.getCause();
		return t.getMessage();
	}
}
