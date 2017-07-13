package com.eastcom.ipnet.orders.protocol.ssh2.jce;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyPairGenRSA implements com.eastcom.ipnet.orders.protocol.ssh2.KeyPairGenRSA {
	byte[] d; // private
	byte[] e; // public
	byte[] n;

	byte[] c; // coefficient
	byte[] ep; // exponent p
	byte[] eq; // exponent q
	byte[] p; // prime p
	byte[] q; // prime q

	public void init(int key_size) throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(key_size, new SecureRandom());
		KeyPair pair = keyGen.generateKeyPair();

		PublicKey pubKey = pair.getPublic();
		PrivateKey prvKey = pair.getPrivate();

		d = ((RSAPrivateKey) prvKey).getPrivateExponent().toByteArray();
		e = ((RSAPublicKey) pubKey).getPublicExponent().toByteArray();
		n = ((RSAPrivateKey) prvKey).getModulus().toByteArray();

		c = ((RSAPrivateCrtKey) prvKey).getCrtCoefficient().toByteArray();
		ep = ((RSAPrivateCrtKey) prvKey).getPrimeExponentP().toByteArray();
		eq = ((RSAPrivateCrtKey) prvKey).getPrimeExponentQ().toByteArray();
		p = ((RSAPrivateCrtKey) prvKey).getPrimeP().toByteArray();
		q = ((RSAPrivateCrtKey) prvKey).getPrimeQ().toByteArray();
	}

	public byte[] getD() {
		return d;
	}

	public byte[] getE() {
		return e;
	}

	public byte[] getN() {
		return n;
	}

	public byte[] getC() {
		return c;
	}

	public byte[] getEP() {
		return ep;
	}

	public byte[] getEQ() {
		return eq;
	}

	public byte[] getP() {
		return p;
	}

	public byte[] getQ() {
		return q;
	}
}
