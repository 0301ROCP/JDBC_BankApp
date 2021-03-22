package com.mybank.presentation.view;


import java.util.Queue;
import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.presentation.controller.Action;
import com.mybank.presentation.controller.Navigate;
import com.mybank.presentation.controller.SetUser;
import com.mybank.presentation.models.Menu;
import com.mybank.service.access_mgt.AMImpl;

public class Login extends Page{
	
	//--------CONSTRUCTOR----------
	
	public Login() {
		super();
		
		this.name = "Login";
		this.header = "Please Log In!";
		this.instructions = "";
		this.menu = new Menu(instructions);
		
		//this menu has no buttons!
		
		//this.menu.addUtils(); //TODO fix this logic (cant click back or quit bc typing in pw)
		
	}

	//---------METHODS-----------
	@Override
	public void print() { //TODO make default print smarter: if no buttons, print this instead
		System.out.println(header);
		System.out.println();
	}
	
	@Override
	public Queue<Action> run() {
		Scanner sc = new Scanner(System.in);
		AMImpl accessMgr = new AMImpl();

		print();
		
		System.out.println("Please enter your username");
		String userName = sc.next();
		System.out.println("Please enter your password");
		String password = sc.next();
		
		User verifiedUser = accessMgr.attemptLogin(userName,password);
		Action nextPage;
		
		if(!(verifiedUser.equals(null))){ //if successful login
			Action setActiveUser = new SetUser(verifiedUser);
			nextPage = new Navigate("CustomerDB"); //TODO hardcoded!
			actionQueue.add(setActiveUser);
			actionQueue.add(nextPage);
		}

		else {
			//TODO
		}
		
		clear();
		
		return actionQueue;
	}

}
