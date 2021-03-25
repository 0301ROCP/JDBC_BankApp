package com.mybank.tests;

import org.junit.Test;

import com.mybank.presentation.controller.operations.ValidateMoney;

import junit.framework.Assert;

public class ValidateMoneyTests {
	
	public static ValidateMoney moneyValidator = new ValidateMoney();

	
	@Test
	public void testNegative() {
		
		moneyValidator.setUserAnswer("-5");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("-33.20");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("33.20");
		
		Assert.assertTrue(moneyValidator.run());
	}
	
	
	@Test
	public void testNoDecimal() {
		
		moneyValidator.setUserAnswer("5");
		
		Assert.assertTrue(moneyValidator.run());
		
		moneyValidator.setUserAnswer("333333");
		
		Assert.assertTrue(moneyValidator.run());
		
		moneyValidator.setUserAnswer("00");
		
		Assert.assertTrue(moneyValidator.run());
	}
	
	@Test
	public void testOneDecimal() {
		
		moneyValidator.setUserAnswer("5.3");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("333333.0");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("00.0");
		
		Assert.assertFalse(moneyValidator.run());
	}
	
	@Test
	public void testTwoDecimal() {
		
		moneyValidator.setUserAnswer("5.33");
		
		Assert.assertTrue(moneyValidator.run());
		
		moneyValidator.setUserAnswer("333333.00");
		
		Assert.assertTrue(moneyValidator.run());
		
		moneyValidator.setUserAnswer("00.00");
		
		Assert.assertTrue(moneyValidator.run());
	}
	
	@Test
	public void testMoreThanTwoDecimals() {
		
		moneyValidator.setUserAnswer("5.333");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("333333.00000000000000000000");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("00.004");
		
		Assert.assertFalse(moneyValidator.run());
	}
	
	@Test
	public void testNonNumbers() {
		
		moneyValidator.setUserAnswer("hello");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("q");
		
		Assert.assertFalse(moneyValidator.run());
		
		moneyValidator.setUserAnswer("");
		
		Assert.assertFalse(moneyValidator.run());
	}
	
}
