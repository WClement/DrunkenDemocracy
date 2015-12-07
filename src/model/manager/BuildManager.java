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
				System.out.println("Building " + curr.getName()
						+ " has completed.");
				built.add(curr); // add to built buildings
				iterator.remove(); // remove from inProgress list
			} else { // report current progress
//				System.out.println("Added progress to " + curr.getName() + ".");
				String currProgress = curr.printProgressPretty();
				if (!currProgress.equals("")) {
					getMyModel().sendChatMessage(currProgress);
				}
			}
		}
	}

	
	public void newBuilding(String toBuild, int kingdomIndex) {

		for (BuildingProperties b : BuildingProperties.values()) {
			if (toBuild.toLowerCase().equals(b.name)) {
				Building newBuilding = BuildingFactory.createBuilding(b);
				// newBuilding.setLocation(theLocation);
				// theMap.place(newBuilding);
				
				
				LocationNode loc = getMyModel().mapManager.getKingdomLocation(kingdomIndex);
				
				newBuilding.setLocation(loc);
				boolean hasSpace = loc.addBuilding(newBuilding);
				if (hasSpace){
					newBuilding.construct();
					System.out.println("Now building: " + newBuilding.getName());
					getMyModel().sendChatMessage("Now building: " + newBuilding.getName());
					inProgress.add(newBuilding);
				}
				else{
					System.out.println("Not enough space for " + newBuilding.getName());
					getMyModel().sendChatMessage("Not enough space for " + newBuilding.getName());
				}
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
		else if (check.equals("info")) {
			for (Building b : built) {
				System.out.println(b.getName() + " is at index " + b.getLocationIndex() + " at location node " + b.getLocation().getId());
			}
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
			newBuilding(cmd.getSuffix(), cmd.getControllerId());

		}
		else if (check.matches("[ ]?[a-zA-Z0-9]")) {
			// any number of spaces, name of building
			newBuilding(cmd.getSuffix(), cmd.getControllerId());

		}
			
		newBuilding(check.toUpperCase(), cmd.getControllerId());
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
