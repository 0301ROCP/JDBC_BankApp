package com.mybank.presentation.controller.operations;

import java.util.Scanner;

public class Confirm extends Operation{
	
	String prompt;

	public Confirm(String prompt) {
		super(OperationCategory.CONFIRM);
		this.prompt = prompt;
	}
	
	@Override
	public boolean run() {
		
		boolean match = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println(prompt);
		
		String result = sc.next();
		
		if(result.equals(userAnswer)) {
			match = true;
		}
		
		return match;
		
	}

}
