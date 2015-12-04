package model.manager;

import gameObjects.Building;
import gameObjects.GenericBuilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import model.Model;

public class BuildManager implements Manager {

	Model myGame;
	Pattern p = Pattern.compile("[a-zA-Z0-9]X+ [0-9]X+");
	
	List<Building> built;
	List<Building> inProgress;
	
	public BuildManager(Model myGame) {
		this.myGame = myGame;
		built = new ArrayList<Building>();
		inProgress = new ArrayList<Building>();
	}
	
	// maintaining resources watched by the manager
	public void manage() {
		for (Iterator<Building> iterator = inProgress.iterator(); iterator.hasNext();) {
			Building curr = iterator.next();
			if(!curr.addProgress()) { // if returns true, its finished
				myGame.sendChatMessage("Building " + curr.getName() + " has completed.");
				built.add(curr); // add to built buildings
				iterator.remove(); // remove from inProgress list
			}
			else { // report current progress
				String currProgress = curr.printProgressPretty();
				if (!currProgress.equals("")) {
					myGame.sendChatMessage(currProgress);
				}
			}
		}
	}
	
	public void newBuilding(String cmd) {
		// assert cmd is of format: "name maxProgress"
		if (!Pattern.matches("[ ]?[a-zA-Z0-9]+ [0-9]+", cmd)) {
			System.out.printf("Build suffix %s is in wrong format\n", cmd);
			return;
		}
		// remove one allowed whitespace before args
		if (cmd.indexOf(' ') == 0) {
			cmd = cmd.substring(1, cmd.length());
		}
		
		
		String name = cmd.substring(0, cmd.indexOf(' '));
		int maxProgress = Integer.parseInt(cmd.substring(cmd.indexOf(' ')+1, cmd.length()));
		Building newBuilding = new GenericBuilding(name, maxProgress);
		myGame.sendChatMessage("Now building: " + name);
		inProgress.add(newBuilding);
	}
	
	public List<Building> getBuiltList() {
		return built;
	}
	
	public List<Building> getInProgressList() {
		return inProgress;
	}
	
}
