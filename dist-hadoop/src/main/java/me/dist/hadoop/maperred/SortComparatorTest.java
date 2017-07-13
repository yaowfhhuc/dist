package me.dist.hadoop.maperred;

import org.apache.hadoop.io.WritableComparator;

import me.dist.hadoop.bean.FlowBean;

public class SortComparatorTest extends WritableComparator {

	@Override
	public int compare(Object a, Object b) {
		FlowBean flowBean = (FlowBean) a;
		FlowBean flowBean2 = (FlowBean) b;
		return flowBean.getSum_flow()>flowBean2.getSum_flow()?1:-1;
	}
}
