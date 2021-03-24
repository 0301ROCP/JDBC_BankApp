package com.mybank.repository.userdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDao;

public interface UserDao {
	

	//CREATE
	boolean insertUser(User u);
	
	//READ
	public User selectUserByID(int ID);
	public User selectUserByUsername(String username);
	public List<User> selectUserByName(String firstName, String lastName);
	public List<User> selectAllUsers();	
	public List<User> selectUsersByAttribute(String attribute, String column);
	public String selectStringValueByAttributeByID(int id, String column);
	
	//UPDATE
	boolean updateUserPassword(int upi, String password);
	boolean updateUserEmpStatus(int upi, boolean employeeStatus);
	boolean updateUserCustStatus(int upi, boolean custStatus);
	
	//DELETE
	boolean deleteUser(int upi);
	
}
