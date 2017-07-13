package me.test.util.unix.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadConfigFile {

	private static Logger logger = LoggerFactory.getLogger(LoadConfigFile.class);
	
	private static LoadConfigFile instance = null;
	
	private Properties properties = new Properties();
	
	public static synchronized LoadConfigFile getInstance(){
		if(instance==null){
			instance = new LoadConfigFile();
		}
		return instance;
	}
	
	private LoadConfigFile(){
		String filePath = null;
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
			filePath = "./src/main/resources/linuxUtil.properties";
		else
			filePath = "../conf/linuxUtil.properties";
		
		try {
			properties.load(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("can not found file {} \t {}",filePath,e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("load file stream failed . {}",e.getMessage());
		}
		if(!properties.isEmpty()){
			logger.info(properties.toString());
		}
	}
	
	public Properties getProperties(){
		return properties;
	}
	
	
	public static void main(String[] args) {
		System.out.println(LoadConfigFile.getInstance().properties);
	}
}
