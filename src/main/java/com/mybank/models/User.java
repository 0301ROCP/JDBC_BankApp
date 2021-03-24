package com.mybank.models;

public class User {
	
	private int upi;
	private String userName;
	private String firstName;
	private String lastName;
	private boolean isCustomer;
	private boolean isEmployee;
	private String password;
	
	
	//---------CONSTRUCTOR---------

	public User(int upi, String userName, String firstName, String lastName, boolean isCustomer, boolean isEmployee, String password) {
		this.upi = upi;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isCustomer = isCustomer;
		this.isEmployee = isEmployee;
		this.password = password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	//---------GETTERS & SETTERS--------
	
	



	public int getUpi() {
		return upi;
	}
	
	public void setUpi(int upi) {
		this.upi = upi;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	


	//-----------TOSTRING----------
	
	@Override
	public String toString() {
		return "User [upi=" + upi + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", isCustomer=" + isCustomer + ", isEmployee=" + isEmployee + ", password=" + password + "]";
	}

	


}
