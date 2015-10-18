package model;

import controller.ChatCommand;
import controller.Controller;

public class Game {

	Controller myController; //our controller
	
	// these are defined by hand for now,
	// but at some point we should have a 
	// list of receivers to deal with commands
	BuildReceiver buildReceiver;
	KreygasmReceiver kreyReceiver;
	AttackReceiver attackReceiver;
	KappaReceiver kappaReceiver;
	
	public Game(Controller myController) {
		this.myController = myController;
		buildReceiver = new BuildReceiver(this);
		kappaReceiver = new KappaReceiver(this);
	}
	
	public void receiveCommand(ChatCommand cmd) {
		
		switch (cmd.getMyEnum()) {
		case KAPPA: 
			kappaReceiver.addCommand(cmd);
			kappaReceiver.executeNextCommand();
			break;
		case ATTACK:
//			attackReceiver.addCommand(cmd);
			myController.sendChatMessage("Attacking: " + cmd.getSuffix());
			break;
		case BUILD:
//			cmd.setReceiver(buildReceiver);
			buildReceiver.addCommand(cmd);
			buildReceiver.executeNextCommand();
			break;
		case KREYGASM:
			myController.sendChatMessage("You know I'm all about that Kreygasm");
			break;
		case VOTE:
//			voteReceiver.addCommand(cmd);
			myController.sendChatMessage("Voted for: " + cmd.getSuffix());
			break;
		default:
			break;
		}
		
		// Prints the class of the command
		System.out.println(cmd.getClass().toString());
	}
	
	public void buildAction(ChatCommand cmd) {
		myController.sendChatMessage("Now building: " + cmd.getSuffix());
	}
	
	public void kappaAction(ChatCommand cmd) {
		myController.sendChatMessage("Wow Kappa");
	}
	
}
