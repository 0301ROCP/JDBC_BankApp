package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.mybank.presentation.controller.Action;

public class FormBlock extends InteractionBlock{
	
	private ArrayList <Question> questions;
	
	//-----------CONSTRUCTOR---------
	
	public FormBlock() {
		this.blockType = BlockType.FORM;
		this.instructions = "Please enter the following information:";
		this.questions = new ArrayList<Question>();
	}
	
	//-----------GETTERS--------------
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	//-------------METHODS------------
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	
	@Override
	public Queue<Action> run() {
		Queue<Action> actionQueue = new LinkedList<Action>();
		
		HashMap<String,String> formAnswers = new HashMap<String,String>();
		
		for(Question q: questions) { //TODO not done!
			
			q.setFormList(formAnswers);
			
			String userAnswer = null;
			boolean valid = false;
			
			System.out.println(q.getQuestionText()); //print out the question			
			
			//TODO set max attempts, and action to take once reached
			while(!valid) {
				userAnswer = sc.nextLine();
				
				if(q.validate(userAnswer)) {
					valid = true;
				}
				
				else {
					System.out.println(q.getInvalidMessage());
				}	
			}
					
			q.handleData(userAnswer); 
			
			formAnswers = q.getFormList();
						
		}
		
		System.out.println("Form list: "+formAnswers);
		
		
		return actionQueue;
	}
	

}
