package me.test.util.unix.prococol.ssh2;

public class HostKey {
	private static final byte[] sshdss = "ssh-dss".getBytes();
	private static final byte[] sshrsa = "ssh-rsa".getBytes();

	public static final int SSHDSS = 0;
	public static final int SSHRSA = 1;
	static final int UNKNOWN = 2;

	String host;
	int type;
	byte[] key;

	public HostKey(String host, byte[] key) throws SshException {
		this.host = host;
		this.key = key;
		if (key[8] == 'd') {
			this.type = SSHDSS;
		} else if (key[8] == 'r') {
			this.type = SSHRSA;
		} else {
			throw new SshException("invalid key type");
		}
	}

	HostKey(String host, int type, byte[] key) {
		this.host = host;
		this.type = type;
		this.key = key;
	}

	public String getHost() {
		return host;
	}

	public String getType() {
		if (type == SSHDSS) {
			return new String(sshdss);
		}
		if (type == SSHRSA) {
			return new String(sshrsa);
		}
		return "UNKNOWN";
	}

	public String getKey() {
		return new String(Util.toBase64(key, 0, key.length));
	}

	public String getFingerPrint(JSch jsch) {
		HASH hash = null;
		try {
			Class c = Class.forName(jsch.getConfig("md5"));
			hash = (HASH) (c.newInstance());
		} catch (Exception e) {
			System.err.println("getFingerPrint: " + e);
		}
		return Util.getFingerPrint(hash, key);
	}
}
