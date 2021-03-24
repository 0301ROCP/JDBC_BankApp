package com.mybank.presentation.controller.operations;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.presentation.models.Question;

public class Confirm extends Operation{
	
	final static Logger Log = Logger.getLogger(Question.class);
	
	String prompt;

	public Confirm(String prompt) {
		super(OperationCategory.CONFIRM);
		this.prompt = prompt;
	}
	
	@Override
	public boolean run() {
		
		Log.debug("Confirm run()");
		
		boolean match = false;
		
		if(!userAnswer.equals("")) {
		
			Scanner sc = new Scanner(System.in);
			System.out.println(prompt);
			
			String result = sc.next();
			Log.debug("User entered: " + result);
			
			if(result.equals(userAnswer)) {
				match = true;
			}
			
			return match;	
		}
		
		else {
			System.out.println("This field cannot be left blank.");
			Log.debug("Return false; field left blank");
			return false;
		}
				
	}

}
