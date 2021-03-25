package com.mybank.presentation.view;

import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Select;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class TransferApproveIndiv extends Page{

	final static Logger Log = Logger.getLogger(TransferApproveIndiv.class);	
	
	
	//-----------CONSTRUCTOR----------
	
	public TransferApproveIndiv() {
		super();
		
		this.name = "TransferApproveIndiv";
		this.header = "Review Pending Transfer";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("A", "Approve", Button.makeActionQueue(
				new Select("Approve")
				));
		
		((MenuBlock) this.interactionBlock).addButton("R", "Reject", Button.makeActionQueue(
				new Select("Deny")
				));
		
		((MenuBlock) this.interactionBlock).addButton("S", "Skip", Button.makeActionQueue(
				new Select("Skip")
				));
	}
	
	
	//----------METHODS----------
	
	public void print(Transfer thisTransfer) {
		
		Log.setLevel(Level.DEBUG);
		
		
		System.out.println("----" + header + "----");
		
		boolean valid = true;
		
		String username = null;
		String firstname = null;
		String lastname = null;
		int amountCents = 0;
		String amountDollarString = null;
		
		
		//Validate user
		User sender = thisTransfer.getSender();
		try {
			username = sender.getUserName();
			firstname = sender.getFirstName();
			lastname = sender.getLastName();
		}
		catch(Exception e) {
			Log.error("Problem with transfer " + thisTransfer);
			valid = false;
		}
		
		//Parse amount
		amountCents = thisTransfer.getAmountCents();
		amountDollarString = String.valueOf(((double) amountCents)/100);
		
		//Get date
		java.sql.Date date = thisTransfer.getDate();
		
		
		//Print the transfer!
		if(valid) {
			System.out.println(username + " (" + firstname + " " + lastname + ")");
			if(amountCents>=0) {
				System.out.println("requested to send you " + amountDollarString);
			}
			else {
				System.out.println("requested " + amountDollarString + " from you");
			}
			System.out.println("On " + date);
			System.out.println("Memo: " + thisTransfer.getMemo());
			
		}
		
		
		interactionBlock.print();
		
	}
	
	public Queue<Action> run(User currentUser, Transfer thisTransfer) { 
		
		print(thisTransfer); //print this page's header and action block
				
		Queue<Action> actionQueue = interactionBlock.run(currentUser);

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}
	
}
