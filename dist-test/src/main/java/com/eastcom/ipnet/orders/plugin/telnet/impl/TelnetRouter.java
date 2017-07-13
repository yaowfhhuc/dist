package com.eastcom.ipnet.orders.plugin.telnet.impl;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.message.Constants;
import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.telnet.TelnetWrapper;

public class TelnetRouter extends AbstractTerminalPlugin {

	private static Log logger = LogFactory.getLog(TelnetRouter.class);

	public TelnetRouter(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;
		wrapper = new TelnetWrapper();
	}

	public boolean autoLogin(HashMap<String, Object> map) {

		String first_user = (String) map.get(LoginParam.FIRST_USER_NAME);
		String first_password = (String) map.get(LoginParam.FIRST_PASSWORD);
		String first_login_prompt = (String) map.get(LoginParam.FIRST_LOGIN_PROMPT);
		String first_password_prompt = (String) map
				.get(LoginParam.FIRST_PASSWORD_PROMPT);
		// String second_user = map.get(LoginParam.SECOND_USER_NAME);
		String second_password = (String) map.get(LoginParam.SECOND_PASSWORD);
		// String second_login_prompt = map.get(LoginParam.SECOND_LOGIN_PROMPT);
		String second_password_prompt = (String) map
				.get(LoginParam.SECOND_PASSWORD_PROMPT);
		int login_level = Integer.parseInt((String) map.get(LoginParam.LOGIN_LEVEL));

		String prompt = null;
		long confirmTime;

		String su_cmd = (String) map.get(LoginParam.SU_CMD);

		try {
			wrapper.login(first_user, first_password, first_login_prompt,
					first_password_prompt, LoginParam.LOGIN_NET_DELAY);

			confirmTime = 2000;//Long.parseLong(LoadConfigFile.getInstance().getProperties().getProperty("util.login.confirmTime","2000").toString());
		} catch (IOException e) {

			logger.error("[E] TelnetRouter:login() normal login failure : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			confirmTime = Constants.LOGIN_CONFIRM_TIME;
		}

//		 认证时间可能会比较长，难以评估，需要给足够的时间,暂时定为两秒
		prompt = wrapper.searchPrompt(confirmTime);
		if (!wrapper.confirmLogin(prompt)) {
			logger
					.debug("[I] Telnet login failure due to incorrect prompt encountered : "
							+ prompt);
			return false;
		} else {
			if (logger.isDebugEnabled())
				logger.debug("[I] telnet.searchPrompt() return prompt : "
						+ prompt);
		}
		// System.out.println("[I] telnet.searchPrompt() return prompt : "
		// + prompt);

		if (login_level == LoginParam.SUPER_LOGIN) {
			try {
				wrapper.send(su_cmd);
				wrapper.changeLevel(second_password, second_password_prompt,
						LoginParam.NET_DELAY);
			} catch (IOException e) {
				logger
						.error("[E] TelnetRouter:changeLevel()  SUPER_LOGIN failure : "
								+ e.getMessage());
				return false;
			}

			prompt = wrapper.searchPrompt(confirmTime);

			if (!wrapper.confirmLogin(prompt)) {
				logger.error("[E] Change to super user failure , sessionid = "
						+ sessionid);
				return false;
			}
			wrapper.setPrompt(prompt);
		} else
			wrapper.setPrompt(prompt);

		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}

		return true;
	}

	@Override
	/**
	 * 
	 */
	public boolean standardLogin(HashMap<String, Object> map) {
		if (terminalType == LoginParam.TERMINAL) {
			wrapper.setListener(this);
			wrapper.startReaderThread();
		}
		return true;
	}

	@Override
	public void setLogin(String user) {

	}

	@Override
	public void setPassword(String passwd) {

	}

}
