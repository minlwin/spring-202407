package com.jdc.portal.api.utils.cipher;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

import com.jdc.portal.api.utils.exceptions.ApiSystemException;

@Service
public class PasswordCipherUtils {
	
	private static PasswordSecret secret;
	
	public PasswordCipherUtils(PasswordSecret _secret) {
		secret = _secret;
	}

	public static String encrypt(String password) {
		try {
			var cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
			var bytes = cipher.doFinal(password.getBytes());
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			throw new ApiSystemException(e);
		}
	}

	public static String decrypt(String password) {
		try {
			var cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
			var bytes = cipher.doFinal(Base64.getDecoder().decode(password));
			return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiSystemException(e);
		}
	}

	private static Key getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		var bytes = Base64.getDecoder().decode(secret.getPublicKey());
		var keySpec = new X509EncodedKeySpec(bytes);
		var keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}
	
	private static Key getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		var bytes = Base64.getDecoder().decode(secret.getPrivateKey());
		var keySpec = new PKCS8EncodedKeySpec(bytes);
		var keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}
	
}
