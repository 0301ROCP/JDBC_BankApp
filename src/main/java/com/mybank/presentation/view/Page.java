package com.mybank.presentation.view;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.operations.ValidateMoney;
import com.mybank.presentation.models.InteractionBlock;
import com.mybank.repository.JointDao;
import com.mybank.repository.TransactionDao;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.transferDao.TransferDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;
import com.mybank.service.transfer_mgt.TransferManagerImpl;



public class Page {
	
	final static Logger Log = Logger.getLogger(Page.class);
	
	String name;
	String header;
	InteractionBlock interactionBlock;
	
	protected static Scanner sc = new Scanner(System.in);
	protected static UserDaoImpl userDao = new UserDaoImpl();
	protected static AccountDaoImpl accountDao = new AccountDaoImpl();
	protected static TransactionDao logDao = new TransactionDao();
	protected static TransferDaoImpl transferDao = new TransferDaoImpl();
	protected static JointDao jointDao = new JointDao();
	protected static AcctMgrImpl accountManager = new AcctMgrImpl(accountDao);
	protected static AccessMgrImpl accessManager = new AccessMgrImpl(userDao);
	protected static TransferManagerImpl transferManager = new TransferManagerImpl(transferDao);
	
	protected static ValidateMoney moneyValidator = new ValidateMoney();
	protected static DecimalFormat formatMoney = new DecimalFormat("#.00");
	
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
		
		for(int i = 0; i<15; i++) {
			System.out.println();
		}
		for(int i = 0; i<100; i++) {
			System.out.print("_");
		}
		System.out.println();
		
	}
	
	public static void pause() { //WORKING
		long startTime = System.currentTimeMillis();
		System.out.println("redirecting...");
        while(startTime >= System.currentTimeMillis() - 1000); // do nothing
       
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

	
	
	
	public ArrayList<Action> run(User currentUser) { //WORKING
		//Log.setLevel(Level.DEBUG);
		
		Log.debug("Default Page run()");
		
		print(); //print this page's header and action block
			
		ArrayList<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
