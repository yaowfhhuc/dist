package orders.protocol.ssh2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DumbAuthUserInfo implements UserInfo, UIKeyboardInteractive {

	private static Log logger = LogFactory.getLog(DumbAuthUserInfo.class);
	
	String passwd = null;
	String passphrase = null;

	public void setPassword(String password) {
		this.passwd = password;
	}

	public boolean promptYesNo(String str) {
		return true;
	}

	public String getPassword() {
		return passwd;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public boolean promptPassword(String message) {
		return true;
	}

	public boolean promptPassphrase(String message) {
		return true;
	}

	public void showMessage(String message) {

	}

	public String[] promptKeyboardInteractive(String destination, String name,
			String instruction, String[] prompt, boolean[] echo) {

		String[] response = new String[prompt.length];

		for (int i = 0; i < prompt.length; i++) {
			logger.debug("Keyboard Interactive prompt ["+ prompt[i] + "]");

			if (prompt[i].contains("yes/no")) {
				response[i] = "yes";
				logger.debug("Keyboard Interactive response ["+ response[i] + "]");
				continue;
			}
			if (prompt[i].contains("assword:")|| prompt[i].contains("����")) {
				response[i] = passwd;
				logger.debug("Keyboard Interactive response ["+ response[i] + "]");
				continue;
			}

		}

		return response;
	}

}
