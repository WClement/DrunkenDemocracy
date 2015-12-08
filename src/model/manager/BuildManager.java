package model.manager;

import gameObjects.Building;
import gameObjects.BuildingFactory;
import gameObjects.BuildingProperties;
import gameObjects.Kingdom;
import gameObjects.LocationNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import model.Model;
import controller.command.ChatCommand;

public class BuildManager extends Manager {


	Pattern p = Pattern.compile("[a-zA-Z0-9]X+ [0-9]X+");

	List<Building> built;
	List<Building> inProgress;

	public final int BUILD_TIME = 60;
	int buildTimer = 0;
	

	public BuildManager(Model myModel) {
		super(myModel);
		built = new ArrayList<Building>();
		inProgress = new ArrayList<Building>();
	}

	// maintaining resources watched by the manager
	public void manage() {
		
		if (buildTimer >= 60) { // check for command every 60 frames
			if (executeNextReceiverCommand())
				buildTimer = 0;
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
		incrementTimers();

	}

	public void newBuilding(String toBuild, int kingdomId, int nodeIndex) {

		for (BuildingProperties b : BuildingProperties.values()) {
			if (toBuild.toLowerCase().equals(b.name)) {
				
				LocationNode loc;
				
				Building newBuilding = BuildingFactory.createBuilding(b);
	
				if (nodeIndex == -1) {
					loc = getMyModel().mapManager.getKingdomLocation(kingdomId);
				}
				else {
					loc = getMyModel().mapManager.getLocation(nodeIndex);
				}
				boolean canAfford = getMyModel().kingdomManager.canAfford(b.goldCost, kingdomId);
				newBuilding.setLocation(loc);
				
				// adding building to map
				boolean hasSpace = loc.addBuilding(newBuilding);
				
				if (hasSpace && canAfford){
					newBuilding.setTeamId(kingdomId);
					
					// adding building to kingdom's building list
					getMyModel().kingdomManager.addBuilding(newBuilding, kingdomId);
					newBuilding.construct(); // start adding building progress
					System.out.println("Now building: " + newBuilding.getName());
					getMyModel().sendChatMessage("Now building: " + newBuilding.getName());
					inProgress.add(newBuilding); //in progress list
				}
				else{
					if (!hasSpace) {
						System.out.println("Not enough space for " + newBuilding.getName());
						getMyModel().sendChatMessage("Not enough space for " + newBuilding.getName());
					}
					if (!canAfford) {
						System.out.println("Kingdom " + kingdomId + " cant afford " + newBuilding.name);
						getMyModel().sendChatMessage("Not enough gold for " + newBuilding.getName());
					}
				}
			}
		}
	}
	
	// break down buildCommand into parts
	private void parseCommand(ChatCommand cmd) {
		
		String check = cmd.getSuffix().trim();
				
		
		if (check.matches("[.]*[0-9]+[.]*")) { //is one number
			// idk something with one number
			System.out.println("BuildManager.parseCommand() received a number");
		}
		else if (check.equals("info")) {
			System.out.println("BuildManager.parseCommand() received info");

			for (Building b : built) {
				System.out.println(b.getName() + " is at buildingSlot " + b.getLocationIndex() + " of location node " + b.getLocation().getId());
			}
			for (Kingdom k : getMyModel().kingdomManager.kingdomList) {
			System.out.println("Kingdom" + k.getKingdomId() + " has " + k.getGold() + " GOLD.");
			}
		}
		else if (check.equals("y")) {
			System.out.println("BuildManager.parseCommand() received y");
			// yes vote for building
		}
		else if (check.equals("n")) {
			System.out.println("BuildManager.parseCommand() received n");
			// no vote for building
		}
		else if (check.matches("[a-zA-Z0-9]+[ ]+[0-9]+")) {
			System.out.println("BuildManager.parseCommand() received a building and a node");
			// building name, node to build
			// B: Tower 500
			// B:    Tower 20
			String prefab = check.substring(0, check.indexOf(' '));
			int nodeIndex = Integer.parseInt(check.substring(check.indexOf(' ')+1, check.length()));
			newBuilding(prefab, cmd.getControllerId(), nodeIndex);

		}
		else if (check.matches("[a-zA-Z0-9]+")) {
			System.out.println("BuildManager.parseCommand() received text. Checking for prefab. Assuming kingdom");
			// any number of spaces, name of building, defaults to kingdom
			newBuilding(check, cmd.getControllerId(), -1);
		}	
		else {
			
		}
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

	@Override
	public void incrementTimers() {
		// TODO Auto-generated method stub
		buildTimer++;
	}
	
	
}
