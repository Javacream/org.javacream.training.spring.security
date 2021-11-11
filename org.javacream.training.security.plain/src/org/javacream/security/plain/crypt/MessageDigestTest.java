package org.javacream.security.plain.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MessageDigestTest{

	@Test
	public void testShowAlgorithms(){
		Set<String> algorithms = Security.getAlgorithms("MessageDigest");
		System.out.println(algorithms);
	}
	@Test
	public void testMD5() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] digest = messageDigest.digest("Hello".getBytes());
			System.out.println(messageDigest.getProvider());
			System.out.println("MD5:\t" + new String(digest));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testSHA1() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");
			byte[] digest = messageDigest.digest("Hello".getBytes());
			System.out.println(messageDigest.getProvider());
			System.out.println("SHA1:\t" + new String(digest));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	@Test
	public void testSHA512() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			byte[] digest = messageDigest.digest("Hello".getBytes());
			System.out.println(messageDigest.getProvider());
			System.out.println("SHA-512:\t" + new String(digest));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}

}
