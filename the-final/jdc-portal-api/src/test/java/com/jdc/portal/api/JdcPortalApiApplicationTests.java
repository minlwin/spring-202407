package com.jdc.portal.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.portal.api.utils.cipher.PasswordCipherUtils;

@SpringBootTest
class JdcPortalApiApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(PasswordCipherUtils.encrypt("niwLniMw@Z#20250615"));
		System.out.println(PasswordCipherUtils.encrypt("hello@123"));
	}

}
