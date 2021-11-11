package org.javacream.security.plain.crypt;

import static org.junit.jupiter.api.Assertions.fail;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class CipherTest{

	private static byte[] data;

	private static byte[] encrypted;

	private static SecretKey cipherKey;

	@BeforeAll
	public static void setUp() {
		data = "Hello, Security!".getBytes();
		KeyGenerator generator;
		try {
			generator = KeyGenerator.getInstance("AES");
			cipherKey = generator.generateKey();
			System.out.println(cipherKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
}

	
	@Test
	@Order(1)
	public void testEncrypt() {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, cipherKey);
			encrypted = cipher.doFinal(data);
			System.out.println(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}

	}

	@Test
	@Order(2)
	public void testDecrypt(){
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, cipherKey);
			byte[] decrypted = cipher.doFinal(encrypted);
			System.out.println(new String(decrypted));
			Assertions.assertEquals(new String(data),new String(decrypted));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
