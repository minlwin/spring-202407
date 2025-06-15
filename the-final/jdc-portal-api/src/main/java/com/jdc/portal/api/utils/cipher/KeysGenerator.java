package com.jdc.portal.api.utils.cipher;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeysGenerator {

	public static void generate() throws NoSuchAlgorithmException {
		var generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		
		var keyPair = generator.generateKeyPair();
		
		var publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		var privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
	
		System.out.println(publicKey);
		System.out.println(privateKey);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		generate();
	}
}
