package me.test.util.unix.command;

import org.slf4j.LoggerFactory;

import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;

public class ExportFile {

	private static Logger logger = LoggerFactory.getLogger(ExportFile.class);
	
	private static File destFile = null;
	
	private static ExportFile instance = null;
	
	public static ExportFile getInstance() {
		if(instance == null){
			instance = new ExportFile();
		}
		return instance;
	}
	
	private ExportFile(){
		destFile = new File( LoadConfigFile.getInstance().getProperties().getProperty("util.command.dest.file","result.xls"));
	}

	private static String[] getTitle(){
		return new String[]{"deviceName","businessSystem","deviceIp","deviceType","telnetType",
			"port","proxyId","loginId","loginPassword","prompt","result","mark"};
	}

	public void export2Excel(){
		if(destFile!=null){
			try {
				WorkbookSettings settings = new WorkbookSettings();
				settings.setEncoding("gb2312");
				OutputStream os = new FileOutputStream(destFile);
				WritableWorkbook workbook = jxl.Workbook.createWorkbook(os, settings);
				WritableSheet worksheet = workbook.createSheet("测试结果", 0);

				Label label = null;// 用于写入文本内容到工作表中去
				String[] titles = getTitle();
				// 开始写入第一行，即标题栏
				for (int i = 0; i < titles.length; i++)
				{
					String title = titles[i];
					label = new Label(i, 0, title);
					worksheet.addCell(label);
				}
				AtomicInteger count = new AtomicInteger(0);
				if(MainManager.queue.size()!=0){
					Set<String> keySet =  MainManager.queue.keySet();
					Iterator<String> iterator = keySet.iterator();
					
					while(iterator.hasNext()){
						int lineNum = count.addAndGet(1);
						LineEntity res = MainManager.queue.get(iterator.next());
						Map<String, Object> map = res.toMap();
							for(int j =0 ;j<titles.length;j++){
								String value = map.get(titles[j])==null?null:map.get(titles[j]).toString();
								label = new Label(j,lineNum, value);
								worksheet.addCell(label);
							}
					}
				}
				workbook.write();
				workbook.close();
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error("file {} not found . {}",destFile.getName(),e.getMessage());
			} catch (WriteException e) {
				e.printStackTrace();
				logger.error("write to file {} failed . {}",destFile.getName() ,e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("file stream error .{}.",e.getMessage());
			} 
		}
		logger.info("export to file {} success !!!" ,destFile.getName());
	}
	
}
