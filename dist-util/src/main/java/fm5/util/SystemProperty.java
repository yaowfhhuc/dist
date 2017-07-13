package fm5.util;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

public class SystemProperty implements InitializingBean {

	private Properties properties;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void afterPropertiesSet() throws Exception {
		for (Enumeration<?> e = properties.propertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String value = properties.getProperty(key);
			System.setProperty(key, value);
		}
	}

}