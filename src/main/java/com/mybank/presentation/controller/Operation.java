package com.mybank.presentation.controller;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Operation {
	
	public enum OperationCategory {VERIFY_EXISTS,CONFIRM,ADD_TO_LIST};
	public OperationCategory category;
	
	String userAnswer;
	HashMap<String,String> toPersist;
	
	
	//--------CONSTRUCTOR-----------
	
	public Operation(OperationCategory category) {
		this.category = category;
		this.toPersist = new HashMap<String,String>();
	}

	
	//--------GETTERS & SETTERS---------
	
	public OperationCategory getCategory() {
		return category;
	}
	
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public HashMap<String,String> getPersistList(){
		return toPersist;
	}


	//---------METHODS----------
	
	public boolean run(){
		return false;
	}
}
