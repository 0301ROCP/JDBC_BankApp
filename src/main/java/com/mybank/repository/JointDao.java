package com.mybank.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.util.ConnectionFactory;

public class JointDao {

	final static Logger Log = Logger.getLogger(JointDao.class);
	
	static AccountDaoImpl accountDao = new AccountDaoImpl();
	static UserDaoImpl userDao = new UserDaoImpl();

	
	//--------------CREATE---------------
	
	public boolean insertRow(int userID, int accountID) {
			
		boolean success = false;
		
		
		String sqlStatement = "INSERT into secondary_users (user_id, account_id) values "
				+ "(?,?)";
		


		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			
			
			pStatement.setInt(1, userID);
			pStatement.setInt(2, accountID);
			
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to insert joint row" + userID + accountID);
			//e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to insert joint row");
		}
		

		return success;
		
		
	}

	
	//-------------READ-------------
	
	public ArrayList<Account> selectAllSecondAccountsForUser(int userId) { //WORKING
		
		ArrayList<Account> secondaryAccounts = new ArrayList<Account>();
		
		String sqlString = "SELECT * FROM secondary_users WHERE user_id = ?";
		
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int accountID = rs.getInt("account_id");
				secondaryAccounts.add(accountDao.selectAccountByAccountID(accountID));
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select all secondary accounts for user " + userId);
			//e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select all secondary accounts");
		}
		
		return secondaryAccounts;
	}
	
	public ArrayList<User> selectAllSecondUsersForAccount(int accountId) { //WORKING
		
		ArrayList<User> secondaryUsers = new ArrayList<User>();
		
		String sqlString = "SELECT * FROM secondary_users WHERE account_id = ?";
		
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setInt(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int userID = rs.getInt("user_id");
				secondaryUsers.add(userDao.selectUserByID(userID));
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select all secondary users for account " + accountId);
			//e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select all secondary users");
		}
		
		return secondaryUsers;
	}
	
}
	

