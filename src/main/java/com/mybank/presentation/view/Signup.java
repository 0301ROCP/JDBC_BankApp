package com.mybank.presentation.view;


import java.util.LinkedList;
import java.util.Queue;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.operations.AddToList;
import com.mybank.presentation.controller.operations.Confirm;
import com.mybank.presentation.controller.operations.VerifyExists;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.Question;


public class Signup extends Page{
	
	User newUser;
		
	//-------CONSTRUCTOR---------
	public Signup() {
		super();
		
		this.name = "Signup";
		this.header = "Signup Form";
		
		this.interactionBlock = new FormBlock("users","create",true); //create a new FormBlock that corresponds to users table
				
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Please enter your first name:",
				null,
				null, 
				new AddToList("first_name") //add password to form info
				));
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Please enter your last name:",
				null,
				null, 
				new AddToList("last_name") //add password to form info
				));
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question( //add a new question to this FormBlock
				"Please create a username:",
				"That username is already taken. Please create a different username:",
				new VerifyExists("users","username",true), //verify that this username is not already in the database
				new AddToList("username") //add username to form info
				));		
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Please create a password:",
				"Your passwords do not match. Please enter your password again:",
				new Confirm("Please confirm your password:"), //enter password again to confirm match
				new AddToList("user_password") //add password to form info
				));

	}
	
	
	//-------METHODS-----------
	
	@Override
	public Queue<Action> run(User currentUser){
		print();		
			
		Queue<Action> actionQueue = interactionBlock.run(currentUser);
		
		actionQueue.add(new Navigate("SelectAccounts"));
		
		clear();
		
		return actionQueue;
		
	}
	
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
