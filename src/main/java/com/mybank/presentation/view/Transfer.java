package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class Transfer extends Page{

//-----------CONSTRUCTOR-------------

	public Transfer() {
		super();
		
		this.name = "Transfer";
		this.header = "Transfer Form";
		
		this.interactionBlock = new NullBlock();
	}
	
}
