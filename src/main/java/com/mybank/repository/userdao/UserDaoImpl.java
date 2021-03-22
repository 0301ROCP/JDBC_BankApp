package com.mybank.repository.directory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mybank.models.User;
import com.mybank.util.ConnectionFactory;

public class DirectoryDaoImpl implements DirectoryDao {

//	private List<User> allUsers; //TESTING
	
	//----------------CONSTRUCTORS-----------
	
	public DirectoryDaoImpl() {
		
	}
	
//	public DirectoryDaoImpl(List<User> dummyUsers) { //TOASK is this how we should do this?
//		this.allUsers = dummyUsers;
//	}
	
	
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
		User thisUser = null;
		String sqlStatement = "SELECT * FROM users WHERE upi = ?";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setInt(1, id);
			
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
	public List<User> selectUserByName(String firstName, String lastName) {
		
		List<User> theseUsers = null;
		
		String sqlString = "SELECT * FROM users WHERE first_name = '?' AND last_name = '?'";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				theseUsers.add(new User(
						rs.getInt("upi"),
						rs.getString("username"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getBoolean("is_customer"),
						rs.getBoolean("is_employee"),
						rs.getString("user_password")));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return theseUsers;
		
	}
	

	@Override
	public List<User> selectAllUsers() {
		
		List<User> allUsers = null;
		
		String sqlString = "SELECT * FROM users";
		
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				allUsers.add(new User(
						rs.getInt("upi"),
						rs.getString("username"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getBoolean("is_customer"),
						rs.getBoolean("is_employee"),
						rs.getString("user_password")));
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allUsers;
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
