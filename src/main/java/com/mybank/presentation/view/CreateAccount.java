package com.mybank.presentation.view;

import org.apache.log4j.Logger;

import com.mybank.presentation.controller.operations.AddToList;
import com.mybank.presentation.controller.operations.ValidateMoney;
import com.mybank.presentation.models.FormBlock;
import com.mybank.presentation.models.Question;
import com.mybank.service.account_mgt.AccountManager;

public class CreateAccount extends Page{

	final static Logger Log = Logger.getLogger(CreateAccount.class);
	
	//-------CONSTRUCTOR---------
	public CreateAccount(String name, String header) {
		super();
		
		this.name = name;
		this.header = header;
		
		this.interactionBlock = new FormBlock("accounts","create",false); //create a new FormBlock that corresponds to users table
				
//			((FormBlock) this.interactionBlock).addQuestion(new Question(
//					"Will this be a joint account?",
//					null,
//					null, 
//					new AddToList("first_name") //add password to form info
//					));
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				new AddToList("primary_owner"),
				true,
				"#getuser_upi" //this will become the current user's upi				
				));
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"How much would you like to deposit into this account?",
				"The amount must be 0 or more, and follow the format 1234.56",
				new ValidateMoney(),
				new AddToList("balance_in_dollars") //add password to form info
				));
		
		
		((FormBlock) this.interactionBlock).addQuestion(new Question(
				"What would you like to name this account?",
				null,
				null, 
				new AddToList("nickname") //add password to form info
				));		
		
		
		switch(name) {
		
		case "CreateSavings":
			((FormBlock) this.interactionBlock).addQuestion(new Question(
					new AddToList("account_type"),
					true,
					"Savings" 			
					));
			break;
		
		case "CreateChecking":
			((FormBlock) this.interactionBlock).addQuestion(new Question(
					new AddToList("account_type"),
					true,
					"Checking" 				
					));				
			break;
		
		default:
			Log.fatal("CreateAccount page called for neither savings nor checking: called "+name);
			break;
				
		}

	}
	
}