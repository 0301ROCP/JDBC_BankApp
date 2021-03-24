package com.mybank.repository.accountdao;

import java.util.ArrayList;
import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;

public interface AccountDao {

	//CREATE
	boolean insertAccount(Account a);
	boolean insertApprovedAccount(Account a); //not sure if/when you'd need this but here it is
	
	//READ
	public Account selectAccountByAccountID(int id);
	public ArrayList<Account> selectAllAccounts();	
	public ArrayList<Account> selectAccountsByUpi(int upi);
	
	//UPDATE
	boolean updateAccountBalance(Account a, int amount);
	boolean changePrimaryOwner(Account a, User u);
	boolean updateJointOwners(Account a, User u);
	boolean updateApprovalStatus(Account a, boolean status);
	
	//DELETE
	boolean closeAccount(Account a); //this is NOT true deletion; accounts are never deleted from the database
	
}
