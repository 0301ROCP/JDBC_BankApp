package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class Logout extends Page {
	
//-----------CONSTRUCTOR-------------

	public Logout() {
		super();
		
		this.name = "Logout";
		this.header = "Are You Sure You Want To Log Out?";
		
		this.interactionBlock = new NullBlock();
	}

}
