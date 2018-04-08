package orders.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextManager {

	private static String contextPath;
	
	private static ApplicationContextManager context;

	public static ApplicationContextManager getInstance() {
		return getInstance(null);
	}

	public synchronized static ApplicationContextManager getInstance(String path) {
		if (context == null) {
			if (contextPath == null) {
				contextPath = (path == null ? "applicationContext.xml" : path);
			} else if (path != null) {
				contextPath = path;
			}
			log.info("create applicationContext......(path=" + contextPath
					+ ")");
			context = new ApplicationContextManager();
		}

		return context;
	}

	private ApplicationContext applicationContext;

	private ApplicationContextManager() {
		if (contextPath.indexOf(":") != -1) {
			String type = contextPath.substring(0, contextPath.indexOf(":"));
			String path = contextPath.substring(contextPath.indexOf(":") + 1);
			if (type.equalsIgnoreCase("file")) {
				applicationContext = new FileSystemXmlApplicationContext(path);
			} else if (type.equalsIgnoreCase("class")) {
				applicationContext = new ClassPathXmlApplicationContext(path);
			} else {
				throw new IllegalStateException(
						"unsupported applicationContext type[" + type + "]");
			}
			return;
		}
		applicationContext = new ClassPathXmlApplicationContext(contextPath);
	}

	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	public Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	private static Log log = LogFactory.getLog(ApplicationContextManager.class);
}
