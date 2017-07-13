package com.eastcom.ipnet.orders.plugin.ssh.impl;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.message.Constants;
import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.ssh.SshWrapper;


public class Ssh1Host extends AbstractTerminalPlugin {

	private static Log logger = LogFactory.getLog(Ssh1Host.class);

	public Ssh1Host(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;
		wrapper = new SshWrapper();
	}

	public boolean autoLogin(HashMap<String, Object> map) {

		String first_user = (String) map.get(LoginParam.FIRST_USER_NAME);
		String first_password = (String) map.get(LoginParam.FIRST_PASSWORD);
		String first_login_prompt = (String) map
				.get(LoginParam.FIRST_LOGIN_PROMPT);
		// String first_password_prompt = map
		// .get(LoginParam.FIRST_PASSWORD_PROMPT);
		// String second_user = map.get(LoginParam.SECOND_USER_NAME);
		// String second_password = map.get(LoginParam.SECOND_PASSWORD);
		// String second_login_prompt = map.get(LoginParam.SECOND_LOGIN_PROMPT);
		// String second_password_prompt = map
		// .get(LoginParam.SECOND_PASSWORD_PROMPT);
		int login_level = Integer.parseInt((String) map
				.get(LoginParam.LOGIN_LEVEL));
		String prompt = null;
		long confirmTime;

		((SshWrapper) wrapper).setLogin(first_user);
		((SshWrapper) wrapper).setPassword(first_password);
		wrapper.setPrompt(first_login_prompt);
		try {
			wrapper.sendNoWait("");
			confirmTime = 2000;// Long.parseLong(LoadConfigFile.getInstance().getProperties().getProperty("util.login.confirmTime","2000").toString());
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			confirmTime = Constants.LOGIN_CONFIRM_TIME;
		}
		prompt = wrapper.searchPrompt(confirmTime);
		if (!wrapper.confirmLogin(prompt)) {
			logger
					.error("[E] Ssh login failure due to incorrect prompt encountered : "
							+ prompt);
			return false;
		} else {
			if (logger.isDebugEnabled())
				logger
						.debug("[I] ssh.searchPrompt() return prompt : "
								+ prompt);
		}

		wrapper.setPrompt(prompt);

		if (login_level == LoginParam.SUPER_LOGIN) {

			// ssh.login(second_user, second_password);
			// ????
			// ssh root �û���ֱ�ӵ�¼������su

		}

		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}
		return true;

	}

	@Override
	public boolean standardLogin(HashMap<String, Object> map) {
		String user = (String) map.get(Constants.ATTR_DEV_USER);
		String password = (String) map.get(Constants.ATTR_DEV_PASSWORD);

		((SshWrapper) wrapper).setLogin(user);
		((SshWrapper) wrapper).setPassword(password);

		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}

		try {
			wrapper.sendNoWait("");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	@Override
	public void setLogin(String user) {
		((SshWrapper) wrapper).setLogin(user);
	}

	@Override
	public void setPassword(String passwd) {
		((SshWrapper) wrapper).setPassword(passwd);

	}
}
