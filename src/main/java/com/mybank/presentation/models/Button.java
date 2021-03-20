package com.mybank.presentation.models;

import com.mybank.presentation.view.Page;

public class Button {
	
	private String keyStroke;
	private String name;
	private String target;


	
	public Button(String keyStroke, String name, String target){
		this.keyStroke = keyStroke;
		this.name = name;
		this.target = target;
	}
		
	public String getKey() {
		return keyStroke;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTarget() {
		return target;
	}
	


	
	public void printDashes(String l) {
		StringBuilder line = new StringBuilder(" ");
		int buttonLength = name.length()+4;
		
		for(int d = 0; d<buttonLength+2; d++) {
			line.append(l);
		}
		
		System.out.print(line);
		
	}
	
	public void printMiddle() {
		StringBuilder line2 = new StringBuilder();		
		line2.append("| " + name + " (" + keyStroke + ") |");
		
		System.out.print(line2);
	}
	
	
	

//	public ?? getTarget(){
//		return target;
//	}
	
}
