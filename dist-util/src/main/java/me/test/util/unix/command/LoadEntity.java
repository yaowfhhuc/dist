package me.test.util.unix.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadEntity {

	private static Logger logger = LoggerFactory.getLogger(LoadEntity.class);
	
	private static File targetFile = null;
	
	public static Properties properties = null;
	
	private static LoadEntity instance = null;
	
	public static LoadEntity getInstance() {
		if(instance == null){
			instance = new LoadEntity();
		}
		return instance;
	}
	
	private LoadEntity(){
		properties = LoadConfigFile.getInstance().getProperties();
		targetFile = new File( properties.getProperty("util.command.target.file"));
	}
	
	public List<LineEntity> loadEntityFromFile() {
		logger.info("load file {} start >>>",targetFile.getName());
		
		List<LineEntity> ipList = new ArrayList<LineEntity>();
		String fileType = properties.getProperty("util.target.filetype",
				targetFile.getName().substring(targetFile.getName().lastIndexOf("."),targetFile.getName().length() ));
		try {
			if(fileType.equals("xls")){
				loadXlsIps(ipList,targetFile);
			}else if (fileType.equals("xlsx")) {
				loadXlsxIps(ipList,targetFile);
			}else if (fileType.equals("csv")) {
				loadCsvIps(ipList,targetFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("load source file failed , {}",e.getMessage());
		}
		for(LineEntity lineEntity :ipList){
			MainManager.queue.put(lineEntity.getDeviceIp(), lineEntity) ;
		}
		return ipList;
	}
	
	private void loadXlsIps(List<LineEntity> ipList,File targetFile ) throws Exception{
		Workbook workbook = new HSSFWorkbook(new FileInputStream(targetFile));
		loadIpsContent(ipList,workbook );
	}
	
	private void loadXlsxIps(List<LineEntity> ipList,File targetFile ) throws Exception{
		Workbook workbook = new XSSFWorkbook(new FileInputStream(targetFile));
		loadIpsContent(ipList,workbook);
	}
	
	private void loadCsvIps(List<LineEntity> ipList,File targetFile ) throws Exception{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile));
		String tmpStr = null;
		bufferedReader.readLine();
		LineEntity line = null;	
		while((tmpStr = bufferedReader.readLine()) !=null){
			String[] texts = tmpStr.split(",");
			line = new LineEntity();
			line.setDeviceName(texts[0]);
			line.setBusinessSystem(texts[1]);
			line.setDeviceIp(texts[2]);
			line.setDeviceType(texts[3]);
			line.setTelnetType(texts[4]);
			line.setPort(texts[5]);
			line.setProxyId(texts[6]);
			line.setLoginId(texts[7]);
			line.setLoginPassword(texts[8]);
			line.setPrompt(texts[9]);
			ipList.add(line);
		}
		bufferedReader.close();
	}
	
	private void loadIpsContent(List<LineEntity> ipList, Workbook workbook){
		Sheet sheet = workbook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		Row row = null;
		LineEntity line = null;
		for(int i =1;i<rowNum ;i++){
			line = new LineEntity();
			row = sheet.getRow(i);
			line.setDeviceName( getCellFormatValue(row.getCell(0)).toString());
			line.setBusinessSystem( getCellFormatValue(row.getCell(1)).toString());
			line.setDeviceIp( getCellFormatValue(row.getCell(2)).toString());
			line.setDeviceType( getCellFormatValue(row.getCell(3)).toString());
			line.setTelnetType( getCellFormatValue(row.getCell(4)).toString());
			line.setPort( getCellFormatValue(row.getCell(5)).toString());
			line.setProxyId(getCellFormatValue(row.getCell(6)).toString());
			line.setLoginId(getCellFormatValue(row.getCell(7)).toString());
			line.setLoginPassword( getCellFormatValue(row.getCell(8)).toString());
			line.setPrompt( getCellFormatValue(row.getCell(9)).toString());
			ipList.add(line);
		}
	}
	
    /** 
     *  
     * 根据Cell类型设置数据 
     *  
     * @param cell 
     * @return 
     * @author zengwendong 
     */  
    private Object getCellFormatValue(Cell cell) {  
        Object cellvalue = "";  
        if (cell != null) {  
            // 判断当前Cell的Type  
            switch (cell.getCellTypeEnum()) {  
            case NUMERIC :// 如果当前Cell的Type为NUMERIC  
            case FORMULA: {  
                // 判断当前的cell是否为Date  
                if (DateUtil.isCellDateFormatted(cell)) {  
                    // 如果是Date类型则，转化为Data格式  
                    // data格式是带时分秒的：2013-7-10 0:00:00  
                    // cellvalue = cell.getDateCellValue().toLocaleString();  
                    // data格式是不带带时分秒的：2013-7-10  
                    Date date = cell.getDateCellValue();  
                    cellvalue = date;  
                } else {// 如果是纯数字  
  
                    // 取得当前Cell的数值  
                    cellvalue = String.valueOf(cell.getNumericCellValue());  
                }  
                break;  
            }  
            case STRING:// 如果当前Cell的Type为STRING  
                // 取得当前的Cell字符串  
                cellvalue = cell.getRichStringCellValue().getString();  
                break;  
            default:// 默认的Cell值  
                cellvalue = "";  
            }  
        } else {  
            cellvalue = "";  
        }  
        return cellvalue;  
    }  
}
