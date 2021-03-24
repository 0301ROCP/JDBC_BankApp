package com.mybank.presentation.view;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.SetUser;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class Logout extends Page {
	
//-----------CONSTRUCTOR-------------

	public Logout() {
		super();
		
		this.name = "Logout";
		this.header = "Are You Sure You Want To Log Out?";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("Y", "Yes, Log Me Out!", Button.makeActionQueue(
				new SetUser(new User()),
				new Navigate("Welcome")
				));
		
	}

}
