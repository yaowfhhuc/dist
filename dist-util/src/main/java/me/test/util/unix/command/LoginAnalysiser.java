package me.test.util.unix.command;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.test.util.unix.plugin.IPlugin;
import me.test.util.unix.plugin.TerminalFactory;

public class LoginAnalysiser implements Analysiser{

	private static Logger logger = LoggerFactory.getLogger(LoginAnalysiser.class);
	
	private static LoginAnalysiser instance = null;
	
	private PasswordCryptMD5 md5 = new PasswordCryptMD5();
	
	private BlockingQueue<String> loginQueue = new ArrayBlockingQueue<>(10000);
	
	private ExecutorService loginService = Executors.newFixedThreadPool(20);
	
	public int getQueueSize() {
		return loginQueue.size();
	}
	
	public boolean isEnd(){
		return loginService.isShutdown();
	}
	
	public static LoginAnalysiser getInstance(int size) {
		if(instance==null){
			instance = new LoginAnalysiser(size);
		}
		return instance;
	}
	
	public LoginAnalysiser(int size) {
		for(int i=0; i<size;i++){
			loginService.submit(new Worker(i)) ;
		}
		try {
			this.md5.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化 MD5加密器失败！！！");
		}
	}
	@Override
	public void doAnalysis(String ip) throws Exception{
		loginQueue.put(ip);
	}
	
	private class Worker implements Runnable{
		StringBuilder stringBuilder = new StringBuilder();
		HashMap<String, Object> paramMap = new HashMap<>();
		int connectTimeout = 2000;
		int num = 0;
		public Worker(int num) {
			this.num = num;
			connectTimeout = Integer.parseInt(
					LoadConfigFile.getInstance().getProperties().getProperty("util.login.timeOut",connectTimeout+""));
		}
		@Override
		public void run() {
			logger.info(" {} worker thread {} start",logger.getName(),num);
			IPlugin terminal = null;
			while(true){
				stringBuilder.delete(0, stringBuilder.length());
				paramMap.clear();
				try {
					String ip = loginQueue.take();
					LineEntity line = MainManager.queue.get(ip);
					line.setLoginPassword(encode(line.getLoginPassword()));
					
					
					if(line.getTelnetType().equals(TELNETTYPE.TELNET.value())){
						TerminalFactory.createTerminal(3, 2, null);
					}else if(line.getTelnetType().equals(TELNETTYPE.SSH.value())) {
						terminal = TerminalFactory.createTerminal(1, 2, null);
						terminal.setLogin(line.getLoginId());
						terminal.setPassword(line.getLoginPassword());
						terminal.connect(line.getDeviceIp(), Integer.parseInt(line.getPort()),connectTimeout);
						boolean login_ok = terminal.standardLogin(paramMap);
						line.setLoginResult(login_ok?"T":"F");
					}else if(line.getTelnetType().equals(TELNETTYPE.SSH2.value())){
						terminal = TerminalFactory.createTerminal(2, 2, null);
						terminal.setLogin(line.getLoginId());
						terminal.setPassword(line.getLoginPassword());
						terminal.connect(line.getDeviceIp(), Integer.parseInt(line.getPort()),connectTimeout);
						boolean login_ok = terminal.standardLogin(paramMap);
						line.setLoginResult(login_ok?"T":"F");
					}
					MainManager.queue.put(ip, line);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		private String encode(String pass) {
			String ciphertext = null;
			if(pass==null){
				return null;
			}
			try {
				byte[] decode = md5.decode(pass);
				ciphertext = new String(decode);
			} catch (Exception e1) {
				e1.printStackTrace();
				ciphertext = "解密失败";
				return null;
			}
			return ciphertext;
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, Object> paramMap = new HashMap<>();
		IPlugin terminal = TerminalFactory.createTerminal(2, 2, null);
		terminal.setLogin("ipnet13693890060");
		terminal.setPassword("DED7587209D22E2A133D7DC429988C9E");
		terminal.connect("139.118.89.3",2,2000);
		boolean login_ok = terminal.standardLogin(paramMap);
		System.out.println(login_ok);
	}
}
