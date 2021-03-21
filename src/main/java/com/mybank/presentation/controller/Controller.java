package com.mybank.presentation.controller;


import java.util.HashMap;
import java.util.Stack;

import com.mybank.models.User;
import com.mybank.presentation.view.*;


public class Controller {
	
	HashMap<String, Page> siteMap;	
	Stack<Page> history;
	private User currentUser;
	
	//-------CONSTRUCTOR--------
	
	public Controller() {
		
		Page welcomePage = new Welcome();
		Page loginPage = new Login();
		Page customerDB = new CustomerDB();
		Page employeeDB = new EmployeeDB();
		Page logoutPage = new Logout();
		Page signupPage = new Signup();
		Page guestPage = new Guest();
		Page blankPage = new Blank();
				
		this.siteMap = new HashMap<String, Page>();
		siteMap.put("Welcome",welcomePage);
		siteMap.put("Login",loginPage);
		siteMap.put("CustomerDB", customerDB);
		siteMap.put("EmployeeDB", employeeDB);
		siteMap.put("Logout", logoutPage);
		siteMap.put("Signup", signupPage);
		siteMap.put("Guest", guestPage);
		siteMap.put("Blank", blankPage);
		
		this.history = new Stack<Page>();
		
	}

	
	//--------GETTERS & SETTERS------
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	//--------METHODS-------
	
	public void start() {		
		runApp(siteMap.get("Welcome"));		
	}
	
	public void runApp(Page thisPage) {
		
		String nextPage = thisPage.run(); //print this page and tell me what page to go to next
		
		//nextPage will point to either a page or an action
		//if a page, go to the page
		//if an action, call the action from the service layer
		
		if(nextPage.equals("Quit")){
			System.out.println("Application Terminated");
		}
		else if(nextPage.equals("Back")){ //TODO skip login pages on backs
			Page prevPage = history.pop();
			String prevName = prevPage.getName();
			runApp(siteMap.get(prevName));
		}
		else {
			history.push(thisPage);
			runApp(siteMap.get(nextPage));
		}
	}	
}
