
package me.test.util.unix.message;

import java.io.Serializable;

/**
 * Title: Followinds 广东巡检项目改造组 <br>
 * Description: 与dispatcher通信消息模型 <br>
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-19 上午11:12:26 <br>
 *
 */
public abstract class AbstractMessage implements Serializable {

    /**
     * 消息类型
     * @see  com.eastcom.ipnet.orders.message.Constants
     */
	private int msgType=-1;
	
	/**
     * 与 dispatcher 通信的 相关客户端类型
     * @see  com.eastcom.ipnet.orders.message.Constants
     */
	private int clientType = Constants.CLIENT_SIM_TERMINAL;
	
	
	private byte[] command;
	
    /**
     * 得到信息类型
     * @see  com.eastcom.ipnet.orders.message.Constants
     * @return
     */
	public int getMsgType() {
		return msgType;
	}

	/**
	 *  SET信息类型
	 * @see  com.eastcom.ipnet.orders.message.Constants
	 * @param msgType
	 */
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	/**
	 *  SET 与 dispatcher 通信的 相关客户端类型
	 * @see  com.eastcom.ipnet.orders.message.Constants
	 * @param clientType
	 */
	
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	/**
	 * 得到  与 dispatcher 通信的 相关客户端类型
	 * @see  com.eastcom.ipnet.orders.message.Constants
	 * @return
	 */
	public int getClientType() {
		return clientType;
	}

	public byte[] getCommand() {
		return command;
	}

	public void setCommand(byte[] command) {
		this.command = command;
	}


}
