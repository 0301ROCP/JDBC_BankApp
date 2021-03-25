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
	public boolean addToBalance(Account account, int cents, User currentUser);
	public ArrayList<Account> getPendingAccounts();
	public void setAccountApproval(Account account, String status, User approver);
	public void openAccount(Account account);

	//see account details

}
