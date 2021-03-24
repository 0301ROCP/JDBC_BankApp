package com.mybank.presentation.controller.operations;

import org.apache.log4j.Logger;

import com.mybank.presentation.models.Question;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class VerifyExists extends Operation{
	
	final static Logger Log = Logger.getLogger(VerifyExists.class);
	
	String table;
	String column;
	boolean reverse;
	AccessMgrImpl accessManager = new AccessMgrImpl(new UserDaoImpl());

	//----------CONSTRUCTOR---------
	
	public VerifyExists(String table, String column, boolean reverse) {
	
		super(OperationCategory.VERIFY_EXISTS);
		
		this.table = table;
		this.column = column;
		this.reverse = reverse;
	}
	
	
	//----------METHODS------------
	

	@Override
	public boolean run() {
		
		Log.debug("VerifyExists run()");
		
		boolean result = false;
		
		if(!userAnswer.equals("")) { //as long as they entered something
		
			if(table.equals("users")) { //TODO hardcoded
				result = accessManager.verifyExists(userAnswer, column);
				Log.debug("AccessManager says: " + result);
			}
			else {
				Log.fatal("Ran VerifyExists on a table other than user: table " + table);
				//TODO something went wrong
			}
			
			if(reverse) {
				Log.debug("Reverse; return " + !result);
				return !result;			
			}
			else {
				return result;
			}
		}
		
		else {
			System.out.println("This field cannot be left blank.");
			Log.debug("Return false; field left blank");
			return false;
		}
		
	}
	
	

}
