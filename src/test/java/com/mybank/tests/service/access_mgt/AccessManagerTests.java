package com.mybank.tests.service.access_mgt;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.mybank.models.User;
import com.mybank.repository.userdao.UserDao;
import com.mybank.service.access_mgt.AccessManager;
import com.mybank.service.access_mgt.AccessMgrImpl;

public class AccessManagerTests {
	
	static AccessManager accessMgr;
	
	
	@Mock
	static UserDao directory;
	
	
	@BeforeClass
	public static void setup() {
		
		directory = Mockito.mock(UserDao.class);
		accessMgr = new AccessMgrImpl(directory);
		
		
		User fakeUser = new User(1, "userName", "firstName", "lastName", true, false, "password");
		User fakeUser2 = new User(2, "userName2", "firstName", "lastName", true, false, "password");
		List<User> fakeUserList = new ArrayList<User>();
		fakeUserList.add(fakeUser);
		fakeUserList.add(fakeUser2);
		
		Mockito.when(directory.insertUser(fakeUser)).thenReturn(true);
		Mockito.when(directory.selectUserByName("firstName","lastName")).thenReturn(fakeUserList);
		
	}
	
	@Test
	public void testCreateNewUser() {
		
		//TOASK how do I use mockito.when with user input?
		
	}
	

}
