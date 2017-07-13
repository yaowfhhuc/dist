package com.eastcom.ipnet.orders.message;

import java.io.Serializable;


public class ProcessInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -715791721368380602L;
	
	private Integer pid;
	private String processName;
	private Integer processType;
	private Integer processState;
	private Double cpuUsage;
	private Long memUsed;
	private String processDesc;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Integer getProcessType() {
		return processType;
	}
	public void setProcessType(Integer processType) {
		this.processType = processType;
	}
	public Integer getProcessState() {
		return processState;
	}
	public void setProcessState(Integer processState) {
		this.processState = processState;
	}
	public Double getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(Double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public Long getMemUsed() {
		return memUsed;
	}
	public void setMemUsed(Long memUsed) {
		this.memUsed = memUsed;
	}
	public String getProcessDesc() {
		return processDesc;
	}
	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	
	

}