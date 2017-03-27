package me.dist.hadoop.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{

	private String no;
	private long up_flow;
	private long d_flow;
	private long sum_flow;
	
	public void set(String no,long up_flow,long d_flow){
		this.no = no;
		this.up_flow = up_flow;
		this.d_flow = d_flow;
		this.sum_flow = up_flow+d_flow;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public long getUp_flow() {
		return up_flow;
	}
	public void setUp_flow(long up_flow) {
		this.up_flow = up_flow;
	}
	public long getD_flow() {
		return d_flow;
	}
	public void setD_flow(long d_flow) {
		this.d_flow = d_flow;
	}
	public long getSum_flow() {
		return sum_flow;
	}
	public void setSum_flow(long sum_flow) {
		this.sum_flow = sum_flow;
	}
	
	@Override
	public String toString() {
		return "no:"+no+"up_flow:"+up_flow+"d_flow:"+d_flow+"sum_flow:"+sum_flow;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(no);
		out.writeLong(up_flow);
		out.writeLong(d_flow);
		out.writeLong(sum_flow);
	}
	
	/**
	 * 顺序和write 相同
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		no = in.readUTF();
		up_flow = in.readLong();
		d_flow = in.readLong();
		sum_flow = in.readLong();
	}
	@Override
	public int compareTo(FlowBean o) {
		if(this.sum_flow>o.sum_flow)
			return 1;
		if(this.sum_flow<o.sum_flow)
			return -1;
		return 0;
	}
	
}
