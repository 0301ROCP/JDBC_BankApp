package com.mybank.presentation.view;


import java.util.Queue;
import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.Question;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class Login extends Page{
	
	//--------CONSTRUCTOR----------
	
	public Login() {
		super();
		
		this.name = "Login";
		this.header = "Login Page";
		
		this.interactionBlock = new FormBlock();
		
//		((FormBlock) this.interactionBlock).addQuestion(new Question("Enter your username:"));
//		((FormBlock) this.interactionBlock).addQuestion(new Question("Enter your password:"));
		
		//this menu has no buttons!
		
		//this.menu.addUtils(); //TODO fix this logic (cant click back or quit bc typing in pw)
		
	}

	//---------METHODS-----------
//	@Override
//	public void print() { //TODO make default print smarter: if no buttons, print this instead
//		System.out.println(header);
//		System.out.println();
//	}
	
//	@Override
//	public Queue<Action> run() {
////		Scanner sc = new Scanner(System.in);
////		AccessMgrImpl accessMgr = new AccessMgrImpl();
////
////		print();
////		
////		System.out.println("Please enter your username");
////		String userName = sc.next();
////		System.out.println("Please enter your password");
////		String password = sc.next();
////		
////		User verifiedUser = accessMgr.attemptLogin(userName,password);
////		Action nextPage;
////		
////		if(!(verifiedUser.equals(null))){ //if successful login
////			Action setActiveUser = new SetUser(verifiedUser);
////			nextPage = new Navigate("CustomerDB"); //TODO hardcoded!
////			actionQueue.add(setActiveUser);
////			actionQueue.add(nextPage);
////		}
////
////		else {
////			//TODO
////		}
////		
////		clear();
////		
////		return actionQueue;
//	}

}
