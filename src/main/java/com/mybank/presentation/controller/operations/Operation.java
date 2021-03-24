package com.mybank.presentation.controller.operations;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Operation {
	
	public enum OperationCategory {VERIFY_EXISTS, CONFIRM, ADD_TO_LIST, VERIFY_MATCH, VALIDATE_MONEY};
	
	public OperationCategory category;
	String userAnswer;
	HashMap<String,String> toPersist;
	HashMap<String,String> currentList;
	
	
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

	public void setCurrentList(HashMap<String,String> currentList) {
		this.currentList = currentList;
	}

	
	//------------TOSTRING------------
	
	@Override
	public String toString() {
		return "Operation [category=" + category + ", userAnswer=" + userAnswer + "]";
	}
	
	//---------METHODS----------
	
	public boolean run(){
		System.out.println("Default method! You shouldn't be here");
		return false;
	}



}
