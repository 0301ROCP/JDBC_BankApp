package com.mybank.presentation.view;

import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.controller.actions.Select;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.presentation.models.NullBlock;

public class MakeTransfer extends Page{

//-----------CONSTRUCTOR-------------

	public MakeTransfer() {
		super();
		
		this.name = "Transfer Money";
		this.header = "Transfer Form";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("I", "Internal Transfer (Between My Accounts)", Button.makeActionQueue(
				new Navigate("TransferInternal")
				));
		((MenuBlock) this.interactionBlock).addButton("E", "External Transfer (To/From Someone Else)", Button.makeActionQueue(
				new Navigate("TransferExternal")
				));
	}
	
}
