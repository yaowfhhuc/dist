package me.test.util.unix.command;

public interface Analysiser {
	
	public void doAnalysis(String ip) throws Exception;
	
	public int getQueueSize() ;
	
	public boolean isEnd();
}
