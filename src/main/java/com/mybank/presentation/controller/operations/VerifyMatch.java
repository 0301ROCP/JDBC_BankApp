package com.mybank.presentation.controller.operations;

import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class VerifyMatch extends Operation{
	
	String table;
	String column;
	String compareToColumn;
	AccessMgrImpl accessManager = new AccessMgrImpl(new UserDaoImpl());

	public VerifyMatch(String table, String column, String compareToColumn) {
		
		super(OperationCategory.VERIFY_MATCH);
		this.table = table;
		this.column = column;
		this.compareToColumn = compareToColumn;
		
	}
	
	@Override
	public boolean run() {
		
		boolean result = false;
		
		if(table.equals("users")) { //TODO hardcoded
			result = accessManager.verifyMatch(currentList.get(compareToColumn),compareToColumn,userAnswer,column);
		}
		else {
			System.out.println("ERROR in VerifyExists extends Operation");
		}
		
		return result;
		
	}
	
	

}
