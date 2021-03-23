package com.mybank.presentation.models;

import java.util.Queue;
import java.util.Scanner;

import com.mybank.presentation.controller.actions.Action;

public abstract class InteractionBlock {

	protected enum BlockType {MENU, FORM, NULL};
	protected BlockType blockType;
	protected String instructions;
	protected Queue<Action> actionQueue;
	Scanner sc = new Scanner(System.in);
	
	
	//------------GETTERS-------------
	
	public BlockType getType() {
		return blockType;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	
	//----------ABSTRACT METHODS-----
	
	public abstract Queue<Action> run();
	
	
	//------------METHODS-------------
	
	public void print() {
		System.out.println(instructions);
	}


}
