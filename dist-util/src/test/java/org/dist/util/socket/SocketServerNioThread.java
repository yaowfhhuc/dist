package org.dist.util.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.LoggerFactory;

public class SocketServerNioThread implements Runnable{
    
	private static final org.slf4j.Logger LOGGER= LoggerFactory.getLogger(SocketServerNioThread.class);


	    private Socket socket;

	    public SocketServerNioThread (Socket socket) {
	        this.socket = socket;
	    }

	    @Override
	    public void run() {
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	            in = socket.getInputStream();
	            out = socket.getOutputStream();
	            Integer sourcePort = socket.getPort();
	            int maxLen = 2048;
	            byte[] contextBytes = new byte[maxLen];
	            int realLen;
	            StringBuffer message = new StringBuffer();
	            //下面我们收取信息（设置成非阻塞方式，这样read信息的时候，又可以做一些其他事情）
	            this.socket.setSoTimeout(10);
	            BIORead:while(true) {
	                try {
	                    while((realLen = in.read(contextBytes, 0, maxLen)) != -1) {
	                        message.append(new String(contextBytes , 0 , realLen));
	                        /*
	                         * 我们假设读取到“over”关键字，
	                         * 表示客户端的所有信息在经过若干次传送后，完成
	                         * */
	                        if(message.indexOf("over") != -1) {
	                            break BIORead;
	                        }
	                    }
	                } catch(SocketTimeoutException e2) {
	                    //===========================================================
	                    //      执行到这里，说明本次read没有接收到任何数据流
	                    //      主线程在这里又可以做一些事情，记为Y
	                    //===========================================================
	                    LOGGER.info("这次没有从底层接收到任务数据报文，等待10毫秒，模拟事件Y的处理时间");
	                    continue;
	                }
	            }
	            //下面打印信息
	            Long threadId = Thread.currentThread().getId();
	            LOGGER.info("服务器(线程：" + threadId + ")收到来自于端口：" + sourcePort + "的信息：" + message);

	            //下面开始发送信息
	            out.write("回发响应信息！".getBytes());

	            //关闭
	            out.close();
	            in.close();
	            this.socket.close();
	        } catch(Exception e) {
	        	LOGGER.error(e.getMessage(), e);
	        }
	    }
}
