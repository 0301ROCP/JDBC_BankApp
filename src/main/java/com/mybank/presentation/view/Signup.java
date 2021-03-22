package com.mybank.presentation.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.presentation.controller.Action;
import com.mybank.presentation.controller.Navigate;
import com.mybank.presentation.controller.SetUser;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.Question;
import com.mybank.service.access_mgt.AccessMgrImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;


public class Signup extends Page{
	
	//-------CONSTRUCTOR---------
	public Signup() {
		super();
		
		this.name = "Signup";
		this.header = "Signup Form";
		
		this.interactionBlock = new FormBlock();
		
		((FormBlock) this.interactionBlock).addQuestion(new Question("Please select a username"));
		((FormBlock) this.interactionBlock).addQuestion(new Question("Please select a password"));
		((FormBlock) this.interactionBlock).addQuestion(new Question("Please confirm your password"));
		
		
		//this.menu.addUtils();	
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
