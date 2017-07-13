package me.test.util.unix.plugin;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.test.util.unix.prococol.Wrapper;


public abstract class AbstractTerminalPlugin implements IPlugin, IDataReceive {

	private static Log logger = LogFactory.getLog(AbstractTerminalPlugin.class);

	protected Wrapper wrapper;

	protected boolean loginSuccessFlag = false;

	protected String sessionid;

	protected int terminalType = 0;

	abstract public boolean autoLogin(HashMap<String, Object> map);

	abstract public boolean standardLogin(HashMap<String, Object> map);

	public boolean isLoginSuccess() {
		return loginSuccessFlag;
	}

	public void setLoginSuccess(boolean isSuccess) {
		loginSuccessFlag = isSuccess;
	}

	public boolean connect(String ip, int port, int net_delay) {
		try {
			wrapper.connect(ip, port, net_delay);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public void sendRawBytes(byte[] byteStream) throws IOException {
		wrapper.sendRawBytes(byteStream);
	}

	public boolean logout() {
		try {
			wrapper.disconnect();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public void setMatchMoreTagList(String[] matchMoreTagList) {
		wrapper.setMatchMoreTagList(matchMoreTagList);
	}

	public void setLoginConfirmPatternList(String[] confirmPatternList) {
		wrapper.setLoginConfirmPatternList(confirmPatternList);
	}

	public void onDataReceived(byte[] msg) {

	/*	IoSession dataChannelSession = ProxyServer.getInstance()
				.getDataChannel(sessionid);

		if (dataChannelSession == null) {
			// data channel此时有可能没有建立，只有 login 成功才能 继续 进行 data channel的创建工作
			// 因此此时无法发送数据
			logger.debug("[I] onDataReceived():Not found data channel sessionid ="
					+ sessionid);
			// + " IOsession = "
			// + dataChannelSession
			// + " , skip data send!");
			logger.debug("onDataReceived exit  = " + new String(msg));
			return;
		}
		logger.debug("dataChannelSession ==   "
				+ dataChannelSession.isClosing()
				+ "[I] onDataReceived : send SIMTREM_RAW_RESPONSE sessionid ="
				+ sessionid + "--- result = " + new String(msg));
		ResultMessage rm = new ResultMessage();
		rm.setMsgType(Constants.SIMTREM_RAW_RESPONSE);
		rm.setResult(msg);
		dataChannelSession.write(rm);*/
		// printBytes(msg);
	}

	// private void printBytes(byte[] data) {
	// StringBuffer pbuf = new StringBuffer();
	//
	// for (byte ch : data) {
	// pbuf.append((int) ch).append(" ");
	// }
	// logger.debug("[I] " + data.length + " bytes = " + pbuf.toString());
	// }

	public void onDataReceived(byte[] cmd, byte[] msg){
		
	}
	
	abstract public void setLogin(String user);

	abstract public void setPassword(String passwd);
	
	
}
