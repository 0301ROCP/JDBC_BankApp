package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;

public class CustomerDB extends Page{
	
	final static Logger Log = Logger.getLogger(FormBlock.class);
	
	//---------CONSTRUCTOR---------
	
	public CustomerDB() {
		super();
		
		this.name = "CustomerDB";
		this.header = "Welcome back!"; //won't actually print this; print overrides for this class
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("W", "Withdraw", Button.makeActionQueue(
				new Navigate("Withdraw"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("D", "Deposit", Button.makeActionQueue(
				new Navigate("Deposit"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("T", "Transfer", Button.makeActionQueue(
				new Navigate("Transfer"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("N", "Open A New Account", Button.makeActionQueue(
				new Navigate("SelectAccounts") //SelectAccounts already has an action to navigate back to customerDB
				));
		
		((MenuBlock) this.interactionBlock).addButton("O", "Log Out", Button.makeActionQueue(
				new Navigate("Logout")
				));
		

	
	}
	
	//-----------METHODS------------
	
	public void print(User currentUser) { 
		
		//Log.setLevel(Level.DEBUG);
		
		//Header:
		System.out.println("Welcome Back " + currentUser.getFirstName() + "!");
		System.out.println();
		System.out.println("Your Accounts:");
		
		//Display accounts:
		AcctMgrImpl accountManager = new AcctMgrImpl(new AccountDaoImpl());
		
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
		Log.debug("User's accounts:" + thisUsersAccounts);
		
		for(Account account: thisUsersAccounts) { //print accounts
			double balance = account.getBalanceCents()/100; //TODO pad 0's
			boolean approved = account.isApproved();
			System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "'");
			if(!approved) {
				System.out.print(" (pending approval)");
			}
			System.out.print(": ");
			System.out.print("Current Balance = " + balance + "  ");
			System.out.println();
		}
		
		System.out.println();
		interactionBlock.print();
	}
	
	@Override
	public Queue<Action> run(User currentUser) { //WORKING
		Log.debug("CustomerDB run()");
		
		print(currentUser); //print this page's header and action block
				
		Queue<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
