package model;

import java.util.List;

import view.View;
import model.manager.BuildManager;
import model.manager.Kappa;
import model.manager.KappaManager;
import model.receiver.AttackReceiver;
import model.receiver.BuildReceiver;
import model.receiver.KappaReceiver;
import model.receiver.KreygasmReceiver;
import model.receiver.VoteReceiver;
import controller.Controller;
import controller.command.ChatCommand;

public class Model {

	Controller myController; // our controller
	View myView;
		
	// command receivers
	KappaReceiver kappaReceiver;
	AttackReceiver attackReceiver;
	BuildReceiver buildReceiver;
	KreygasmReceiver kreyReceiver;
	VoteReceiver voteReceiver;
	
	// managers for commands
	BuildManager buildManager;
	KappaManager kappaManager;
	
	// game timer
	TimedExecutor time;
	
	public Model(Controller myController) {
		this.myController = myController;
		kappaReceiver = new KappaReceiver(this);
		attackReceiver = new AttackReceiver(this);
		buildReceiver = new BuildReceiver(this);
		kreyReceiver = new KreygasmReceiver(this);
		voteReceiver = new VoteReceiver(this);
		
		buildManager = new BuildManager(this);
		kappaManager = new KappaManager(this);
		
		time = new TimedExecutor(this);
	}
	
	
	public void setView(View myView) {
		this.myView = myView;
	}
	
	public View getView() {
		return myView;
	}
	
	public void start() {
		time.start();
	}
	
	public void sendChatMessage(String msg) {
		myController.sendChatMessage(msg);
	}
	
	public void receiveCommand(ChatCommand cmd) {
		
		switch (cmd.getMyEnum()) {
		case KAPPA: 
			kappaReceiver.addCommand(cmd);
//			kappaReceiver.executeNextCommand();
			break;
		case ATTACK:
			attackReceiver.addCommand(cmd);
			attackReceiver.executeNextCommand();
			break;
		case BUILD:
			buildReceiver.addCommand(cmd);
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
	
	public void executeKappaAction() {
		kappaReceiver.executeNextCommand();
	}
	public void buildAction(ChatCommand cmd) {
		
//		myController.sendChatMessage("Now building: " + cmd.getSuffix());
		buildManager.newBuilding(cmd.getSuffix());
	}
	
	public void kappaAction(ChatCommand cmd) {
		myController.sendChatMessage("Wow Kappa");
		kappaManager.createKappa();
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
	
	// TEMPORARY
	public List<Kappa> getKappas() {
		return kappaManager.getKappaList();
	}
	
}
