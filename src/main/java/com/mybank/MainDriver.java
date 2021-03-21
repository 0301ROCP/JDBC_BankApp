package com.mybank;

import com.mybank.presentation.controller.Controller;
import com.mybank.util.ConnectionFactory;


public class MainDriver {
	
	public static void main(String[] args) {
				
		Controller appController = new Controller();
		
		appController.start();
		
		
		
	}

}
