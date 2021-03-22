package com.mybank.repository.accountdao;

import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;

public interface AccountDao {

	//CREATE
	boolean insertAccount(Account a);
	
	//READ
	public Account selectAccountByID(int ID);
	public List<Account> selectAllAccounts();	
	
	//UPDATE
	boolean updateAccountBalance(Account a, int amount);
	boolean changePrimaryOwner(Account a, User u);
	boolean updateJointOwners(Account a, User u);
	boolean updateApprovalStatus(Account a, boolean status);
	
	//DELETE
	boolean closeAccount(Account a); //this is NOT true deletion; accounts are never deleted from the database
	
}
