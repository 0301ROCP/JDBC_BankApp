package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class Deposit extends Page{

	//-----------CONSTRUCTOR-------------
	
	public Deposit() {
		super();
		
		this.name = "Deposit";
		this.header = "Deposit Form";
		
		this.interactionBlock = new NullBlock();
	}
	
}
