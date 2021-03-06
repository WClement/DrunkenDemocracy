package model;

import gameObjects.Building;
import gameObjects.Kappa;
import gameObjects.LocationNode;

import java.util.ArrayList;
import java.util.List;

import model.manager.AttackManager;
import model.manager.BuildManager;
import model.manager.KappaManager;
import model.manager.KingdomManager;
import model.manager.KreygasmManager;
import model.manager.MapManager;
import model.manager.UnitManager;
import model.manager.VoteManager;
import model.receiver.AttackReceiver;
import model.receiver.BuildReceiver;
import model.receiver.KappaReceiver;
import model.receiver.KreygasmReceiver;
import model.receiver.UnitReceiver;
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
	UnitReceiver unitReceiver;
	
	// managers for commands
	public BuildManager buildManager;
	public KappaManager kappaManager;
	public KreygasmManager kreyManager;
	public VoteManager voteManager;
	public AttackManager attackManager;
	public MapManager mapManager;
	public KingdomManager kingdomManager;
	public UnitManager unitManager;
	
	// game timer
	TimedExecutor time;
	
	public Model() {
		
		controllers = new ArrayList<Controller>();
		
		buildManager = new BuildManager(this);
		kappaManager = new KappaManager(this);
		kreyManager = new KreygasmManager(this);
		voteManager = new VoteManager(this);
		attackManager = new AttackManager(this);
		mapManager = new MapManager(this);
		kingdomManager = new KingdomManager(this);
		unitManager = new UnitManager(this);
				
		kappaReceiver = new KappaReceiver(kappaManager);
		attackReceiver = new AttackReceiver(attackManager);
		buildReceiver = new BuildReceiver(buildManager); // also sets manager's receiver
		kreyReceiver = new KreygasmReceiver(kreyManager);
		voteReceiver = new VoteReceiver(voteManager);
		unitReceiver = new UnitReceiver(unitManager);
		
		time = new TimedExecutor(this);
	}
	
	public void startNewGame() {
		mapManager.createMap1();
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
		startNewGame();
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
		case UNIT:
			System.out.println("Unit cmd detected");
			unitReceiver.addCommand(cmd);
		default:
			break;
		}
		// Prints the class of the command
		//System.out.println(cmd.getClass().toString());
	}
	
	public void manageManagers() {
		buildManager.manage();
		kappaManager.manage();
		kingdomManager.manage();
		unitManager.manage();
	}
	

	// TEMPORARY
	public List<Building> getBuildingsForDrawing() {
		return buildManager.getBuiltList();
	}
	
	public LocationNode getLocation(){
		return mapManager.getKingdomLocation(0);
	}
	
	// TEMPORARY
	public List<Kappa> getKappas() {
		return kappaManager.getKappaList();
	}
}
