package com.global.translator.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptoUtilsTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void addTwoNumbers() {
		
		CryptoUtils cryptoUtils=new CryptoUtils();
		
		assertEquals(8,cryptoUtils.addTwoNumbers(4, 4));
	}
	

}
