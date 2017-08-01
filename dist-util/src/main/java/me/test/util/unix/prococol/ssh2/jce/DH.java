package me.test.util.unix.prococol.ssh2.jce;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

public class DH implements me.test.util.unix.prococol.ssh2.DH {
	BigInteger p;
	BigInteger g;
	BigInteger e; // my public key
	byte[] e_array;
	BigInteger f; // your public key
	BigInteger K; // shared secret key
	byte[] K_array;

	private KeyPairGenerator myKpairGen;
	private KeyAgreement myKeyAgree;

	public void init() throws Exception {
		myKpairGen = KeyPairGenerator.getInstance("DH");
		// myKpairGen=KeyPairGenerator.getInstance("DiffieHellman");
		myKeyAgree = KeyAgreement.getInstance("DH");
		// myKeyAgree=KeyAgreement.getInstance("DiffieHellman");
	}

	public byte[] getE() throws Exception {
		if (e == null) {
			DHParameterSpec dhSkipParamSpec = new DHParameterSpec(p, g);
			myKpairGen.initialize(dhSkipParamSpec);
			KeyPair myKpair = myKpairGen.generateKeyPair();
			myKeyAgree.init(myKpair.getPrivate());
			// BigInteger
			// x=((javax.crypto.interfaces.DHPrivateKey)(myKpair.getPrivate())).getX();
			@SuppressWarnings("unused")
			byte[] myPubKeyEnc = myKpair.getPublic().getEncoded();
			e = ((javax.crypto.interfaces.DHPublicKey) (myKpair.getPublic()))
					.getY();
			e_array = e.toByteArray();
		}
		return e_array;
	}

	public byte[] getK() throws Exception {
		if (K == null) {
			KeyFactory myKeyFac = KeyFactory.getInstance("DH");
			DHPublicKeySpec keySpec = new DHPublicKeySpec(f, p, g);
			PublicKey yourPubKey = myKeyFac.generatePublic(keySpec);
			myKeyAgree.doPhase(yourPubKey, true);
			byte[] mySharedSecret = myKeyAgree.generateSecret();
			K = new BigInteger(mySharedSecret);
			K_array = K.toByteArray();

			// System.out.println("K.signum(): "+K.signum()+
			// " "+Integer.toHexString(mySharedSecret[0]&0xff)+
			// " "+Integer.toHexString(K_array[0]&0xff));

			K_array = mySharedSecret;
		}
		return K_array;
	}

	public void setP(byte[] p) {
		setP(new BigInteger(p));
	}

	public void setG(byte[] g) {
		setG(new BigInteger(g));
	}

	public void setF(byte[] f) {
		setF(new BigInteger(f));
	}

	void setP(BigInteger p) {
		this.p = p;
	}

	void setG(BigInteger g) {
		this.g = g;
	}

	void setF(BigInteger f) {
		this.f = f;
	}
}
