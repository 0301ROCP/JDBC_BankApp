package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
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
		
		for(Question q: questions) { //TODO not done!
			System.out.println(q);
			String thisAnswer = sc.nextLine();
		}
		
		
		return actionQueue;
	}
	

}
