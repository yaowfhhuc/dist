package orders.message;


/**
 * Title: Followinds 广东指令平台改造组 <br>
 * Description: <br>
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-22 下午02:16:43 <br>
 *该类定义为 dispatch server 到 proxy client 或 proxy server到 dispatch server 的信息
 * 消息类型可能为：
 * SIMTREM_RAW_RESPONSE : 回传给仿真终端的指令响应 BATCHCMD_RESPONSE ： 批量指令执行结果
 */
public class ResultMessage extends AbstractMessage {

	private static final long serialVersionUID = -3300456053422684713L;
    /**
     * 错误信息描述
     */
	private String errorMsg;

	/**
     * 返回的批量执行结果，（包括 仿真终端和批量执行两种）
     */
	private byte[] result;

    /**
     * 返回码
     */
	private int resultCode;

	private String sessionid;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	public byte[] getResult() {
		return result;
	}

	public void setResult(byte[] result) {
		this.result = result;
	}

	
	public int getResultCode() {
		return resultCode;
	}

	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
