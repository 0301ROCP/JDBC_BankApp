package com.mybank.presentation.controller.operations;

import org.apache.log4j.Logger;

import com.mybank.presentation.models.Question;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class VerifyMatch extends Operation{
	
	final static Logger Log = Logger.getLogger(VerifyMatch.class);
	
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
		
		Log.debug("VerifyMatch run()");
		
		boolean result = false;
		
		if(table.equals("users")) { //TODO hardcoded
			result = accessManager.verifyMatch(currentList.get(compareToColumn),compareToColumn,userAnswer,column);
			Log.debug("AccessManager says: " + result);
		}
		else {
			Log.fatal("Ran VerifyMatch on a table other than user: table " + table);
			//TODO something went wrong
		}
		
		return result;
		
	}
	
}
