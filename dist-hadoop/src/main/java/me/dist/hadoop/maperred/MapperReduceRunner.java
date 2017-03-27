package me.dist.hadoop.maperred;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



//hadoop jar *.jar org.dist.hadoop.maperred.MapperReduceRunner



public class MapperReduceRunner {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		//System.setProperty("hadoop.home.dir", "D:/greensoft/hadoop-2.4.1");
		
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.190.129:9000");
		
		Job job = Job.getInstance(conf);
		
		//设置jar包
		//  conf.set("mapreduce.job.jar", "dist-hadoop-0.0.1-SNAPSHOT.jar"); 与下同
		job.setJarByClass(MapperReduceRunner.class);
		
		
		job.setMapperClass(MapperTest.class);
		job.setReducerClass(ReducerTest.class);
		
		//job.setCombinerClass(ReducerTest.class); 可以跟reducer用同一个类， 具体根据算法决定
		job.setCombinerClass(CombinerTest.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		
		//加入自定义分区
		job.setPartitionerClass(PartitionerTest.class);
		//设置reduce task 的数量 ， 与分片个数匹配
		//设置数量少与partition的数量时 会抛出异常（为1时无异常）
		job.setNumReduceTasks(3);
		
		//输入数据存放路径
		FileInputFormat.setInputPaths(job, "/input");
		//输出数据存放路径
		FileOutputFormat.setOutputPath(job, new Path("/output"));
		
		job.waitForCompletion(true);
	}
	
}
