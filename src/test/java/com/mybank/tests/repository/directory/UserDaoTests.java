package com.mybank.tests.repository.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;



import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mybank.models.User;
import com.mybank.repository.directory.DirectoryDao;
import com.mybank.repository.directory.DirectoryDaoImpl;

public class DirectoryDaoTests {
	
	static DirectoryDao directory;
	static List<User> users = new ArrayList<User>();
	
	
	@BeforeClass
	public static void populateDirectory() {
		
		///TODO add some users
		
		User user1 = new User(501, "username1", "firstname1", "lastname1", true, false, "1234");
		User user2 = new User(502, "username2", "firstname2", "lastname2", true, false, "1234");
		User employee1 = new User(503, "username3", "firstname3", "lastname3", true, true, "1234");
		User employee2 = new User(504, "username4", "firstname4", "lastname4", false, true, "1234");
		
		users.add(user1);
		users.add(user2);
		users.add(employee1);
		users.add(employee2);
		
		directory = new DirectoryDaoImpl();
		
	}
	
	
	
	@AfterClass
	public static void removeDummyData() {
		//TODO delete entries
		directory = null;
	}
	
	
	@Before
	public void resetDirectory() {
		directory = new DirectoryDaoImpl();
	}
	
	@Test
	public void testInsertUser() {
		
		//tests that inserting a user:
		//increases the database size by 1
		//actually puts the user in the database
		//TODO sets each field of the new user in the database as expected
		//TOASK how to test serial? should we actually be connecting to the db?
		
		User userAdd = new User(700, "idk", "firstname6", "lastname6", true, false, "1234");
		
		//int originalSize = directory.selectAllUsers().size();
		
		//assertTrue(directory.insertUser(userAdd));
		
		//int newSize = directory.selectAllUsers().size();
		
		//assertEquals(originalSize + 1, newSize);
		
		//assertTrue(directory.selectAllUsers().contains(userAdd));
		
	}
	
	//TODO test remove user
	//TODO more tests
	
	
	

}
