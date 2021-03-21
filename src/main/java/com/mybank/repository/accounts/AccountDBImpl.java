package com.mybank.repository.accounts;

import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;

public class AccountDBImpl implements AccountDBDao{

	@Override
	public boolean insertAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account selectAccountByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAccountBalance(Account a, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePrimaryOwner(Account a, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJointOwners(Account a, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateApprovalStatus(Account a, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
