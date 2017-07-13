package me.dist.hadoop.maperred;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperTest extends Mapper<LongWritable, Text, Text, LongWritable>{

	
	/**
	 * 只在此mapper初始化时由系统调用一次
	 */
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		//全局数据的获取等，比如全局文件，数据库连接，缓存数据获取等
		super.setup(context);
	}
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		
		String[] values = StringUtils.split(value.toString()," ");
		
		for(String value1 :values){
			context.write(new Text(value1), new LongWritable(1));
		}
	}
	
	
	/**
	 * 只在此Mapper执行结束时由系统调用一次
	 */
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		super.cleanup(context);
	}
}
