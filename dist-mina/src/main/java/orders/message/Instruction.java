package orders.message;

import java.io.Serializable;

public class Instruction implements Serializable {

	private static final long serialVersionUID = 3256718477086767408L;

	private String command;

	private int net_delay;

	private String prompt;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getNet_delay() {
		return net_delay;
	}

	public void setNet_delay(int net_delay) {
		this.net_delay = net_delay;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

}
