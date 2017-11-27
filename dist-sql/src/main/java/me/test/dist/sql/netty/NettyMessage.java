package me.test.dist.sql.netty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NettyMessage<T> implements Message,Serializable{

	private static final long serialVersionUID = 7043963658466255132L;
	
	private String TYPE;
	private Map<String, Object> params ;
	private String data;
	private List<T> dataList;
	/**
	 * @return the tYPE
	 */
	public String getTYPE() {
		return TYPE;
	}
	/**
	 * @param tYPE the tYPE to set
	 */
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	/**
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the dataList
	 */
	public List<T> getDataList() {
		return dataList;
	}
	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
