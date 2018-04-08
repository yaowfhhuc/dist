package orders.message;

import com.eastcom.ipnet.orders.util.SystemUtil;



public class ProcessStatusMonitorMessage extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7250084605787268740L;
	
	private String proxyID = null;
	
	private ProcessInfo processInfo = null;
	
	private Double collectCpu;
	
	private Double collectMem;

	public ProcessStatusMonitorMessage() {
		super.setMsgType(Constants.PROCESS_STATUS);
	}
	
	public void init(){
		processInfo = SystemUtil.getProcessInfo();
	}
	

	public String getProxyID() {
		return proxyID;
	}

	public void setProxyID(String proxyID) {
		this.proxyID = proxyID;
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public Double getCollectCpu() {
		return collectCpu;
	}

	public void setCollectCpu(Double collectCpu) {
		this.collectCpu = collectCpu;
	}

	public Double getCollectMem() {
		return collectMem;
	}

	public void setCollectMem(Double collectMem) {
		this.collectMem = collectMem;
	}
	
	
}



