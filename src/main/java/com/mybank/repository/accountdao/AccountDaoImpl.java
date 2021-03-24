package com.mybank.repository.accountdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.service.account_mgt.AccountManager;
import com.mybank.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao{
	
	final static Logger Log = Logger.getLogger(AccountDao.class);


	//-----------------CREATE METHODS------------
	
	@Override
	public boolean insertAccount(Account a) {
		
		boolean success = false; //TODO check if table exists first, if not create it
		
		String sqlStatement = "INSERT into accounts (primary_owner, account_type, joint_account, date_created, balance_in_cents, is_open) values "
				+ "(?,?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setInt(1, a.getPrimaryOwner().getUpi());
			pStatement.setString(2, a.getAccountType());
			pStatement.setBoolean(3, a.isJointAccount());
			pStatement.setDate(4, a.getDateCreated());
			pStatement.setInt(5, a.getBalanceCents());
			pStatement.setBoolean(6, a.isOpen());
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to insert account");
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to insert account");
		}
		
		return success;
	}
	
	@Override
	public boolean insertApprovedAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//-----------------READ METHODS---------------

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
	
	
	//-----------------UPDATE METHODS-----------------

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
	
	
	//----------------DELETE METHODS--------------

	@Override
	public boolean closeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}




	
	
}
