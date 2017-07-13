package me.test.util.unix.prococol.ssh2;

interface Identity {
	boolean setPassphrase(String passphrase) throws SshException;

	byte[] getPublicKeyBlob();

	byte[] getSignature(byte[] data);

	boolean decrypt();

	String getAlgName();

	String getName();

	public boolean isEncrypted();
}
