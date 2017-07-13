package me.test.util.unix.command;

public enum TELNETTYPE {
	SSH(1), SSH2(2), TELNET(3), JUMPER(4);
	private int value = 0;

	private TELNETTYPE(int i) {
		this.value = i;
	}

	public int value() {
		return this.value;
	}

	public static TELNETTYPE valueOf(int value) {
		switch (value) {
		case 1:
			return SSH;
		case 2:
			return SSH2;
		case 3:
			return TELNET;
		case 4:
			return JUMPER;
		default:
			return null;
		}
	}
}
