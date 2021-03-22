package com.mybank.presentation.models;

public class Question {
	
	String questionText;
	
	//--------CONSTRUCTOR------
	public Question(String questionText) {
		this.questionText = questionText;
	}
	
	
	//---------GETTERS----------
	String getQuestionText() {
		return questionText;
	}


	
	
	//---------TOSTRING---------
	@Override
	public String toString() {
		return "Question [questionText=" + questionText + "]";
	}
	
	
}
