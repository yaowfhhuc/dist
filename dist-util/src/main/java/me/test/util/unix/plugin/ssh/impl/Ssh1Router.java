package me.test.util.unix.plugin.ssh.impl;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.test.util.unix.LoginParam;
import me.test.util.unix.command.LoadConfigFile;
import me.test.util.unix.message.Constants;
import me.test.util.unix.plugin.AbstractTerminalPlugin;
import me.test.util.unix.prococol.ssh.SshWrapper;

public class Ssh1Router extends AbstractTerminalPlugin {

	private static Log logger = LogFactory.getLog(Ssh1Router.class);

	public Ssh1Router(int terminal_type, String sessionid) {
		this.sessionid = sessionid;
		this.terminalType = terminal_type;
		wrapper = new SshWrapper();
	}

	@Override
	public void setLogin(String user) {
		((SshWrapper) wrapper).setLogin(user);
	}

	@Override
	public void setPassword(String passwd) {
		((SshWrapper) wrapper).setPassword(passwd);

	}

	public boolean autoLogin(HashMap<String, Object> map) {
		String first_user = (String) map.get(LoginParam.FIRST_USER_NAME);
		String first_password = (String) map.get(LoginParam.FIRST_PASSWORD);
		String first_login_prompt = (String) map
				.get(LoginParam.FIRST_LOGIN_PROMPT);
//		String first_password_prompt = (String) map
//				.get(LoginParam.FIRST_PASSWORD_PROMPT);

		String second_password = (String) map.get(LoginParam.SECOND_PASSWORD);
		String second_password_prompt = (String) map
				.get(LoginParam.SECOND_PASSWORD_PROMPT);

		int login_level = Integer.parseInt((String) map
				.get(LoginParam.LOGIN_LEVEL));

		String prompt = null;
		long confirmTime;

		((SshWrapper) wrapper).setLogin(first_user);
		((SshWrapper) wrapper).setPassword(first_password);
		wrapper.setPrompt(first_login_prompt);

		try {
			wrapper.sendNoWait("");
			confirmTime = Long.parseLong(LoadConfigFile.getInstance().getProperties().getProperty("util.login.confirmTime","2000").toString());
		} catch (IOException e1) {
			logger.error(e1);
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

		if (login_level == LoginParam.SUPER_LOGIN) {
			String su_cmd = (String) map.get(LoginParam.SU_CMD);
			try {
				wrapper.send(su_cmd);
				wrapper.changeLevel(second_password, second_password_prompt,
						LoginParam.NET_DELAY);
			} catch (IOException e) {
				logger.error("[E] Ssh1Router:login()  SUPER_LOGIN failure : "
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

}
