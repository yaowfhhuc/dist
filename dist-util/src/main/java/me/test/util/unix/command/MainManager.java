package me.test.util.unix.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainManager{

	private static Logger logger = LoggerFactory.getLogger(MainManager.class);
	
	protected static Map<String, LineEntity> queue = new HashMap<String, LineEntity>();
	
	private static List<Analysiser> analysisers = new ArrayList<>();
	
	private static MainManager instance = null;
	
	public static MainManager getInstance() {
		if(instance == null){
			instance = new MainManager();
		}
		return instance;
	}
	private MainManager(){
		LoadEntity.getInstance().loadEntityFromFile();
		analysisers.add(TracerouteAnalysiser.getInstance(5));
		analysisers.add(LoginAnalysiser.getInstance(5));
	}
	
	public void run() {
		LineEntity lineEntity = null;
		Iterator<String> iterator = MainManager.queue.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			for(Analysiser analysiser:analysisers){
				try {
					analysiser.doAnalysis(key);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("analysis {} failed . {} ",lineEntity,e.getMessage());
					continue;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		logger.info("MainManager start >>> ");
		MainManager.getInstance().run();
		while(true){
			try {
				boolean isEnd = true;
				for(Analysiser analysiser :analysisers){
					isEnd &= (analysiser.getQueueSize()==0);
				}
				
				Thread.sleep(15000);

				if(isEnd){
					ExportFile.getInstance().export2Excel();
					logger.info("analysis end ..");
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.exit(0);
	}
	
}
