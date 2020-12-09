package by.itransition.ilearning.rcp_game;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;


public class Encryptor {

	private SecretKey secretKey;
	
	public Encryptor() throws NoSuchAlgorithmException {
		KeyGenerator g = KeyGenerator.getInstance("AES");
		SecureRandom s = new SecureRandom();
		int bitSize = 128;
		g.init(bitSize, s);
		this.secretKey = g.generateKey();
	}

	public  byte[] getHmac(String move) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKey);
		mac.update(move.getBytes());
		return mac.doFinal();
	}
	
	public String bytesToHex(byte[] bytes) {   
		BigInteger result = new BigInteger(1, bytes);
		return result.toString(16);
	}
	
	public SecretKey getKey() {
		return secretKey;
	}
	
}
