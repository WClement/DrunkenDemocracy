package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildManager {

	List<Building> built;
	List<Building> inProgress;
	
	public BuildManager() {
		built = new ArrayList<Building>();
		inProgress = new ArrayList<Building>();
	}
	
	public void manage() {
		
		for (Iterator<Building> iterator = inProgress.iterator(); iterator.hasNext();) {
			Building curr = iterator.next();
			if(curr.addProgress()) { // if returns true, its finished
				System.out.println("Building " + curr.getName() + " has completed.");
				built.add(curr);
				iterator.remove();
//				inProgress.remove(curr);
			}
			else {
				if (curr.getProgressPercentage() > 0.45 && curr.getProgressPercentage() < 0.55)
					System.out.printf(curr.getName() + " is at %d", curr.getProgressPercentage());
			}
		}
	}
	
	public void newBuilding(String cmd) {
		// assume cmd is of format: "name maxProgress"
		if (cmd.indexOf(' ') == 0) {
			cmd = cmd.substring(1, cmd.length());
		}
		System.out.println("new building cmd is: " + cmd);
		String name = cmd.substring(0, cmd.indexOf(' '));
		int maxProgress = Integer.parseInt(cmd.substring(cmd.indexOf(' ')+1, cmd.length()));
		Building newBuilding = new GenericBuilding(name, maxProgress);
		inProgress.add(newBuilding);
	}
}
