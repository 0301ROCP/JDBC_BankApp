package com.mybank.presentation.controller;


import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.presentation.view.CustomerDB;
import com.mybank.presentation.view.Login;
import com.mybank.presentation.view.Page;
import com.mybank.presentation.view.Signup;
import com.mybank.presentation.view.Welcome;


public class Controller {
	
	HashMap<String, Page> siteMap;	
	Stack<Page> history;
	private User currentUser;
	private Queue<Action> mainActionQueue;
	
	//TODO add hover-over explanations of methods?
	
	//-------CONSTRUCTOR--------
		public Controller() {
		
		Page welcomePage = new Welcome();
		Page loginPage = new Login();
		Page customerDB = new CustomerDB();
//		Page employeeDB = new EmployeeDB();
//		Page logoutPage = new Logout();
		Page signupPage = new Signup();
//		Page guestPage = new Guest();
//		Page blankPage = new Blank();
				
		this.siteMap = new HashMap<String, Page>();
		siteMap.put("Welcome",welcomePage);
		siteMap.put("Login",loginPage);
		siteMap.put("CustomerDB", customerDB);
////		siteMap.put("EmployeeDB", employeeDB);
////		siteMap.put("Logout", logoutPage);
		siteMap.put("Signup", signupPage);
//		siteMap.put("Guest", guestPage);
//		siteMap.put("Blank", blankPage);
		
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
		System.out.println("---------Running start------------");
		runApp(siteMap.get("Welcome"));		
		System.out.println("----------Leaving start----------");
	}
	
	
	public void runApp(Page thisPage) {
		
		System.out.println("------Running page "+ thisPage.getName()+"-----------"); //TEMP
		
		Queue<Action> actionQueue = thisPage.run(); //print this page and give me a queue of actions to do next
		
		System.out.println("Retrieved "+ actionQueue.size() + " action(s)"); //TEMP
				
		while(!actionQueue.isEmpty()) { //while there are still actions in the stack
			
			Action thisAction = actionQueue.poll(); //get the next action
			System.out.println("This action is: "+ thisAction);
			
			
			switch(thisAction.getCategory()) {
//			
			case NAVIGATE:
//				//TODO if this isn't the last thing in the stack and the new page has new instructions, what do??
				String target = ((Navigate) thisAction).getTarget(); //get the target String of the page we're navigating to
				history.push(thisPage); //add the current page to the history stack
				Page nextPage = siteMap.get(target);
				runApp(nextPage); //run the new page
				break;
//			
////			case FORM:
////				System.out.println("Fill out a form!"); //TEMP
////				break;
//			
			case SETUSER:

				User newUser = ((SetUser) thisAction).getUser();
				currentUser = newUser;
				System.out.println("current user: "+currentUser);
				break;
			}
			
			//execute this action
		}
		
		System.out.println("-------Leaving run of page " + thisPage.getName()+"---------");
		
//		if(nextPage.equals("Quit")){
//			System.out.println("Application Terminated");
//		}
//		else if(nextPage.equals("Back")){ //TODO skip login pages on backs
//			Page prevPage = history.pop();
//			String prevName = prevPage.getName();
//			runApp(siteMap.get(prevName));
//		}
//		else {
//			history.push(thisPage);
//			runApp(siteMap.get(nextPage));
//		}
	}	
}
