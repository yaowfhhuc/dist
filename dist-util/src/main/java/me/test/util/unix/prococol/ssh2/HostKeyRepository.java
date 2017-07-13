package me.test.util.unix.prococol.ssh2;

public interface HostKeyRepository {
	final int OK = 0;
	final int NOT_INCLUDED = 1;
	final int CHANGED = 2;

	int check(String host, byte[] key);

	void add(String host, byte[] key, UserInfo ui);

	void remove(String host, String type);

	void remove(String host, String type, byte[] key);

	String getKnownHostsRepositoryID();

	HostKey[] getHostKey();

	HostKey[] getHostKey(String host, String type);
}
