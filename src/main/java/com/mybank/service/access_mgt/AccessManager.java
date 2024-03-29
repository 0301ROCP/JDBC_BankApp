package com.mybank.service.access_mgt;

import java.util.HashMap;

import com.mybank.models.User;

public interface AccessManager {

	public boolean verifyLogin(int upi, String password);
	
	public User createNewUser(boolean isCustomer, boolean isEmployee); //return new user's UPI

	public boolean verifyExists(String value, String column);
	
	public User enterForm(HashMap<String, String> formAnswers, String crudAction);

	public User lookupUser(String column, String userLookup);

	boolean verifyMatch(String value1, String column1, String value2, String column2);
	
}
