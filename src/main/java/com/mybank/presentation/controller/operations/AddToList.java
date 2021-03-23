package com.mybank.presentation.controller.operations;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mybank.presentation.controller.operations.Operation.OperationCategory;
import com.mybank.presentation.models.MenuBlock;

public class AddToList extends Operation{
	
	final static Logger Log = Logger.getLogger(AddToList.class);
	
	String column;
	
	
	//--------CONSTRUCTOR-----------
	
	public AddToList(String column) {
		super(OperationCategory.ADD_TO_LIST);
		this.column = column;

	}
	
	
	
	//-----------METHODS---------------
	@Override
	public boolean run() {
		
		Log.debug("AddToList run()");
		
		toPersist.put(column, userAnswer);
		
		return true;
	}

}
