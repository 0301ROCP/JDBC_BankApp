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
import com.mybank.presentation.models.Menu;
import com.mybank.service.access_mgt.AMImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;


public class Signup extends Page{
	
	//-------CONSTRUCTOR---------
	public Signup() {
		this.instructions = "Please enter your information below to create an account.";
		this.menu = new Menu(instructions);
		
		this.name = "Signup";
		this.header = "Account Signup Form";
		
		//this menu has no buttons!!
		
		//this.menu.addUtils();	
	}
	
	
	//-------METHODS-----------
	@Override
	public void print() { //TODO make default print smarter: if no buttons, print this instead
		System.out.println(header);
		System.out.println();
		System.out.println(instructions);
	}
	
	@Override
	public Queue<Action> run() {
		
		Queue<Action> actionQueue = new LinkedList<Action>();
		print(); //print this page
		
		//AcctMgrImpl accountMgr = new AcctMgrImpl();
		AMImpl accessMgr = new AMImpl();
		
		//Ask what kind(s) of accounts they want
		//Buttons: checking, savings, both, back, quit
		//Create stack based on answer
		
		//loop: execute creation of accounts in the stack
		
		User newUser = accessMgr.createNewUser(true, false);
		
		Action setActiveUser = new SetUser(newUser);
		Action nextPage = new Navigate("CustomerDB"); //TODO hardcoded!
		
		actionQueue.add(setActiveUser);
		actionQueue.add(nextPage);
		//add an action to navigate to 
		
		clear(); //clear the console
		
		//create a new action for newuser
		//return the action
		return actionQueue; 
	}

}
