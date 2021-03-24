package com.mybank.service.account_mgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;

public interface AccountManager {
	
	public void enterForm(HashMap<String, String> formAnswers, String crudAction);
	public boolean closeAccount();
	public ArrayList<Account> getThisUsersAccounts(User currentUser);
	public void addToBalance(Account account, int cents);

	//see account details

}
