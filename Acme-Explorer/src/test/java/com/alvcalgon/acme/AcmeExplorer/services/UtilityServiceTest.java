package com.alvcalgon.acme.AcmeExplorer.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@SpringBootTest
public class UtilityServiceTest {

	@Autowired
	private UtilityService utilityService;

	@Test
	public void hashTextTest1() {
		String input = ConstantPool.BLANK;

		String result = utilityService.getTextEncoded(input);
		System.out.println("test 1: " + result);

		assertTrue(ConstantPool.BLANK.equals(result));
	}

	@Test
	public void hashTextTest2() {
		String input = "alvarogoles";

		String result = utilityService.getTextEncoded(input);
		System.out.println("test 2: " + result);

		assertNotNull(result);
	}

	@Test
	public void hashTextTest3() {
		String input = "alvarogoles";

		String result = utilityService.getTextEncoded(input);
		System.out.println("test 3: " + result);

		assertNotNull(result);
	}
}
