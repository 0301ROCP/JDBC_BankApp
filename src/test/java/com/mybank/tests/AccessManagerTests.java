package com.mybank.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotEquals;


import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.repository.userdao.UserDao;
import com.mybank.service.access_mgt.AccessManager;
import com.mybank.service.access_mgt.AccessMgrImpl;

import junit.framework.Assert;

public class AccessManagerTests {
	
	static AccessManager accessMgr;
	
	
	@Mock
	static UserDao directory;
	
	
	@BeforeClass
	public static void setup() {
		
		directory = Mockito.mock(UserDao.class);
		accessMgr = new AccessMgrImpl(directory);
		
		User testUser1 = new User(7, "stcstc", "Gil", "Phish", true, true, "1234");
		User testUser2 = new User(1, "dummyuser01", "Dummy", "User", true, false, "1234");
		User fakeUser = new User(1, "userName", "firstName", "lastName", true, false, "password");
		User fakeUser2 = new User(2, "userName2", "firstName", "lastName", true, false, "password");
		Account testAccount1 = new Account(14, "Savings", testUser1, "Lucky", false, null, null, 88888, testUser1, true, "approved");
		Account testAccount2 = new Account(12, "Chhecking", testUser2, "Bob", false, null, null, 3030, testUser2, true, "approved");
		

		List<User> fakeUserList = new ArrayList<User>();
		fakeUserList.add(testUser1);

		

		Mockito.when(directory.selectUsersByAttribute("username", "stcstc")).thenReturn(fakeUserList);
				
		Mockito.when(directory.selectUserByID(7)).thenReturn(testUser1);
		
		Mockito.when(directory.selectUserByUsername("dummyuser01")).thenReturn(testUser2);

		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testVerifyExists() {
		
		Assert.assertTrue(accessMgr.verifyExists("username","stcstc"));
		
		Assert.assertFalse(accessMgr.verifyExists("username", "notinlist"));
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testLookupUserUPI() {
		
		User testUser1 = new User(7, "stcstc", "Gil", "Phish", true, true, "1234");
		
		Assert.assertEquals(testUser1.getUserName(), accessMgr.lookupUser("upi","7").getUserName());
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testLookupUserUsername() {
		
		User testUser2 = new User(1, "dummyuser01", "Dummy", "User", true, false, "1234");

		Assert.assertEquals(testUser2.getUserName(), accessMgr.lookupUser("username","dummyuser01").getUserName());
	
	}
	
	
	

}
