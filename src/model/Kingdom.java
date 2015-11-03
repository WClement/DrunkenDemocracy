package model;

import model.receiver.AttackReceiver;
import model.receiver.BuildReceiver;
import model.receiver.KappaReceiver;
import model.receiver.KreygasmReceiver;
import model.receiver.VoteReceiver;
import controller.Controller;
import controller.command.ChatCommand;

public class Game {

	Controller myController; //our controller
	
	// these are defined by hand for now,
	// but at some point we should have a 
	// list of receivers to deal with commands
	KappaReceiver kappaReceiver;
	AttackReceiver attackReceiver;
	BuildReceiver buildReceiver;
	KreygasmReceiver kreyReceiver;
	VoteReceiver voteReceiver;
	
	BuildManager buildManager;
	
	TimedExecutor time;
	
	public Game(Controller myController) {
		this.myController = myController;
		kappaReceiver = new KappaReceiver(this);
		attackReceiver = new AttackReceiver(this);
		buildReceiver = new BuildReceiver(this);
		kreyReceiver = new KreygasmReceiver(this);
		voteReceiver = new VoteReceiver(this);
		
		buildManager = new BuildManager();
		
		time = new TimedExecutor(this);
	}
	
	public void start() {
		time.gameLoop();
	}
	
	public void receiveCommand(ChatCommand cmd) {
		
		switch (cmd.getMyEnum()) {
		case KAPPA: 
			kappaReceiver.addCommand(cmd);
			kappaReceiver.executeNextCommand();
			break;
		case ATTACK:
			attackReceiver.addCommand(cmd);
			attackReceiver.executeNextCommand();
			break;
		case BUILD:
			buildReceiver.addCommand(cmd);
			//buildReceiver.executeNextCommand();
			break;
		case KREYGASM:
			kreyReceiver.addCommand(cmd);
			kreyReceiver.executeNextCommand();
			break;
		case VOTE:
			voteReceiver.addCommand(cmd);
			break;
		default:
			break;
		}
		
		// Prints the class of the command
		System.out.println(cmd.getClass().toString());
	}
	
	public void manageManagers() {
		buildManager.manage();
	}
	
	public boolean executeBuildAction() {
		return buildReceiver.executeNextCommand();
	}
	public void buildAction(ChatCommand cmd) {
		myController.sendChatMessage("Now building: " + cmd.getSuffix());
		buildManager.newBuilding(cmd.getSuffix());
	}
	
	public void kappaAction(ChatCommand cmd) {
		myController.sendChatMessage("Wow Kappa");
	}

	public void attackAction(ChatCommand cmd) {
		myController.sendChatMessage("Attacking: " + cmd.getSuffix());
	}

	public void voteAction(ChatCommand cmd) {
		myController.sendChatMessage("Voted for: " + cmd.getSuffix());
	}

	public void kreygasmAction(ChatCommand cmd) {
		myController.sendChatMessage("You know I'm all about that Kreygasm");
	}
	
}
