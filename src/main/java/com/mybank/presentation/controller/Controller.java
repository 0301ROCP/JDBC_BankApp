package com.mybank.presentation.controller;


import java.util.HashMap;
import java.util.Stack;

import com.mybank.presentation.view.*;



public class Controller {
	
	HashMap<String, Page> siteMap;	
	Stack<Page> history;
	
	//CONSTRUCTOR
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

	public void start() {		
		runApp(siteMap.get("Welcome"));		
	}
	
	public void runApp(Page thisPage) {
		
		String nextPage = thisPage.run();
		
		if(nextPage.equals("Quit")){
			System.out.println("Application Terminated");
		}
		else if(nextPage.equals("Back")){ //TODO make Back work better
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
