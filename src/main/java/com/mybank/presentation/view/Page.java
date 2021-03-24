package com.mybank.presentation.view;



import java.util.Queue;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.models.InteractionBlock;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;



public class Page {
	
	final static Logger Log = Logger.getLogger(Page.class);
	
	String name;
	String header;
	InteractionBlock interactionBlock;
//	Menu menu;
//	String instructions;
	
	protected static Scanner sc = new Scanner(System.in);
	protected static AcctMgrImpl accountManager = new AcctMgrImpl(new AccountDaoImpl());
	protected static AccessMgrImpl accessManager = new AccessMgrImpl(new UserDaoImpl());
	
	
	//CONSTRUCTOR:
	public Page() { 
		
	}

	
	//--------GETTERS AND SETTERS---------
	public String getName() {
		return this.name;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	
	//----------TOSTRING--------
	
	
	@Override
	public String toString() {
		return "Page [name=" + name + "]";
	}


	//--------METHODS---------
	public void print() { //WORKING
		System.out.println(header);
		interactionBlock.print();
	}
		
	public static void clear() { //WORKING
		pause();
		
		for(int i = 0; i<5; i++) {
			System.out.println();
		}
	}
	
	public static void pause() { //WORKING
		long startTime = System.currentTimeMillis();
		System.out.println("redirecting...");
        while(startTime >= System.currentTimeMillis() - 500); // do nothing
       
	}
	
//	public Queue<Action> getActionsFromButton(String enteredKey) {
//		LinkedHashMap<String,Button> theseButtons = menu.getButtons(); //returns all the buttons in this menu
//		Button selectedButton = theseButtons.get(enteredKey); //get button based on user-entered key
//		Queue<Action> resultingActions = selectedButton.getActionQueue(); //get the action queue that corresponds to clicking this button
//		
//		//String pageTarget = selectedButton.getTarget(); //get target page of selected button
//		
//		return resultingActions;	
//	}

	
	
	
	public Queue<Action> run(User currentUser) { //WORKING
		Log.debug("Default Page run()");
		
		print(); //print this page's header and action block
				
		Queue<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
