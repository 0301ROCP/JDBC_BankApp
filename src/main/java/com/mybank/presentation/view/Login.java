package com.mybank.presentation.view;


import java.util.Queue;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.presentation.controller.operations.AddToList;
import com.mybank.presentation.controller.operations.VerifyExists;
import com.mybank.presentation.controller.operations.VerifyMatch;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.presentation.models.Question;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class Login extends Page{
	
	final static Logger Log = Logger.getLogger(Login.class);
	
	//--------CONSTRUCTOR----------
	
	public Login() {
		super();
		
		this.name = "Login";
		this.header = "Please Log In";
		
		this.interactionBlock = new FormBlock("users","read",true);
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Enter your username:",
				"That username is not in our system. Please enter your username:",
				new VerifyExists("users","username",false), 
				new AddToList("username") //add password to form info
				));
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"Enter your password:",
				"That password is incorrect. Please enter your password:",
				new VerifyMatch("users","user_password","username"),  //TODO
				new AddToList("user_password") //add password to form info
				));
		
		//this.interactionBlock = new FormBlock();
		
//		((FormBlock) this.interactionBlock).addQuestion(new Question("Enter your username:"));
//		((FormBlock) this.interactionBlock).addQuestion(new Question("Enter your password:"));
		
		//this menu has no buttons!
		
		//this.menu.addUtils(); //TODO fix this logic (cant click back or quit bc typing in pw)
		
	}

	//---------METHODS-----------
	
//	@Override
//	public Queue<Action> run(User currentUser){
//		
//		Log.debug("Login Page run()");
//		
//		print();		
//			
//		Queue<Action> actionQueue = interactionBlock.run(currentUser);
//		
//		//actionQueue.add(new Navigate("CustomerDB"));
//		
//		clear();
//		
//		return actionQueue;
//		
//	}
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
