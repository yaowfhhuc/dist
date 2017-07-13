package org.dist.util.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * Nio  实际只是业务处理实现了异步，   accept()的过程还是阻塞的
 * @author yaowf
 *
 */
public class SocketServerNioThreadTest {

	
	   private static Object xWait = new Object();

	    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServerNioThreadTest.class);

	    public static void main(String[] args) throws Exception{
	        ServerSocket serverSocket = new ServerSocket(83);
	        serverSocket.setSoTimeout(100);
	        try {
	            while(true) {
	                Socket socket = null;
	                try {
	                    socket = serverSocket.accept();
	                } catch(SocketTimeoutException e1) {
	                    //===========================================================
	                    //      执行到这里，说明本次accept没有接收到任何TCP连接
	                    //      主线程在这里就可以做一些事情，记为X
	                    //===========================================================
	                    synchronized (SocketServerNioThreadTest.xWait) {
	                    	SocketServerNioThreadTest.LOGGER.info("这次没有从底层接收到任何TCP连接，等待10毫秒，模拟事件X的处理时间");
	                    	SocketServerNioThreadTest.xWait.wait(10);
	                    }
	                    continue;
	                }
	                //当然业务处理过程可以交给一个线程（这里可以使用线程池）,并且线程的创建是很耗资源的。
	                //最终改变不了.accept()只能一个一个接受socket连接的情况
	                SocketServerNioThread socketServerThread = new SocketServerNioThread(socket);
	                new Thread(socketServerThread).start();
	            }
	        } catch(Exception e) {
	        	SocketServerNioThreadTest.LOGGER.error(e.getMessage(), e);
	        } finally {
	            if(serverSocket != null) {
	                serverSocket.close();
	            }
	        }
	    }
}
