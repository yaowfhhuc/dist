package orders.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PersistentCacherFactory {
    
	public static String CACHER_CLASS = "com.eastcom.ipnet.orders.util.PersistentFileCacher";
	private static Log log = LogFactory.getLog(PersistentCacherFactory.class);
	private static PersistentCacher cacher = null;
	static{
	    try
        {
            init();
        }
        catch (Exception e)
        {
            log.error("[建立缓存文件失败]",e);
        }
	}
	public static void init() throws Exception{
		cacher = (PersistentCacher) Class.forName(CACHER_CLASS).newInstance();
		cacher.init();
	}
	
	public static PersistentCacher getPersistentCacher(){
		return cacher;
	}
	
	public static void main(String[] args) throws Exception{
		PersistentFileCacher.ROOT = "cache";
		PersistentCacherFactory.init();
		
		PersistentCacherFactory.getPersistentCacher().put("ABC", "223456766677");
		System.out.println(PersistentCacherFactory.getPersistentCacher().get("ABC"));
	}
}
