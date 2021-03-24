package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class Guest extends Page{
	
	
	//-----------CONSTRUCTOR-------------
	
	public Guest() {
		super();
		
		this.name = "Withdraw";
		this.header = "Withdrawal Form";
		
		this.interactionBlock = new NullBlock();
	}

}
