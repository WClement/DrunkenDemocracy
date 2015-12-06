package model;

import gameObjects.Building;

import java.util.ArrayList;
import java.util.List;

import model.manager.AttackManager;
import model.manager.BuildManager;
import model.manager.Kappa;
import model.manager.KappaManager;
import model.manager.KreygasmManager;
import model.manager.VoteManager;
import model.receiver.AttackReceiver;
import model.receiver.BuildReceiver;
import model.receiver.KappaReceiver;
import model.receiver.KreygasmReceiver;
import model.receiver.VoteReceiver;
import view.View;
import controller.Controller;
import controller.command.ChatCommand;

public class Model {

	Controller myController; // our controller
	ArrayList<Controller> controllers;
	private int nextControllerId = 0;
	
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
	KreygasmManager kreyManager;
	VoteManager voteManager;
	AttackManager attackManager;
	
	// game timer
	TimedExecutor time;
	
	public Model() {
		
		controllers = new ArrayList<Controller>();
		
		buildManager = new BuildManager(this);
		kappaManager = new KappaManager(this);
		kreyManager = new KreygasmManager(this);
		voteManager = new VoteManager(this);
		attackManager = new AttackManager(this);
		
		kappaReceiver = new KappaReceiver(kappaManager);
		attackReceiver = new AttackReceiver(attackManager);
		buildReceiver = new BuildReceiver(buildManager); // also sets manager's receiver
		kreyReceiver = new KreygasmReceiver(kreyManager);
		voteReceiver = new VoteReceiver(voteManager);
		
		time = new TimedExecutor(this);
		
	}
	
	public void addController(Controller toAdd) {
		controllers.add(toAdd);
		toAdd.setControllerId(nextControllerId);
		nextControllerId++;
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
	
	public void sendChatMessage(String msg, int controllerId) {
		controllers.get(controllerId).sendChatMessage(msg);
	}
	
	public void sendChatMessage(String msg) {
		controllers.get(0).sendChatMessage(msg);
	}
	
	public void receiveCommand(ChatCommand cmd) {
		
		switch (cmd.getMyEnum()) {
		case KAPPA:
			kappaReceiver.addCommand(cmd);
			break;
		case ATTACK:
			attackReceiver.addCommand(cmd);
//			attackReceiver.executeNextCommand();
			break;
		case BUILD:
			buildReceiver.addCommand(cmd);
			break;
		case KREYGASM:
			kreyReceiver.addCommand(cmd);
//			kreyReceiver.executeNextCommand();
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
		kappaManager.manage();
	}
	

	// TEMPORARY
	public List<Building> getBuildingsForDrawing() {
		return buildManager.getBuiltList();
	}
	
	// TEMPORARY
	public List<Kappa> getKappas() {
		return kappaManager.getKappaList();
	}
	
}
