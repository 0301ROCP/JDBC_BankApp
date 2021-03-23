package com.mybank.presentation.controller.operations;

import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class VerifyExists extends Operation{
	
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
		
		boolean result = false;
		
		if(table.equals("users")) { //TODO hardcoded
			result = accessManager.verifyExists(userAnswer, column);
		}
		else {
			System.out.println("ERROR in VerifyExists extends Operation");
		}
		
		if(reverse) {
			return !result;
		}
		else {
			return result;
		}
		
	}
	
	

}
