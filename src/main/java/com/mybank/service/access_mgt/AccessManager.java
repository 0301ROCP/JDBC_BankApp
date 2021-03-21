package com.mybank.service.access_mgt;

public interface AccessManager {

	public boolean verifyLogin(int upi, String password);
	
	public int createNewUser(boolean isCustomer, boolean isEmployee); //return new user's UPI
	
}
