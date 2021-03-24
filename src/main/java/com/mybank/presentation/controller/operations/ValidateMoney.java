package com.mybank.presentation.controller.operations;

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
		
		boolean valid = false;
		
		double moneyDouble = Double.parseDouble(userAnswer);
		
		if(moneyDouble >= 0) { //value must be >= 0
			
			double testCents = moneyDouble*100;
			double testCentsFloor = Math.floor(testCents);
			
			if(testCents - testCentsFloor == 0) { //value must have at most 2 decimal places
				valid = true;
			}
		}
		
		return valid;
		
	}
	
}
