package com.mybank.repository.userdao;

import java.util.List;

import com.mybank.models.User;

public interface UserDao {

	//CREATE
	boolean insertUser(User u);
	
	//READ
	public User selectUserByID(int ID);
	public User selectUserByUsername(String username);
	public List<User> selectUserByName(String firstName, String lastName);
	public List<User> selectAllUsers();	
	
	//UPDATE
	boolean updateUserPassword(User u, String password);
	boolean updateUserEmpStatus(User u, boolean employeeStatus);
	boolean updateUserCustStatus(User u, boolean custStatus);
	
	//DELETE
	boolean deleteUser(User u);
	
}
