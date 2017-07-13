package com.eastcom.ipnet.orders.plugin.telnet.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.plugin.AbstractTerminalPlugin;
import com.eastcom.ipnet.orders.plugin.util.LoginParam;
import com.eastcom.ipnet.orders.protocol.telnet.TelnetWrapper;

public class TelnetHost extends AbstractTerminalPlugin {

	private static Log logger = LogFactory.getLog(TelnetHost.class);

	public TelnetHost(int terminal_type, String sessionid) {
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
		String second_user = (String) map.get(LoginParam.SECOND_USER_NAME);
		String second_password = (String) map.get(LoginParam.SECOND_PASSWORD);
		String second_login_prompt = (String) map.get(LoginParam.SECOND_LOGIN_PROMPT);
		String second_password_prompt = (String) map
				.get(LoginParam.SECOND_PASSWORD_PROMPT);
		int login_level = Integer.parseInt((String) map.get(LoginParam.LOGIN_LEVEL));

		String su_cmd = (String) map.get(LoginParam.SU_CMD);

		try {
			long loginBeginTime = System.currentTimeMillis();
			wrapper.login(first_user, first_password, first_login_prompt,
					first_password_prompt, LoginParam.LOGIN_NET_DELAY);

			long loginEndTime = System.currentTimeMillis();
//			 等待一段时间,发一个回车,检验是否确实登录成功
			Thread.sleep(2 * (loginEndTime - loginBeginTime));
			wrapper.send("id");
			String prompt = wrapper
					.searchPrompt(2 * (loginEndTime - loginBeginTime));
			if (!wrapper.confirmLogin(prompt)) {
				logger
						.error("[E] Telnet login failure due to incorrect prompt encountered : "
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
				wrapper.send(su_cmd);
				wrapper.login(second_user, second_password,
						second_login_prompt, second_password_prompt,
						LoginParam.NET_DELAY);

				prompt = wrapper
						.searchPrompt(2 * (loginEndTime - loginBeginTime));

				if (!wrapper.confirmLogin(prompt)) {
					logger.error("[E] Su to root user failure , sessionid = "
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

		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
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
