package com.eastcom.ipnet.orders.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;


public class GetLocalFile extends GetFileFromLocalAnalysis
{
    protected static final Logger log = Logger.getLogger(GetLocalFile.class);
    private String indicate;
    private String[] filecontent;

    public GetLocalFile(String path, String date, String indicate)
    {
      super(path);
      this.indicate = indicate;
      this.filecontent = getContent(path, date);
    }
    
    public GetLocalFile(String path,String indicate)
    {  
      super(path);
      this.indicate = indicate;
    }
    

    public String[]  getContent(String path,String date){
      String[] value = new String[120];
      try {
        getFile(path, date);

        StringBuffer text = new StringBuffer();
        int i = 0;
        String line;
        while ((line = getNextLine()) != null) {
          if (line.startsWith(getIndicate())) {
            value[i] = text.toString();
            i++;
            //text = new StringBuffer();
            text.setLength(0);
            text.append(line).append("\n");
            continue;
          }
          text.append(line).append("\n");
        }
        value[i] = text.toString();
        text=null;
        buffer=null;
      }
      catch (Exception e)
      {
        e.printStackTrace();
        log.error("没有得到相关文件内容path= "+path,e);
      }
      return value;
    }
    
    public  String getContentV2(String path, String date)
    {
        StringBuffer buffer = new StringBuffer();
        try{
        File file = new File(path);
        if (file.getName().indexOf(date) <0) {
            return "";
        }
        FileInputStream in = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader( in,
                "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            line=line.trim();
            if(!line.equals(""))
             buffer.append(line+"\n");
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        log.error("没有得到相关文件内容path= "+path,e);
      }
      return buffer.toString();
    }

    public String getIndicate() {
      return this.indicate;
    }

    public String[] getFilecontent() {
      return this.filecontent;
    }

    public static void main(String[] args) {
      String path = "D:" + File.separator + "gd_srv02_20080927.txt";

      GetLocalFile file = new GetLocalFile(path, "20080927", "#");
      
      

      String[] strs = file.getFilecontent();
      for (int i = 0; i < strs.length; i++)
        if ((strs[i] != null) && (strs[i].trim().length() > 1)) {
          System.out.println(strs[i]);
          System.out.println("***********************************");
        }
    }
}
