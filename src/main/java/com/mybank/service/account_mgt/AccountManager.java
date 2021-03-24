package com.mybank.service.account_mgt;

import java.util.ArrayList;
import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;

public interface AccountManager {
	
	public Account openAccount(String type);
	public boolean closeAccount();
	public ArrayList<Account> getThisUsersAccounts(User currentUser);

	//see account details

}
