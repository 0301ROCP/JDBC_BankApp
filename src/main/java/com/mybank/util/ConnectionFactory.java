package com.mybank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/mybank";
	private static final String USERNAME = "bankUser";
	private static final String PASSWORD = "myb4nk";
	
	
	private static Connection myConnection; //connection object	
	public static Connection getConnection() { //connection method
		
		try {
			myConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
		catch (SQLException e) {
			//TODO better error message!
			System.out.println("util.ConnectionFactory.java: Connection to DB failed!");
			e.printStackTrace();
		}
		
		return myConnection;
			
	}
	
}
