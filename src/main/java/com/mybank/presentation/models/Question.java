package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mybank.presentation.controller.Controller;
import com.mybank.presentation.controller.operations.Operation;

public class Question {
	
	final static Logger Log = Logger.getLogger(Question.class);
	
	String questionText;
	String invalidMessage;
	Operation validator;
	Operation dataHandler;
	HashMap<String,String> formList;
	boolean systemQuestion;
	String systemValue;
	
	//--------CONSTRUCTOR------
	public Question(String questionText, String invalidMessage, Operation validator, Operation dataHandler) {
		this.questionText = questionText;
		this.invalidMessage = invalidMessage;
		this.validator = validator;
		this.dataHandler = dataHandler;
		this.systemQuestion = false;
	}
	
	public Question(Operation dataHandler, boolean systemQuestion, String systemValue) {
		this.dataHandler = dataHandler;
		this.systemQuestion = systemQuestion;
		this.systemValue = systemValue;
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
	
	void setFormList(HashMap<String, String> formAnswers) {
		this.formList = formAnswers;
	}
	
	boolean isSystemQuestion() {
		return this.systemQuestion;
	}
	
	String getSystemValue() {
		return systemValue;
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
		
		Log.debug("Validator: " + validator);
		
		if(validator != null) {
			validator.setUserAnswer(userAnswer);
			validator.setCurrentList(formList);
			valid = validator.run();
		}
		else {
			valid = true;
		}
		
		return valid;
	}

	
	public void handleData(String userAnswer) {
		
		dataHandler.setUserAnswer(userAnswer);
		
		dataHandler.run();
		
		formList.putAll(dataHandler.getPersistList());

	}
	
	
	
}
