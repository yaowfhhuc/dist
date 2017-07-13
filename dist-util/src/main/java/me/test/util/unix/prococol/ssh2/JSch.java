package me.test.util.unix.prococol.ssh2;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class JSch {
	static Hashtable<String, String> config = new Hashtable();
	static {
		// config.put("kex", "diffie-hellman-group-exchange-sha1");
		config
				.put("kex",
						"diffie-hellman-group1-sha1,diffie-hellman-group-exchange-sha1");
		config.put("server_host_key", "ssh-rsa,ssh-dss");
		// config.put("server_host_key", "ssh-dss,ssh-rsa");

		config.put("cipher.s2c", "3des-cbc,blowfish-cbc");
		config.put("cipher.c2s", "3des-cbc,blowfish-cbc");

		config.put("mac.s2c", "hmac-md5,hmac-sha1,hmac-sha1-96,hmac-md5-96");
		config.put("mac.c2s", "hmac-md5,hmac-sha1,hmac-sha1-96,hmac-md5-96");
		config.put("compression.s2c", "none");
		config.put("compression.c2s", "none");
		config.put("lang.s2c", "");
		config.put("lang.c2s", "");

		config.put("compression_level", "6");

		config.put("diffie-hellman-group-exchange-sha1",
				"com.eastcom.ipnet.orders.protocol.ssh2.DHGEX");
		config.put("diffie-hellman-group1-sha1",
				"com.eastcom.ipnet.orders.protocol.ssh2.DHG1");

		config.put("dh", "com.eastcom.ipnet.orders.protocol.ssh2.jce.DH");
		config.put("3des-cbc", "com.eastcom.ipnet.orders.protocol.ssh2.jce.TripleDESCBC");
		config.put("blowfish-cbc",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.BlowfishCBC");
		config.put("hmac-sha1", "com.eastcom.ipnet.orders.protocol.ssh2.jce.HMACSHA1");
		config.put("hmac-sha1-96",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.HMACSHA196");
		config.put("hmac-md5", "com.eastcom.ipnet.orders.protocol.ssh2.jce.HMACMD5");
		config.put("hmac-md5-96", "com.eastcom.ipnet.orders.protocol.ssh2.jce.HMACMD596");
		config.put("sha-1", "com.eastcom.ipnet.orders.protocol.ssh2.jce.SHA1");
		config.put("md5", "com.eastcom.ipnet.orders.protocol.ssh2.jce.MD5");
		config.put("signature.dss",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.SignatureDSA");
		config.put("signature.rsa",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.SignatureRSA");
		config.put("keypairgen.dsa",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.KeyPairGenDSA");
		config.put("keypairgen.rsa",
				"com.eastcom.ipnet.orders.protocol.ssh2.jce.KeyPairGenRSA");
		config.put("random", "me.test.util.unix.util.Random");

		config.put("none", "com.eastcom.ipnet.orders.protocol.ssh2.CipherNone");

		config.put("aes128-cbc", "com.eastcom.ipnet.orders.protocol.ssh2.jce.AES128CBC");
		config.put("aes192-cbc", "com.eastcom.ipnet.orders.protocol.ssh2.jce.AES192CBC");
		config.put("aes256-cbc", "com.eastcom.ipnet.orders.protocol.ssh2.jce.AES256CBC");
		config.put("aes128-ctr", "com.eastcom.ipnet.orders.protocol.ssh2.jce.AES128CTR");
		config.put("cipher.s2c", "aes128-cbc,aes128-ctr,3des-cbc,blowfish-cbc");
		config.put("cipher.c2s", "aes128-cbc,aes128-ctr,3des-cbc,blowfish-cbc");

		config.put("zlib", "com.eastcom.ipnet.orders.protocol.ssh2.Compression");

		config.put("StrictHostKeyChecking", "ask");
	}
	Vector<Session> pool = new Vector<Session>();
	Vector<Identity> identities = new Vector<Identity>();

	private Vector proxies;
	// private KnownHosts known_hosts=null;
	private HostKeyRepository known_hosts = null;

	public JSch() {
		// known_hosts=new KnownHosts(this);
	}

	public Session getSession(String username, String host) throws SshException {
		return getSession(username, host, 22);
	}

	public Session getSession(String username, String host, int port)
			throws SshException {
		Session s = new Session(this);
		s.setLogin(username);
		s.setHost(host);
		s.setPort(port);
		pool.addElement(s);
		return s;
	}

	protected boolean removeSession(Session session) {
		synchronized (pool) {
			return pool.remove(session);
		}
	}

	public void setHostKeyRepository(HostKeyRepository foo) {
		known_hosts = foo;
	}

	public void setKnownHosts(String foo) throws SshException {
		if (known_hosts == null)
			known_hosts = new KnownHosts(this);
		if (known_hosts instanceof KnownHosts) {
			synchronized (known_hosts) {
				((KnownHosts) known_hosts).setKnownHosts(foo);
			}
		}
	}

	public void setKnownHosts(InputStream foo) throws SshException {
		if (known_hosts == null)
			known_hosts = new KnownHosts(this);
		if (known_hosts instanceof KnownHosts) {
			synchronized (known_hosts) {
				((KnownHosts) known_hosts).setKnownHosts(foo);
			}
		}
	}

	/*
	 * HostKeyRepository getKnownHosts(){ if(known_hosts==null) known_hosts=new
	 * KnownHosts(this); return known_hosts; }
	 */
	public HostKeyRepository getHostKeyRepository() {
		if (known_hosts == null)
			known_hosts = new KnownHosts(this);
		return known_hosts;
	}

	/*
	 * public HostKey[] getHostKey(){ if(known_hosts==null) return null; return
	 * known_hosts.getHostKey(); } public void removeHostKey(String foo, String
	 * type){ removeHostKey(foo, type, null); } public void removeHostKey(String
	 * foo, String type, byte[] key){ if(known_hosts==null) return;
	 * known_hosts.remove(foo, type, key); }
	 */
	public void addIdentity(String foo) throws SshException {
		addIdentity(foo, (String) null);
	}

	public void addIdentity(String foo, String bar) throws SshException {
		Identity identity = new IdentityFile(foo, this);
		if (bar != null) {
			identity.setPassphrase(bar);
		}
		synchronized (identities) {
			if (!identities.contains(identity)) {
				identities.addElement(identity);
			}
		}
	}

	 String getConfig(String foo) {
		return (String) (config.get(foo));
	}

	void setProxy(String hosts, Proxy proxy) {
		java.lang.String[] patterns = Util.split(hosts, ",");
		if (proxies == null) {
			proxies = new java.util.Vector();
		}
		synchronized (proxies) {
			for (int i = 0; i < patterns.length; i++) {
				if (proxy == null) {
					proxies.insertElementAt(null, 0);
					proxies.insertElementAt(patterns[i].getBytes(), 0);
				} else {
					proxies.addElement(patterns[i].getBytes());
					proxies.addElement(proxy);
				}
			}
		}
	}

	Proxy getProxy(String host) {
		if (proxies == null)
			return null;
		byte[] _host = host.getBytes();
		synchronized (proxies) {
			for (int i = 0; i < proxies.size(); i += 2) {
				if (Util.glob(((byte[]) proxies.elementAt(i)), _host)) {
					return (Proxy) (proxies.elementAt(i + 1));
				}
			}
		}
		return null;
	}

	void removeProxy() {
		proxies = null;
	}

	public static void setConfig(Hashtable foo) {
		synchronized (config) {
			for (Enumeration e = foo.keys(); e.hasMoreElements();) {
				String key = (String) (e.nextElement());
				config.put(key, (String) (foo.get(key)));
			}
		}
	}
}
