package com.mybank.presentation.models;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mybank.models.User;
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
	public ArrayList<Action> run(User currentUser) {
		
		//Log.debug("NullBlock run()");
		
		ArrayList<Action> actionQueue = new ArrayList<Action>();
		return actionQueue;
	}

}
