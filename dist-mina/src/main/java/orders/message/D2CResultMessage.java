package orders.message;

import com.eastcom.ipnet.orders.front.service.ExecuteResult;

@SuppressWarnings("serial")
public class D2CResultMessage extends AbstractMessage {
	private byte[] result;
	
	private ExecuteResult executeResult;

//	private byte[] command;
//
//	public byte[] getCommand() {
//		return command;
//	}
//
//	public void setCommand(byte[] command) {
//		this.command = command;
//	}

	public byte[] getResult() {
		return result;
	}

	public void setResult(byte[] result) {
		this.result = result;
	}
	
	public ExecuteResult getExecuteResult() {
		return executeResult;
	}
	
	public void setExecuteResult(ExecuteResult executeResult) {
		this.executeResult = executeResult;
	}

}
