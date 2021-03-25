package com.mybank.presentation.view;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Select;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class ApproveIndiv extends Page{

	final static Logger Log = Logger.getLogger(ApproveIndiv.class);	
	
	public ApproveIndiv() {
		super();
		
		this.name = "ApproveIndiv";
		this.header = "Approve Pending Account";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("A", "Approve", Button.makeActionQueue(
				new Select("Approve")
				));
		
		((MenuBlock) this.interactionBlock).addButton("D", "Deny", Button.makeActionQueue(
				new Select("Deny")
				));
		
		((MenuBlock) this.interactionBlock).addButton("S", "Skip", Button.makeActionQueue(
				new Select("Skip")
				));
	}
	
	
	
	public void print(Account thisAccount) {
		
		Log.setLevel(Level.DEBUG);
		
		Log.debug("ApproveIndiv print()");
		
		System.out.println("----" + header + "----");

		
		//User accountUser = thisAccount.getPrimaryOwner();
		//String userName = accountUser.getUserName();
		Double balanceDollars = ((double) thisAccount.getBalanceCents())/100;
		boolean hasNickname = thisAccount.getNickname()!=null;
		
		//System.out.println(thisAccount);

		
		if(hasNickname) {
			System.out.println(thisAccount.getAccountType() + " Account " + "'" + thisAccount.getNickname() + "' (ID: " + thisAccount.getAccountID() + ")");
		}
		else {
			System.out.println(thisAccount.getAccountType() + " Account (ID: " + thisAccount.getAccountID() + ")");
		}
		
		System.out.println("Initial balance: " + balanceDollars);
		//System.out.println("User: " + accountUser.getUserName());
		//System.out.println("User " + accountUser.getUserName() + " (" + accountUser.getFirstName() + " " + accountUser.getLastName() + ")");
					
		interactionBlock.print();
		
	}
	
	public ArrayList<Action> run(User currentUser, Account thisAccount) { //WORKING
		
		print(thisAccount); //print this page's header and action block
				
		ArrayList<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
