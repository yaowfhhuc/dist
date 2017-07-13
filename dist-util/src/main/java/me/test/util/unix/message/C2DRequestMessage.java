package me.test.util.unix.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: Followinds 广东巡检项目改造组 <br>
 * Description: client 与 dispathcer请求信息 SIMTERM_CONNECT_REQUEST <br>
 * 
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-19 上午11:04:43 <br>
 * 
 */

public class C2DRequestMessage extends AbstractMessage {

	private static final long serialVersionUID = -8846423353978906875L;

	/**
	 * 请求参数集合
	 */
	Map<String, Object> paramMap = null;

	/**
	 * 仿真终端的byte流，只在SIMTREM_RAW_REQUEST、SIMTERM_SCRIPT_REQUEST 中使用
	 */
	private byte[] rawBytes = null;

	/** 
	 * 脚本解析类型 只在SIMTERM_SCRIPT_REQUEST 中使用 默认BeanShell解析
	 */
	private Integer scriptType = Constants.ANALYTICAL_BEAN_SHELL_SCRIPT;

	/**
	 * 脚本类型 只在SIMTERM_SCRIPT_REQUEST 中使用 默认在线
	 */
	private Integer offineLine = Constants.SCRIPT_ONLINE;

	public C2DRequestMessage() {

	}

	public Integer getOffineLine() {
		return offineLine;
	}

	public void setOffineLine(Integer offineLine) {
		this.offineLine = offineLine;
	}

	public void setScriptType(Integer scriptType) {
		this.scriptType = scriptType;
	}

	public Integer getScriptType() {
		return scriptType;
	}

	public void setParamInfo(Map<String, Object> param) {

		paramMap = param;
	}

	public Map<String, Object> getParamInfo() {
		return paramMap;
	}

	public void clearParamInfo() {
		if (paramMap != null) {
			paramMap.clear();
			paramMap = null;
		}

	}

	/**
	 * @param rawBytes
	 */
	public void setRawBytes(byte[] rawByteStream) {
		this.rawBytes = rawByteStream;
	}

	/**
	 * @return the rawBytes
	 */
	public byte[] getRawBytes() {
		return rawBytes;
	}

}
