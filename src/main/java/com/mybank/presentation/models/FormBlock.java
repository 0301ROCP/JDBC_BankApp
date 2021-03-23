package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;

public class FormBlock extends InteractionBlock{
	
	private ArrayList <Question> questions;
	private String table;
	private String crudAction;
	boolean returnUser;
	
	//-----------CONSTRUCTOR---------
	
	public FormBlock(String table, String crudAction, boolean returnUser) {
		this.blockType = BlockType.FORM;
		this.instructions = "Please enter the following information:";
		this.actionQueue = new LinkedList<Action>();
		this.questions = new ArrayList<Question>();
		this.table = table;
		this.crudAction = crudAction;
		this.returnUser = returnUser;
	}
	
	//-----------GETTERS--------------
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	//-------------METHODS------------
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void addAction(Action action) {
		actionQueue.add(action);
	}
	
	
	@Override
	public Queue<Action> run(User currentUser) {
		
		HashMap<String, String> formAnswers = new HashMap<String,String>(); //answers to this form
		
		for(Question q: questions) { //for each question on the form
			
			q.setFormList(formAnswers); //feed in the current answers in case this question needs to reference them to validate a response
			
			String userAnswer = null;
			boolean valid = false;
			
			if(!q.isSystemQuestion()) {
				System.out.println(q.getQuestionText()); //print out the question			
				
				//TODO set max attempts, and action to take once reached
				while(!valid) {
					userAnswer = sc.nextLine(); //get the user's answer
					
					if(q.validate(userAnswer)) { //validate the answer
						valid = true;
					}
					
					else {
						System.out.println(q.getInvalidMessage()); 
					}	
				}			
			}
			
			else {
				userAnswer = q.getSystemValue();
				
				if(userAnswer.equals("#getuser_upi")) {
					userAnswer = String.valueOf(currentUser.getUpi());
				}
				valid = true;
			}
			
			q.handleData(userAnswer); 
			
			formAnswers = q.getFormList(); //update the form answers
						
		}
		
		//create a new user
		
		User resultUser = new User(); //we only need this if returning a setuser action
		

		switch (table){ //TODO hardcoded!
		case "users":
			AccessMgrImpl accessMgr = new AccessMgrImpl(new UserDaoImpl());
			
			resultUser = accessMgr.enterForm(formAnswers,crudAction);
			break;

		case "accounts":
			AcctMgrImpl accountMgr = new AcctMgrImpl(new AccountDaoImpl());
			accountMgr.enterForm(formAnswers, crudAction);
			break;
		}
		//TODO other cases
		
		
		if(returnUser) {
			actionQueue.add(new SetUser(resultUser));
		}
		
		
		
		return actionQueue;
	}
	

}
