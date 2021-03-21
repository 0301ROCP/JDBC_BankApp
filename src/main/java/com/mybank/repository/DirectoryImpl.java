package com.mybank.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mybank.models.User;
import com.mybank.util.ConnectionFactory;

public class DirectoryImpl implements DirectoryDao {

	//----------------CREATE----------------
	
	@Override
	public boolean insertUser(User u) {
		boolean success = false;
		
		String sqlStatement = "INSERT into users (username, first_name, last_name, is_customer, is_employee, user_password) values "
				+ "(?,?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setString(1, u.getUserName());
			pStatement.setString(2, u.getFirstName());
			pStatement.setString(3, u.getLastName());
			pStatement.setBoolean(4, u.isCustomer());
			pStatement.setBoolean(5, u.isEmployee());
			pStatement.setString(6, u.getPassword());
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			System.out.println("repository.DirectoryImpl.java: Something went wrong with this user creation!");
			e.printStackTrace();
		}
		
		return success;
	}

	
	//----------------READ----------------
	
	@Override
	public User selectUserByID(int id) {
		return null;
	}
	
	@Override
	public User selectUserByUsername(String username) {
		
		User thisUser = null;
		String sqlStatement = "SELECT * FROM users WHERE username = ?";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setString(1, username);
			
			ResultSet result = pStatement.executeQuery();
			
			while(result.next()) {
				thisUser = new User(
						result.getInt("upi"),
						result.getString("username"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getBoolean("is_customer"),
						result.getBoolean("is_employee"),
						result.getString("user_password"));
			}
		}
		catch(SQLException e) {
			//TODO fix catch block
			System.out.println("repository.DirectoryImpl.java Something went wrong selecting this user!");
			e.printStackTrace();
		}
		
		return thisUser;
	}

	@Override
	public User selectUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//----------------UPDATE----------------

	@Override
	public boolean updateUserPassword(User u, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserEmpStatus(User u, boolean employeeStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserCustStatus(User u, boolean custStatus) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//----------------DELETE----------------

	@Override
	public boolean deleteUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}


}
