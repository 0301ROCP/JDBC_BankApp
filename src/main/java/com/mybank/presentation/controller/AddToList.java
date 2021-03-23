package com.mybank.presentation.controller;

import java.util.ArrayList;

public class AddToList extends Operation{
	
	String column;
	
	
	//--------CONSTRUCTOR-----------
	
	public AddToList(String column) {
		super(OperationCategory.ADD_TO_LIST);
		this.column = column;

	}
	
	
	
	//-----------METHODS---------------
	@Override
	public boolean run() {
		
		toPersist.put(column, userAnswer);
		
		return true;
	}

}
