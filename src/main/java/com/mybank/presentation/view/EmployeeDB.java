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
import com.mybank.presentation.models.MenuBlock;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;

public class EmployeeDB extends Page{
	
	final static Logger Log = Logger.getLogger(EmployeeDB.class);
	
	//-----------CONSTRUCTOR-------------
	
	public EmployeeDB() {
		super();
		
		this.name = "EmployeeDB";
		this.header = "Employee Dashboard";
		
		this.interactionBlock = new MenuBlock();
	
	
		((MenuBlock) this.interactionBlock).addButton("A", "Approve Pending Accounts", Button.makeActionQueue(
				new Navigate("ApproveMain"),
				new Navigate("EmployeeDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("V", "View Customer Profile", Button.makeActionQueue(
				new Navigate("ViewAccounts"),
				new Navigate("EmployeeDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("L", "Transaction Log", Button.makeActionQueue(
				new Navigate("ViewLogs"),
				new Navigate("EmployeeDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("S", "Switch To Personal Dashboard", Button.makeActionQueue(
				new Navigate("CustomerDB") 
				));
		
		((MenuBlock) this.interactionBlock).addButton("O", "Log Out", Button.makeActionQueue(
				new Navigate("Logout")
				));
	}
	
	
	//-----------METHODS------------
	
	public void print(User currentUser) { 
		
		//Log.setLevel(Level.DEBUG);
		
		//Header:
		System.out.println("Hello " + currentUser.getFirstName() + "!");
		System.out.println();
		
		//Display pending request number
		
		ArrayList<Account> pendingAccounts = accountManager.getPendingAccounts(); //ask Account Manager for all unapproved accounts
		
		int numPendingAccounts = pendingAccounts.size();
		
		System.out.println("There are " + numPendingAccounts + " accounts awaiting approval.");
		
	
		
//			
//			for(Account account: pendingAccounts) { //print accounts
//				
//				
//				double balance = account.getBalanceCents()/100; //TODO pad 0's
//				boolean approved = account.isApproved();
//				System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "'");
//				if(!approved) {
//					System.out.print(" (pending approval)");
//				}
//				System.out.print(": ");
//				System.out.print("Current Balance = " + balance + "  ");
//				System.out.println();
//			}
		
		System.out.println();
		interactionBlock.print();
	}
	
	@Override
	public Queue<Action> run(User currentUser) {  //need this because the print() is different!
		Log.debug("EmployeeDB run()");
		
		print(currentUser); //print this page's header and action block
				
		Queue<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}
	
}
