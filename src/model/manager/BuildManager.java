package model.manager;

import gameObjects.Building;
import gameObjects.BuildingFactory;
import gameObjects.BuildingProperties;
import gameObjects.GenericBuilding;
import gameObjects.LocationNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import controller.command.ChatCommand;
import model.Model;
import model.receiver.BuildReceiver;
import model.receiver.CommandReceiver;

public class BuildManager extends Manager {


	Pattern p = Pattern.compile("[a-zA-Z0-9]X+ [0-9]X+");

	List<Building> built;
	List<Building> inProgress;

	int buildCounter = 600;
	

	public BuildManager(Model myModel) {
//		this.myGame = myGame;
		super(myModel);
		built = new ArrayList<Building>();
		inProgress = new ArrayList<Building>();
	}

	// maintaining resources watched by the manager
	public void manage() {
		
		if (buildCounter >= 60) { // check for command every 60 frames
			if (executeNextReceiverCommand())
				buildCounter = 0;
		} else {
			buildCounter++;
		}

		// add progress to existing buildings and finish buildings
		for (Iterator<Building> iterator = inProgress.iterator(); iterator
				.hasNext();) {
			Building curr = iterator.next();
			if (!curr.addProgress()) { // if returns true, its finished
				getMyModel().sendChatMessage("Building " + curr.getName()
						+ " has completed.");
				built.add(curr); // add to built buildings
				iterator.remove(); // remove from inProgress list
			} else { // report current progress
				String currProgress = curr.printProgressPretty();
				if (!currProgress.equals("")) {
					getMyModel().sendChatMessage(currProgress);
				}
			}
		}
	}

	
	public void newBuilding(String toBuild) {

		for (BuildingProperties b : BuildingProperties.values()) {
			if (toBuild.toLowerCase().equals(b.name)) {
				Building newBuilding = BuildingFactory.createBuilding(b);
				// newBuilding.setLocation(theLocation);
				// theMap.place(newBuilding);
				getMyModel().sendChatMessage("Now building: " + newBuilding.getName());
				inProgress.add(newBuilding);
				newBuilding.setLocation(new LocationNode());
				newBuilding.construct();
			}
		}
		
		/*
		String name = cmd.substring(0, cmd.indexOf(' '));
		int maxProgress = Integer.parseInt(cmd.substring(cmd.indexOf(' ') + 1,
				cmd.length()));
		Building newBuilding = new GenericBuilding(name, maxProgress);
		getMyModel().sendChatMessage("Now building: " + name);
		inProgress.add(newBuilding);
		*/
	}
	
	// break down buildCommand into parts
	private void parseCommand(ChatCommand cmd) {
		
		String check = cmd.getSuffix().trim();
		
		if (check.matches(".*\\d.*")) { //is one number
			// idk something with one number
		}
		else if (check.equals("y")) {
			// yes vote for building
		}
		else if (check.equals("n")) {
			// no vote for building
		}
		else if (check.matches("[ ]?[a-zA-Z0-9]+ [0-9]+")) {
			// any number of spaces, building name, frames to build
			// B: Tower 500
			// B:    Tower 20
			newBuilding(cmd.getSuffix());

		}
		else if (check.matches("[ ]?[a-zA-Z0-9]")) {
			// any number of spaces, name of building
			newBuilding(cmd.getSuffix());

		}
			
		newBuilding(check.toUpperCase());
	}

	public List<Building> getBuiltList() {
		return built;
	}

	public List<Building> getInProgressList() {
		return inProgress;
	}

	@Override
	public void executeCommand(ChatCommand cmd) {
		// myController.sendChatMessage("Now building: " + cmd.getSuffix());
		parseCommand(cmd);
	}

}
