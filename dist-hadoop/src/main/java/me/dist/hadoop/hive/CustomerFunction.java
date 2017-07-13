package me.dist.hadoop.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

public class CustomerFunction extends UDF{

	
	public  int evaluate(int up_flow ,int d_flow) {
		return up_flow+d_flow;
	}
	
	
	public String evaluate(String num){
		return Integer.parseInt(num)%2==0?"偶数":"奇数";
	}
}
