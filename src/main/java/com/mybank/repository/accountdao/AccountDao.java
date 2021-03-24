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
	public ArrayList<Account> selectAccountsByStringfield(String column, String value);
	
	//UPDATE
	boolean updateAccountBalance(int accountId, int amountCents);
	boolean changePrimaryOwner(int accountId, User u);
	boolean updateJointOwners(int accountId, User u);
	boolean updateApprovalStatus(int accountID, String status);
	boolean setOpen(int accountID, boolean open);
	boolean setApprover(int accountID, int upi);
	
	//DELETE
	boolean closeAccount(int accountId); //this is NOT true deletion; accounts are never deleted from the database
	
	
}
