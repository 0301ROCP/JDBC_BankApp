package com.mybank.service.account_mgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDao;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessManager;

public class AcctMgrImpl implements AccountManager{
	
	final static Logger Log = Logger.getLogger(AccountManager.class);
	
	private static AccountDao accountDao = new AccountDaoImpl();

	
	//------------CONSTRUCTOR-------------
	
	
	public AcctMgrImpl(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}
	
	
	//-------------METHODS----------------
	
	@Override
	public void enterForm(HashMap<String, String> formAnswers, String crudAction) {	
		
		UserDao userDao = new UserDaoImpl();
		
		int account_id = -1;
		String account_type = null;
		User primary_owner = null;
		String nickname = formAnswers.get("nickname");
		boolean joint_account = false; //TODO skipped
		ArrayList<User> joint_owners = null; //TODO skipped
		java.sql.Date date_created = null;
		int balance_in_cents = 0;
		User approved_by = null;
		boolean open = false;
		String status = null;
		
		if(formAnswers.get("account_id") != null) {
			account_id = Integer.parseInt(formAnswers.get("account_id"));
		}
		
		if(formAnswers.get("account_type") != null){
			account_type = formAnswers.get("account_type"); //TODO can it parse from enum to string?
		}
		
		if(formAnswers.get("primary_owner") != null) {
			int ownerUpi = Integer.parseInt(formAnswers.get("primary_owner"));			
			primary_owner = userDao.selectUserByID(ownerUpi);
		}
		
		if(formAnswers.get("date_created") != null) {
			date_created = java.sql.Date.valueOf(formAnswers.get("date_created"));
		}
		
		if(formAnswers.get("balance_in_dollars") != null) {
			balance_in_cents = (int) Double.parseDouble(formAnswers.get("balance_in_dollars")) * 100;
		}
		
		if(formAnswers.get("approved_by") != null) {
			int approverUpi = Integer.parseInt(formAnswers.get("approved_by")); //TODO risky try catch, what if it's not a upi?
			approved_by = userDao.selectUserByID(approverUpi);
		}
		
		if(formAnswers.get("open") != null) {
			open = Boolean.parseBoolean(formAnswers.get("open")); //TODO risky try catch, what if it's not a boolean?
		}
		
		if(formAnswers.get("status") != null) {
			status = formAnswers.get("status"); //TODO risky try catch, what if it's not a boolean?
		}
		
		Account thisAccount = new Account(account_id, account_type, primary_owner, nickname, joint_account, joint_owners, date_created, balance_in_cents, approved_by, open, status);
						

		switch(crudAction) {
		
		case "create":
			accountDao.insertAccount(thisAccount);
			Log.debug("Insert account " + thisAccount);
	
			break;
		
		default:
			Log.fatal("Called EnterForm on CRUD action that does not exist");
				
		}		
		
	}

	@Override
	public boolean closeAccount() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ArrayList<Account> getThisUsersAccounts(User currentUser) {
		
		//Log.setLevel(Level.DEBUG);
		
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		toReturn = accountDao.selectAccountsByUpi(currentUser.getUpi());
		Log.debug("toReturn = " + toReturn);
		
		return toReturn;
	}
	
	@Override
	public void addToBalance(Account account, int cents) {
		Account thisAccount = accountDao.selectAccountByAccountID(account.getAccountID());
		int balanceCents = thisAccount.getBalanceCents();
		int newBalance = balanceCents + cents;
		
		accountDao.updateAccountBalance(account.getAccountID(), newBalance);
	}
	
	@Override
	public ArrayList<Account> getPendingAccounts() {
		
		ArrayList<Account> pendingAccounts = new ArrayList<Account>();
		
		pendingAccounts = accountDao.selectAccountsByStringfield("status", "pending");
		
		return pendingAccounts;
	}
	
	@Override
	public void setAccountApproval(Account account, String status, User approver) {
		
		accountDao.updateApprovalStatus(account.getAccountID(),status);
		
		accountDao.setApprover(account.getAccountID(),approver.getUpi());
		
		if(status.equals("approved")) {
			accountDao.setOpen(account.getAccountID(),true);
		}
		
	}


	
	@Override
	public void openAccount(Account account) {
		accountDao.insertAccount(account);
		
	}
	
}


