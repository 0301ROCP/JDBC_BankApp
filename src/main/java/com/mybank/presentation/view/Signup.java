package com.mybank.presentation.view;


import com.mybank.presentation.controller.AddToList;
import com.mybank.presentation.controller.Confirm;
import com.mybank.presentation.controller.VerifyExists;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.Question;


public class Signup extends Page{
		
	//-------CONSTRUCTOR---------
	public Signup() {
		super();
		
		this.name = "Signup";
		this.header = "Signup Form";
		
		this.interactionBlock = new FormBlock();
				
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Please create a username:",
				"That username is already taken. Please create a different username:",
				new VerifyExists("users","username",true),
				new AddToList("username") //TODO
				));		
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Please create a password:",
				"Your passwords do not match. Please enter your password again:",
				new Confirm("Please confirm your password:"),
				new AddToList("password")
				));
	}
	
	
	//-------METHODS-----------
	
//	@Override
//	public Queue<Action> run() {
//		
//		Queue<Action> actionQueue = new LinkedList<Action>();
//		print(); //print this page
//		
//		//AcctMgrImpl accountMgr = new AcctMgrImpl();
//		AccessMgrImpl accessMgr = new AccessMgrImpl();
//		
//		//Ask what kind(s) of accounts they want
//		//Buttons: checking, savings, both, back, quit
//		//Create stack based on answer
//		
//		//loop: execute creation of accounts in the stack
//		
//		User newUser = accessMgr.createNewUser(true, false);
//		
//		Action setActiveUser = new SetUser(newUser);
//		Action nextPage = new Navigate("CustomerDB"); //TODO hardcoded!
//		
//		actionQueue.add(setActiveUser);
//		actionQueue.add(nextPage);
//		//add an action to navigate to 
//		
//		clear(); //clear the console
//		
//		//create a new action for newuser
//		//return the action
//		return actionQueue; 
//	}

}
