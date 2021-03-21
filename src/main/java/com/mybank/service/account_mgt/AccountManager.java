package com.mybank.service.account_mgt;

import com.mybank.models.Account;

public interface AccountManager {
	
	public Account openAccount();
	public boolean closeAccount();

	//see account details

}
