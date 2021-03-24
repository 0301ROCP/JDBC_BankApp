package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class Withdraw extends Page{
	
	
	//-----------CONSTRUCTOR-------------
	
	public Withdraw() {
		super();
		
		this.name = "Withdraw";
		this.header = "Withdrawal Form";
		
		this.interactionBlock = new NullBlock();
	}

}
