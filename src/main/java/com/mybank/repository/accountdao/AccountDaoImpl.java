package com.mybank.repository.accountdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao{
	
	final static Logger Log = Logger.getLogger(AccountDao.class);
	private static UserDaoImpl userDao = new UserDaoImpl();


	//-----------------CREATE METHODS------------
	
	@Override
	public boolean insertAccount(Account a) {
		
		boolean success = false; //TODO check if table exists first, if not create it
		
		String sqlStatement = "INSERT into accounts (account_type, primary_owner, nickname, joint_account, date_created, balance_in_cents, is_open, status) values "
				+ "(?,?,?,?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			int primaryOwner = -1;
			
			try {
				primaryOwner = a.getPrimaryOwner().getUpi();
			}
			catch(Exception e) {
				Log.error("primaryOwner or primaryOwner UPI is null for account " + a);
			}
			
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setString(1, a.getAccountType());
			pStatement.setInt(2, primaryOwner);	
			pStatement.setString(3, a.getNickname());
			pStatement.setBoolean(4, a.isJointAccount());
			pStatement.setDate(5, a.getDateCreated());
			pStatement.setInt(6, a.getBalanceCents());
			pStatement.setBoolean(7, a.isOpen());
			pStatement.setString(8, a.getStatus());
			
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
	public Account selectAccountByAccountID(int id) {
		Account toReturn = new Account();
		
		String sqlString = "SELECT * FROM accounts WHERE account_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, id);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				toReturn = new Account(
						rs.getInt("account_id"),
						rs.getString("account_type"),
						null, //this is the primary User
						rs.getString("nickname"),
						rs.getBoolean("joint_account"),
						null, //list of joint owners
						rs.getDate("date_created"),
						rs.getInt("balance_in_cents"),
						null, //this is the approvedby User
						rs.getBoolean("is_open"),
						rs.getString("status")
						);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select account");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select account");
		}
		
		return toReturn;
	}

	@Override
	public ArrayList<Account> selectAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Account> selectAccountsByUpi(int upi){
		
		//Log.setLevel(Level.DEBUG);
		
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		String sqlString = "SELECT * FROM accounts WHERE primary_owner = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, upi);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account newAccount = new Account(
						rs.getInt("account_id"),
						rs.getString("account_type"),
						userDao.selectUserByID(upi), //primary User
						rs.getString("nickname"),
						rs.getBoolean("joint_account"),
						null, //list of joint owners
						rs.getDate("date_created"),
						rs.getInt("balance_in_cents"),
						userDao.selectUserByID(rs.getInt("approved_by")), //approved by User
						rs.getBoolean("is_open"),
						rs.getString("status")
						);
				Log.debug("Account: "+newAccount);
				toReturn.add(newAccount);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select accounts");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select accounts");
		}
		
		return toReturn;
		
	}
	
	@Override
	public ArrayList<Account> selectAccountsByStringfield(String column, String value){
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		String sqlString = "SELECT * FROM accounts WHERE " + column + "= ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setString(1, value);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account newAccount = new Account(
						rs.getInt("account_id"),
						rs.getString("account_type"),
						null, //this is the primary User
						rs.getString("nickname"),
						rs.getBoolean("joint_account"),
						null, //list of joint owners
						rs.getDate("date_created"),
						rs.getInt("balance_in_cents"),
						null, //this is the approvedby User
						rs.getBoolean("is_open"),
						rs.getString("status")
						);
				Log.debug("Account: "+newAccount);
				toReturn.add(newAccount);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select accounts");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select accounts");
		}
		
		return toReturn;
	}
	
	//-----------------UPDATE METHODS-----------------

	@Override
	public boolean updateAccountBalance(int accountID, int amountCents) {

		boolean success = false;
		
		String sqlString = "UPDATE accounts SET balance_in_cents = ? WHERE account_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, amountCents);
			ps.setInt(2, accountID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ps.execute();
			
			success = true;
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update account balance");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update account balance");
		}
		
		return success;
	}

	@Override
	public boolean changePrimaryOwner(int accountId, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean updateJointOwners(int accountId, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateApprovalStatus(int accountID, String status) {
		
		boolean success = false;

		String sqlStatement = "UPDATE accounts SET status = ? WHERE account_id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setString(1, status);
			pStatement.setInt(2, accountID);
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update account");
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update account");
		}
		
		return success;
	}
	
	@Override
	public boolean setOpen(int accountID, boolean open) {
		boolean success = false;
		
		String sqlStatement = "UPDATE accounts SET is_open = ? WHERE account_id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setBoolean(1, open);
			pStatement.setInt(2, accountID);
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update account");
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update account");
		}
		
		return success;
	}
	
	@Override
	public boolean setApprover(int accountID, int upi) {
		boolean success = false;
		
		String sqlStatement = "UPDATE accounts SET approved_by = ? WHERE account_id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setInt(1, upi);
			pStatement.setInt(2, accountID);
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update account");
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update account");
		}
		
		return success;
	}

	
	//----------------DELETE METHODS--------------

	@Override
	public boolean closeAccount(int accountId) {
		// TODO Auto-generated method stub
		return false;
	}





	
	
}
