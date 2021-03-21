package com.mybank.presentation.view;


import java.util.LinkedHashMap;
import java.util.Scanner;

import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.Menu;





public abstract class Page {
	
	String name;
	String header;
	Menu menu;
	String instructions;
	
	Scanner sc = new Scanner(System.in);
	
	
	//CONSTRUCTOR:
	public Page() { 
		this.instructions = "Type the right letter then hit enter!";
		this.menu = new Menu(instructions);
		
	}

	
	//GETTERS AND SETTERS:
	public String getName() {
		return this.name;
	}
	
	//METHODS:
	public void print() {
		System.out.println(header);
		menu.print();		
	}
	
	public String getInput() {
		String input = sc.nextLine();
		return input;
	}
	
	public static void clear() {
		pause();
		
		for(int i = 0; i<5; i++) {
			System.out.println();
		}
	}
	
	public static void pause() {
		long startTime = System.currentTimeMillis();
		System.out.println("redirecting...");
        while(startTime >= System.currentTimeMillis() - 2000); // do nothing
       
	}
	
	
	public String run() { 
		print(); //print this page
		
		String result = getInput(); //TODO ADD TRY CATCH
		
		LinkedHashMap<String,Button> theseButtons = menu.getButtons();
		Button selection = theseButtons.get(result);
		
		System.out.println("You selected: "+selection.getName()+".  ");
		clear(); //clear the console
		return selection.getTarget();
	}



}
