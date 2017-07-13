package com.eastcom.ipnet.orders.util;

public interface PersistentCacher {
	public void put(String key, Object obj) throws Exception;
	public Object get(String key) throws Exception;
	
	public void init() throws Exception;
}
