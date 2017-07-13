package me.test.util.unix.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TracerouteAnalysiser implements Analysiser{

	private static Logger logger = LoggerFactory.getLogger(TracerouteAnalysiser.class);

	private static TracerouteAnalysiser instance = null;
	
	private BlockingQueue<String> traceRouteQueue = new ArrayBlockingQueue<>(10000);
	
	private ExecutorService traceRouteService = Executors.newFixedThreadPool(20);
	
	public int getQueueSize() {
		return traceRouteQueue.size();
	}
	
	public boolean isEnd(){
		return traceRouteService.isShutdown();
	}
	
	private TracerouteAnalysiser(int size){
		for(int i=0; i<size;i++){
			traceRouteService.submit(new Worker(i)) ;
		}
	}
	
	public static TracerouteAnalysiser getInstance(int size){
		if(instance == null){
			instance = new TracerouteAnalysiser(size);
		}
		return instance;
	}
	
	private class Worker implements Runnable{
		
		StringBuilder stringBuilder = new StringBuilder();
		int num = 0 ;
		public Worker(int i ){
			this.num=i;
		}
		
		public void run() {
			logger.info("{} worker thread-{}  start",logger.getName(),num);
			String command = null;
			while(true){
				stringBuilder.delete(0, stringBuilder.length());
				try {
					String ip = traceRouteQueue.take();
					LineEntity line = MainManager.queue.get(ip);
					command = LoadEntity.properties.getProperty("util.command.type",//"ping")
								System.getProperty("os.name").toLowerCase().contains("windows")?"tracert":"traceroute")
						+"  "+line.getDeviceIp();
					Process process = Runtime.getRuntime().exec(command);
					process.waitFor();
					BufferedReader ibr = new BufferedReader(new InputStreamReader(process.getInputStream(),"GB2312"));
					String tmpStr = null;
					while((tmpStr=ibr.readLine())!=null ){
						stringBuilder.append(tmpStr);
					}
					line.setTraceRouteResult(stringBuilder.toString());
					logger.info(stringBuilder.toString());
					MainManager.queue.put(ip, line);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	@Override
	public void doAnalysis(String ip) throws Exception{
			traceRouteQueue.put(ip);
	}
}
