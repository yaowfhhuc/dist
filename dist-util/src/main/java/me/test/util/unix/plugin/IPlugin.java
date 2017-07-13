package me.test.util.unix.plugin;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author nedved
 * 
 */
public interface IPlugin {

	/**
	 * 连接设备
	 * 
	 * @param ip
	 * @param port
	 * @param net_delay
	 * @return
	 */
	public boolean connect(String ip, int port, int net_delay);

	/**
	 * 登陆设备
	 * 
	 * @param map
	 * @return
	 */
	public boolean autoLogin(HashMap<String, Object> map);

	/**
	 * 登陆设备
	 * 
	 * @param map
	 * @return
	 */
	public boolean standardLogin(HashMap<String, Object> map);

	/**
	 * 发送命令
	 * 
	 * @param byteStream
	 * @throws IOException
	 */
	public void sendRawBytes(byte[] byteStream) throws IOException;

	/**
	 * 登出设备
	 * 
	 * @return
	 */
	public boolean logout();

	/**
	 * 设置“更多”提示符
	 * 
	 * @param matchMoreTagList
	 */
	public void setMatchMoreTagList(String[] matchMoreTagList);

	/**
	 * 设置登陆提示
	 * 
	 * @param confirmPatternList
	 */
	public void setLoginConfirmPatternList(String[] confirmPatternList);

	public void setLogin(String user);

	public void setPassword(String passwd);

}
