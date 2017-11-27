package fm5.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 这个类是要注入到Spring里面的。作为JMX的代理类。
 * 
 * @author HuangJie
 * 
 */
public class ApplicationServer implements ApplicationServerMBean {

	final static Logger logger = LoggerFactory
			.getLogger(ApplicationServer.class);

	private AbstractApplicationContext applicationContext;

	private JMXConnectorServer jmxConnectorServer;

	public AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public JMXConnectorServer getJmxConnectorServer() {
		return jmxConnectorServer;
	}

	public void setApplicationContext(
			AbstractApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setJmxConnectorServer(JMXConnectorServer jmxConnectorServer) {
		this.jmxConnectorServer = jmxConnectorServer;
	}

	@Override
	public void close() throws IOException {
		logger.info("调用ApplicationContext close命令.");
		applicationContext.close();
		logger.info("调用ApplicationContext close命令完成.");
		logger.info("调用JMXConnectorServer stop命令.");
		jmxConnectorServer.stop();
		logger.info("调用JMXConnectorServer stop命令完成.");
	}

	/**
	 * *************************************************************************
	 * 下面的方法为命令行调用的静态方法。
	 * *************************************************************************
	 */
	public static void main(String[] args) {
		logger.info("输入命令  {}", Arrays.toString(args));
		Options options = new Options();
		options.addOption("start", false, "启动服务");
		options.addOption("stop", false, "停止服务");
		CommandLineParser parser = new GnuParser();
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("start")) {
				startCommand();
			} else if (line.hasOption("stop")) {
				stopCommand();
				System.exit(1);
			} else {
				helpCommand(options);
				System.exit(1);
			}
		} catch (Exception e) {
			logger.info("执行命令异常", e);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static int getJMXPort() {
		try {
			String port = System.getProperty("jmx.port");
			return Integer.parseInt(port);
		} catch (NumberFormatException e) {
		}
		return 0;
	}

	public static JMXServiceURL getJMXServiceURL(int port)
			throws MalformedURLException {
		String url = "service:jmx:rmi:///jndi/rmi://localhost:" + port
				+ "/server";
		logger.info("JMXServiceURL: {}.", url);
		return new JMXServiceURL(url);
	}

	public static ObjectName getApplicationObjectName()
			throws MalformedObjectNameException, NullPointerException {
		String objectName = System.getProperty("jmx.object.name",
				"fm5:name=ApplicationServer");
		ObjectName mbeanName = new ObjectName(objectName);
		return mbeanName;
	}

	public static void startCommand() throws Exception {
		int port = getJMXPort();
		if (port <= 0) {
			logger.info("JMX管理功能未启用.");
		}
		MBeanServer mbs = null;
		JMXConnectorServer cs = null;
		if (port > 0) {
			logger.info("JMX注册端口: {}.", port);
			LocateRegistry.createRegistry(port);
			logger.info("JMX注册端口: {}成功.", port);
			logger.info("创建PlatformMBeanServer.");
			mbs = java.lang.management.ManagementFactory
					.getPlatformMBeanServer();
			JMXServiceURL url = getJMXServiceURL(port);
			logger.info("创建JMXConnectorServer.");
			cs = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, mbs);
			cs.start();
			logger.info("JMXConnectorServer启动成功.");
		}
		String configXmlFile = System.getProperty("Config");
		logger.info("创建ApplicationContext: {}.", configXmlFile);
		AbstractApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				configXmlFile);
		if (port > 0) {
			logger.info("注册ApplicationServer.");
			ObjectName mbeanName = getApplicationObjectName();
			ApplicationServer applicationServer = new ApplicationServer();
			applicationServer.setApplicationContext(applicationContext);
			applicationServer.setJmxConnectorServer(cs);
			mbs.registerMBean(applicationServer, mbeanName);
		}
	}

	public static void stopCommand() throws Exception {
		logger.info("执行stop命令.");
		int port = getJMXPort();
		JMXServiceURL url = getJMXServiceURL(port);
		logger.info("连接JMX serviceUrl: {}.", url);
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		ObjectName mbeanName = getApplicationObjectName();
		ApplicationServerMBean mbean = MBeanServerInvocationHandler
				.newProxyInstance(mbsc, mbeanName,
						ApplicationServerMBean.class, true);
		mbean.close();
		logger.info("执行stop命令完成.");
	}

	public static void helpCommand(Options options) {
		StringWriter sw = new StringWriter();
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("FaultManager", options);
		formatter.printUsage(new PrintWriter(sw), 100, "FaultManager", options);
		try {
			sw.close();
		} catch (IOException e) {
		}
	}
}
