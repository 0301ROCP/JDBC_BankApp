package com.mybank.presentation.models;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.Controller;
import com.mybank.presentation.controller.actions.Action;

public class NullBlock extends InteractionBlock {
	
	final static Logger Log = Logger.getLogger(NullBlock.class);

	//------------CONSTRUCTOR----------
	public NullBlock() {
		this.blockType = BlockType.NULL;
		this.instructions = "Nothing to see on this page!";
	}
	
	
	//--------------METHOD----------
	
	@Override
	public Queue<Action> run(User currentUser) {
		
		//Log.debug("NullBlock run()");
		
		Queue<Action> actionQueue = new LinkedList<Action>();
		return actionQueue;
	}

}
