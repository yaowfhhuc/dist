package orders.plugin.db.impl;

import java.io.IOException;
import java.util.HashMap;

import orders.message.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.eastcom.ipnet.orders.message.Constants;
import com.eastcom.ipnet.orders.message.ResultMessage;
import com.eastcom.ipnet.orders.plugin.IDataReceive;
import com.eastcom.ipnet.orders.plugin.IPlugin;
import com.eastcom.ipnet.orders.util.ObjectSeriUtil;


/**
 * 
 * @author xiazy
 */
public class DbDevice implements IPlugin,IDataReceive {
	
	private static Log logger = LogFactory.getLog(DbDevice.class);
	private HashMap<String, Object> param;
	private DbWrapper wrapper;
	protected int terminalType = 0;
	protected String sessionid;
	public DbDevice(int terminal_type, String sessionid){
		this.terminalType=terminal_type;
		this.sessionid=sessionid;
		wrapper=new DbWrapper();
		logger.info("-- DbDevice init sessionId="+sessionid);
	}
	
	@Override
	public boolean autoLogin(HashMap<String, Object> map) {
		this.param=map;
		return false;
	}

	/**
	 * String ip, int port, int netDelay 这3参数随便填，数据库连接时，以param中的参数为准
	 */
	@Override
	public boolean connect(String ip, int port, int netDelay) {
		try {
			wrapper.connect(param);
			onDataReceived(("connect " + param.get(Constants.ATTR_DB_URL) +"\n").getBytes(),ObjectSeriUtil.objectToByte("OK"));
			return true;
		} catch (Exception e) {
			logger.error("DB 登录:"+e.getMessage(), e);
			throw new RuntimeException(e);
//			return false;
		}
	}

	
	@Override
	public boolean logout() {
		try {
			wrapper.disconnect();
			param=null;
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e);
//			return false;
		}
	}

	@Override
	public void sendRawBytes(byte[] byteStream) throws IOException {
		 try {
	        	byte[] res=wrapper.sendCommand(byteStream);
	        	onDataReceived(byteStream, res);
			}catch (Exception e) {
				logger.error("db send command ERROR ", e);
				onExceptionOccured(byteStream, ">>指令错误 <<"  +e.getMessage());
//				throw new IOException(e);
			}
	}

	@Override
	public void setLogin(String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginConfirmPatternList(String[] confirmPatternList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMatchMoreTagList(String[] matchMoreTagList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPassword(String passwd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean standardLogin(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 向dispather端写日志过去
	 */
	@Override
	public void onDataReceived(byte[] cmd, byte[] msg) {
		/*IoSession dataChannelSession = ProxyServer.getInstance()
				.getDataChannel(sessionid);

		if (dataChannelSession == null) {
			// data channel此时有可能没有建立，只有 login 成功才能 继续 进行 data channel的创建工作
			// 因此此时无法发送数据
			 logger.debug("[I] onDataReceived():Not found data channel sessionid ="
					 + sessionid);
			return;
		}
		logger.debug("dataChannelSession ==   "+ "[I] onDataReceived : send SIMTREM_RAW_RESPONSE sessionid ="+ sessionid);
		ResultMessage rm = new ResultMessage();
		rm.setMsgType(Constants.SIMTREM_RAW_RESPONSE);
		rm.setResult(msg);
		rm.setCommand(cmd);
		dataChannelSession.write(rm);*/
	}
	
	public void onDataReceived(byte[] msg){
		onDataReceived(null, msg);
	}
	
	 /**
     * 异常处理
     * @param message
     */
	public void onExceptionOccured(byte[] cmd, String message){
		/*IoSession dataChannelSession = ProxyServer.getInstance()
		.getDataChannel(sessionid);
		if (dataChannelSession == null) {
			 logger
			 .debug("[I] onExceptionOccured():Not found data channel sessionid ="
			 + sessionid);
			return;
		}
		logger.debug("dataChannelSession ==   "+dataChannelSession.isClosing()+"[I] onExceptionOccured : send SIMTREM_RAW_RESPONSE sessionid ="
                + sessionid + "--- error = " + message);
		ResultMessage rm = new ResultMessage();
		rm.setMsgType(Constants.SIMTREM_RAW_RESPONSE);
	    rm.setErrorMsg(message);
	    try {
			rm.setResult(ObjectSeriUtil.objectToByte(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
		rm.setCommand(cmd);
	    rm.setResultCode(Constants.D2C_SIMTERM_REQUEST_FAIL);
		dataChannelSession.write(rm);*/
	}

}
