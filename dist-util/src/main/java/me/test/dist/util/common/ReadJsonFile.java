package me.test.dist.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONReader;

public class ReadJsonFile {
	
	public static <T extends Object> List<T> readJson(File file,Class<T> type) throws Exception{
		return readJson(file,type,"UTF-8");
	}
	
	public static <T extends Object> List<T> readJson(File file,Class<T> type,String charSet) throws Exception{
			InputStreamReader reader = null;
			InputStream is= new FileInputStream(file);
			reader = new InputStreamReader(is, charSet);//, "GB18030"
			JSONReader jsonReader = new JSONReader(reader);
			jsonReader.startArray();
			final List<T> list = new ArrayList<>();
			while (jsonReader.hasNext()) {
				T entity = jsonReader.readObject(type);
				list.add(entity);
			}
			jsonReader.endArray();
			jsonReader.close();
			return list;
	}
}
