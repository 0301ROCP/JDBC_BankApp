package com.mybank.presentation.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.presentation.view.ApproveIndiv;
import com.mybank.presentation.view.ApproveMain;
import com.mybank.presentation.view.CreateAccount;
import com.mybank.presentation.view.CustomerDB;
import com.mybank.presentation.view.DepositWithdrawal;
import com.mybank.presentation.view.EmployeeDB;
import com.mybank.presentation.view.Login;
import com.mybank.presentation.view.Logout;
import com.mybank.presentation.view.MakeTransfer;
import com.mybank.presentation.view.Page;
import com.mybank.presentation.view.SelectAccounts;
import com.mybank.presentation.view.Signup;
import com.mybank.presentation.view.TransferApprove;
import com.mybank.presentation.view.TransferExternal;
import com.mybank.presentation.view.TransferInternal;
import com.mybank.presentation.view.ViewAccounts;
import com.mybank.presentation.view.ViewLogs;
import com.mybank.presentation.view.Welcome;


public class Controller {
	
	final static Logger Log = Logger.getLogger(Controller.class);
	
	HashMap<String, Page> siteMap;	
	Stack<Page> history;
	private User currentUser;
	
	//private Welcome welcome;
	
	//TODO add hover-over explanations of methods?
	
	//-------CONSTRUCTOR--------
	public Controller() {
		
			
		this.siteMap = new HashMap<String, Page>();
		this.history = new Stack<Page>();
		
		
		try {
			//Page welcomePage = welcome;
//			Page loginPage = new Login();
//			Page customerDB = new CustomerDB();
//			Page employeeDB = new EmployeeDB();
//			Page logoutPage = new Logout();
//			Page signupPage = new Signup();
////			Page guestPage = new Guest();
//	//		Page blankPage = new Blank();
//			
//			Page createSavingsPage = new CreateAccount("CreateSavings","Set Up Your Savings Account");
//			Page createCheckingPage = new CreateAccount("CreateChecking","Set Up Your Checking Account");
//			Page selectAccountsPage = new SelectAccounts();
//			Page depositWithdrawalPage = new DepositWithdrawal();
//			Page transferPage = new MakeTransfer();
//			
//			Page accountApprove = new ApproveMain();
//			Page approveIndiv = new ApproveIndiv();
//			Page viewAccounts = new ViewAccounts();
//			Page transferExternal = new TransferExternal();
//			Page transferInternal = new TransferInternal();
//			Page approveTransfers = new TransferApprove();
//			Page viewLogs = new ViewLogs();
			
			
			siteMap.put("Welcome",new Welcome());
			siteMap.put("Login",new Login());
			siteMap.put("CustomerDB", new CustomerDB());
			siteMap.put("EmployeeDB", new EmployeeDB());
			siteMap.put("Logout", new Logout());
			siteMap.put("Signup", new Signup());
//			siteMap.put("Guest", new Guest());
//			siteMap.put("Blank", blankPage);
			
			siteMap.put("CreateSavings", new CreateAccount("CreateSavings","Set Up Your Savings Account"));
			siteMap.put("CreateChecking", new CreateAccount("CreateChecking","Set Up Your Checking Account"));
			siteMap.put("SelectAccounts", new SelectAccounts());
			siteMap.put("DepositWithdrawal", new DepositWithdrawal());
			siteMap.put("MakeTransfer", new MakeTransfer());
			
			siteMap.put("ApproveMain", new ApproveMain());
			siteMap.put("ApproveIndiv", new ApproveIndiv());
			siteMap.put("ViewAccounts", new ViewAccounts());
			siteMap.put("TransferExternal", new TransferExternal());
			siteMap.put("TransferInternal", new TransferInternal());
			siteMap.put("ApproveTransfers", new TransferApprove());
			siteMap.put("ViewLogs", new ViewLogs());
			
		}
		catch(Exception e) {
			Log.error("One or more pages could not be set up correctly");
			//TODO throw "something went wrong"
		}
			
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
		Log.debug("---------Running start------------");
		runApp(siteMap.get("Welcome"));		
		Log.debug("----------Leaving start----------");
	}
	
	
	public void runApp(Page thisPage) {
		
		Log.setLevel(Level.DEBUG);
		
		Log.debug("------Running page "+ thisPage.getName()+"-----------");
		
		
		//print this page and give me a queue of actions to do next
		//Log.debug("Current user: "+currentUser);
		//Log.debug("This page: "+thisPage);
		
		history.push(thisPage); //add the current page to the history stack
		//Log.debug("Add " + thisPage + " to history stack");
		
		ArrayList<Action> actionList = thisPage.run(currentUser); 
		Queue<Action> actionQueue = new LinkedList<Action>();
		
		for(Action action: actionList) {
			actionQueue.add(action);
		}
		
		Log.debug("Retrieved "+ actionQueue.size() + " action(s)"); //TEMP
				
		
		//iterate through the stack of actions
		while(!actionQueue.isEmpty()) {
			
			Action thisAction = actionQueue.poll(); //get the next action
			
			Log.debug("This action is: "+ thisAction);
						
			switch(thisAction.getCategory()) {
			
			case NAVIGATE:
				
				String target = ((Navigate) thisAction).getTarget(); //get the target String of the page we're navigating to
								
				Page nextPage = new Page();
				
				try {
					nextPage = siteMap.get(target);

					try {
						runApp(nextPage); //run the new page
					}
					catch(Exception e) {
						Log.error("Failed to run next page " + nextPage);
					}
				}
				catch(Exception e) {
					Log.error("Button points to target " + target + " which does not exist in siteMap");
					//TODO something went wrong
				}
				
				break;
		
			case SETUSER:
				User newUser = ((SetUser) thisAction).getUser();
				currentUser = newUser;
				Log.debug("Set current user to: "+currentUser);
				break;
				
			default:
				Log.fatal("Switch case: Action " + thisAction.getCategory() + " does not exist");
				break;
				//TODO throw Something Went Wrong
				
			}
			
		}
		
		Log.debug("-------Leaving run of page " + thisPage.getName()+"---------");
		
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
