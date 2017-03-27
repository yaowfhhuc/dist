package me.dist.hadoop.maperred;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionerTest<KEY,VALUE> extends Partitioner<KEY, VALUE>{

	private static Map<String, Integer> maps  = new HashMap<>();
	
	static {
		//获取分片条件
		maps.put("", 123);
		maps.put("", 234);
	}
	
	@Override
	public int getPartition(KEY key, VALUE value, int numPartitions) {
		return maps.get(key.toString());
	}

}
