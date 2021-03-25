package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class CustomerDB extends Page{
	
	final static Logger Log = Logger.getLogger(CustomerDB.class);
	
	//---------CONSTRUCTOR---------
	
	public CustomerDB() {
		super();
		
		this.name = "CustomerDB";
		this.header = "Welcome back!"; //won't actually print this; print overrides for this class
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("D", "Deposit/Withdraw", Button.makeActionQueue(
				new Navigate("DepositWithdrawal"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("T", "Make A New Transfer", Button.makeActionQueue(
				new Navigate("MakeTransfer"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("R", "View Pending Transfer Requests", Button.makeActionQueue(
				new Navigate("ApproveTransfers"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("J", "Add Joint User To Existing Account", Button.makeActionQueue(
				new Navigate("AddJointUser"),
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
		
		
		
		//Check for pending transfers:
		
		ArrayList<Transfer> myTransferReqs = transferManager.getReceivedTransferReqs(currentUser);
		if(myTransferReqs.size()>0) { //if this user has pending inbound transfers
			System.out.println("You have new transfer requests to view!");
			System.out.println();
		}
		
		
		//Display accounts:
		
		System.out.println("Your Accounts:");
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
		Log.debug("User's accounts:" + thisUsersAccounts);
		
		for(Account account: thisUsersAccounts) { //print accounts
			double balance = ((double) account.getBalanceCents())/100; //TODO pad 0's
			String status = account.getStatus();
			boolean isOpen = account.isOpen();
			if(isOpen) {
				System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "' (" + status + ")");
				System.out.print(": ");
				System.out.print("Current Balance = " + formatMoney.format(balance) + "  ");
				if(account.isJointAccount()) {
					System.out.print(" (Joint account with ");
					ArrayList<User> secondUsers = jointDao.selectAllSecondUsersForAccount(account.getAccountID());
					for(User u : secondUsers) {
						System.out.print(u.getUserName() + " ");
					}
					System.out.print(")");
					
				}
				System.out.println();
			}
		}
		
		System.out.println();
		interactionBlock.print();
	}
	
	@Override
	public ArrayList<Action> run(User currentUser) { 
		Log.debug("CustomerDB run()");
		
		print(currentUser); //print this page's header and action block
				
		ArrayList<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
