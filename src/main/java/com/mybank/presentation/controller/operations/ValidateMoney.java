package com.mybank.presentation.controller.operations;

import java.math.BigDecimal;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ValidateMoney extends Operation{
	
	final static Logger Log = Logger.getLogger(ValidateMoney.class);
	
	//------------CONSTRUCTOR------------

	public ValidateMoney() {
		super(OperationCategory.VALIDATE_MONEY);
	}

	
	//-------------METHODS--------------
	
	@Override
	public boolean run() {
		
		//Log.setLevel(Level.DEBUG);
		
		boolean valid = false;
		double moneyDouble = 0;
		
		try {
			moneyDouble = Double.parseDouble(userAnswer); //make sure it's formatted like a number
		}
		catch(Exception e) {
			return false;
		}
		
		if(moneyDouble < 0) { //make sure it's positive
			return false;
		}
		
		int decIndex = userAnswer.indexOf('.');
		Log.debug("decIndex = " + decIndex);

		
		if(decIndex<0) { //if no decimal
			valid = true;
			Log.debug("<0, return true");
		}
		else {
			
			int decSlot = decIndex + 1; //basing off 1 instead of 0 so my brain stops hurting
			int stringLength = userAnswer.length(); //this is already 1-based
			int rootLength = decSlot-1; //length of whole number
			int decLength = (stringLength-rootLength)-1; //length of decimal
			
			Log.debug("decSlot = " + decSlot);
			Log.debug("stringLength = " + stringLength);
			Log.debug("rootLength = " + rootLength);
			Log.debug("decLength = " + decLength);
			
			if(decLength == 2) {
				valid = true;
				Log.debug("declength is 2, return true");
			}
		}
		
//		if(moneyDouble >= 0) { //value must be >= 0
//			
//			double testRound = moneyDouble.round()
//			
//			double testCents = moneyDouble*100.0;
//			Log.debug("testCents = "+testCents);
//			double testCentsRounded = Math.floor(testCents);
//			Log.debug("testCentsFloor = " + testCentsFloor);
//			if(testCents - testCentsFloor == 0) { //value must have at most 2 decimal places
//				valid = true;
//			}
//		}
		
		return valid;
		
	}
	
}
