package me.test.util.unix;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Bastion {
	private static Log logger = LogFactory.getLog(Bastion.class);

	private static Properties bastionconf = new Properties();
	static{
//		System.setProperty("workHome", ".");
		File conf = new File(System.getProperty("workHome")
				+ "/conf/bastion.conf");
		System.out.println(conf);
		if (conf.exists()){
			try{
				FileInputStream in = new FileInputStream(conf);
				bastionconf.load(in);
				in.close();
			} catch(Exception e){
				logger.error("[E]bastion configure file read failure", e);
				bastionconf.put("startup", "off");
			}
		} else {
			bastionconf.put("startup", "off");
		}
		init(bastionconf);
	}
	
	public static String BastionHost, BastionProtocol, BastionUser, BastionPassword
		, BastionPromptUser, BastionPromptPassword, BastionPromptLogin
		, ScriptCommandTelnet, ScriptCommandSsh, ScriptCommandSshPrompt
		, ScriptCommandSshPromptFail, Startup, ScriptCommandLogout;
	public static int BastionPort, ScriptCommandTimeout;
	public static boolean on;
	
	public static void init(Properties p){
		for (Enumeration<?> e = p.keys(); e.hasMoreElements();){
			String key = (String)e.nextElement();
			String value = p.getProperty(key);
			
			String[] var = key.split("\\.");
			StringBuffer sb = new StringBuffer();
			for (String str:var){
				sb.append(str.substring(0, 1).toUpperCase())
					.append(str.substring(1));
			}
//			System.out.println(sb + " = " + value);
			
			try{
				Field f = Bastion.class.getField(sb.toString());
				if (f.getType() == int.class){
					f.set(null, Integer.parseInt(value));
				} else {
					f.set(null, value == null?"":value.trim());
				}
			} catch (Exception ex){				
				logger.error("[E]Parameter set error " + key + " = " + value, ex);
			}
		}
		if (Startup != null && Startup.equalsIgnoreCase("on"))
			on = true;
		else
			on = false;
	}
	
}
