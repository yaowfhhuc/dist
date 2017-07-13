package com.eastcom.ipnet.orders.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SqlUtil {

	private static final Logger log = Logger.getLogger(SqlUtil.class);

	private static Properties props;

	private static String path;

	static {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			path = System.getProperty("workHome") + "/WEB-INF/classes/sql.properties";
		} else {
			path = System.getProperty("workHome") + "/conf/sql.properties";
		}
		log.debug("同步数据PATH  :" + path);
//		path = "D:/sql.properties";
		props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (Exception e) {
		}
	}

	public static String getSQL(String key) {

		return props.getProperty(key);

	}

}
