package com.mybank.presentation.view;

import java.util.Scanner;

public class Login extends Page{
	
	public Login() {
		super();
		
		this.name = "Login";
		this.header = "Please Log In!";
		
		this.menu.addUtils(); //TODO fix this logic (cant click back or quit bc typing in pw)
		
	}

	
	@Override
	public String run() {
		Scanner sc = new Scanner(System.in);

		print();
		
		System.out.println("Please enter your username");
		String userName = sc.next();
		System.out.println("Please enter your password");
		String password = sc.next();
		
		//TODO verify
		//

		clear();
		return "CustomerDB";
	}

}
