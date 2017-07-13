package fm5.datatask.idc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:idcsftp.xml");
		
		ctx.registerShutdownHook();
	}
}
