package me.dist.hadoop.hdfs;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Unit test for simple App.
 */
public class HdfsTest {
	
	private static FileSystem fs = null ;
	
	static{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://ns1");
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void upLoad(Path src,Path dst) throws IOException{
		fs.copyFromLocalFile(src, dst);
	}
	
	public static void downLoad(Path src,Path dst) throws IOException{
		fs.copyToLocalFile(src, dst);
	}
	
	public static boolean mkdirs(String dirs) throws IllegalArgumentException, IOException{
		return fs.mkdirs(new Path(dirs));
	}
	
	public static boolean mkdirs(Path dirs) throws IOException{
		return fs.mkdirs(dirs);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean deleteFile(String file) throws Exception{
		return fs.delete(new Path(file));
	}
	
	@SuppressWarnings("deprecation")
	public static boolean deleteFile(Path file) throws Exception{
		return fs.delete(file);
	}
	
	public static boolean deleteDirs(Path path) throws IOException{
		return fs.delete(path, true);
	}
	
	public static boolean mv(Path src,Path dst) throws IOException{
		return fs.rename(src, dst);
	}

	public static boolean rename(Path src,Path dst) throws IOException{
		return mv(src, dst);
	}
	
	public static void main(String[] args) {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		try {
			/*Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://ns1");
			fs = FileSystem.get(conf);*/
			upLoad(new Path("C:/Users/Admin/Downloads/linuxx64_12201_database.zip"), new Path("/linux"));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
}
