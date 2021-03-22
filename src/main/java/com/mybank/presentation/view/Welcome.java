package com.mybank.presentation.view;

import com.mybank.presentation.controller.Navigate;
import com.mybank.presentation.controller.SetUser;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class Welcome extends Page {

	
	//---------CONSTRUCTOR----------
	
	public Welcome(){ 
		super();
		
		this.name = "Welcome";
		this.header = "Welcome to MyBank!";
				
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("L", "Log In", Button.makeActionQueue(
				new Navigate("Login"),
//				new SetUser(), //TODO made a noargs for this, delete it if not used
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("S", "Sign Up", Button.makeActionQueue(
				new Navigate("Signup")
				));
		
		((MenuBlock) this.interactionBlock).addButton("G", "Continue As Guest", Button.makeActionQueue(
				new Navigate("Guest")
				));
		
//		((MenuBlock) this.actionBlock).addButton("E", "Employee Portal", Button.makeActionQueue(
//				new Navigate("Employee")
//				));

		//this.menu.addUtils();
		
		
	}
	
	
}
