package com.eastcom.ipnet.orders.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * 临时文件缓存
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-20 下午04:54:02 <br>
 *
 */
public class PersistentFileCacher implements PersistentCacher{
	public static String ROOT = "../cache";
	
	private File root = null;
	
	public void put(String key, Object obj)  throws Exception{
		String fn = "M" + MD5.getInstance().getMd5Code(key.getBytes());
		File f = new File(root, fn);
		
		FileOutputStream fout = new FileOutputStream(f);
		try{
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(obj);
			out.flush();
			out.close();
		} finally {
			fout.close();
		}
	}

	public Object get(String key)  throws Exception{
		String fn = "M" + MD5.getInstance().getMd5Code(key.getBytes());
		File f = new File(root, fn);
		
		FileInputStream fin = new FileInputStream(f);
		try{
			ObjectInputStream in = new ObjectInputStream(fin);
			Object obj = in.readObject();
			in.close();
			return obj;
		} finally {
			fin.close();
		}		
	}

	public void init() throws Exception {
		root = new File(ROOT);
		if (!root.exists()){
			root.mkdir();
		} else {
			if (!root.isDirectory()) throw new Exception(root.getCanonicalPath() +"不是一个目录");
			if (!root.canWrite()) throw new Exception(root.getCanonicalPath() + "目录无操作权限");
		}
		
	}
	public static void main(String[] args)
    {
        try
        {
            new PersistentFileCacher().init();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	

}
