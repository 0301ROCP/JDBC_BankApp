package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.mybank.presentation.controller.Operation;

public class Question {
	
	String questionText;
	String invalidMessage;
	Operation validator;
	Operation dataHandler;
	HashMap<String,String> formList;
	
	//--------CONSTRUCTOR------
	public Question(String questionText, String invalidMessage, Operation validator, Operation dataHandler) {
		this.questionText = questionText;
		this.invalidMessage = invalidMessage;
		this.validator = validator;
		this.dataHandler = dataHandler;
	}
	
	
	//---------GETTERS----------
	String getQuestionText() {
		return questionText;
	}

	String getInvalidMessage() {
		return invalidMessage;
	}
	
	Operation getValidator(){
		return validator;
	}
	
	Operation getDataHandler() {
		return dataHandler;
	}
	
	HashMap<String,String> getFormList(){
		return formList;
	}
	
	void setFormList(HashMap<String,String>formList) {
		this.formList = formList;
	}
	
	
	//---------TOSTRING---------
	


	@Override
	public String toString() {
		return "Question [questionText=" + questionText + ", invalidMessage=" + invalidMessage + ", validator="
				+ validator + ", handleData=" + dataHandler + "]";
	}
	
	
	//----------METHODS----------
	
	public boolean validate(String userAnswer) {
		
		boolean valid = false;
		
		validator.setUserAnswer(userAnswer);
		valid = validator.run();
		
		
		return valid;
	}

	
	public void handleData(String userAnswer) {
		
		dataHandler.setUserAnswer(userAnswer);
		
		dataHandler.run();
		
		formList.putAll(dataHandler.getPersistList());

	}
	
	
	
}
