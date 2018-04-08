package orders.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;


/**
 * Title: Followinds 广东指令平台改造组 <br>
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-7-20 上午09:23:01 <br>
 *
 */
public class ReadLogUtil
{  //默认文件编码
   public static  final String  DEFAULT_ENCODE="GBK";
   private static final Logger log = Logger.getLogger(ReadLogUtil.class);

   public static byte[]  readLog(String path,String encode) throws Exception{
       path=System.getProperty("workHome")+"/data/"+path;
       log.info("-- --------------  path -----------"+path);
       File file=new File(path);
       if(!file.exists()) {
           log.info("-- -- readpath----- file not exists ---  "+path);
           return new byte[]{};
       }
       if(encode==null) encode=DEFAULT_ENCODE;
       byte[]  buff=new byte[512];
       StringBuffer sb=new StringBuffer();
       FileInputStream fi=null;
       BufferedInputStream bi=null;
       try{
           fi=new FileInputStream(file);
           bi=new BufferedInputStream(fi);
       int n=0;
       while((n=bi.read(buff))!=-1){
           String str=new String(buff,0,n,encode);
           sb.append(str);
           str=null;
          }
       }finally{
           fi.close();
           bi.close();
       }
       return sb.toString().getBytes();
   }
}
