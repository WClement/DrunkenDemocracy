package gameObjects;

import java.util.ArrayList;

public class LocationNode {

	Track[] myTracks = new Track[6];
	private int buildingSlots;
	private int buildingCount = 0;
	ArrayList<Building> buildings;
	
	public boolean addBuilding(Building toAdd) {
		if (buildingCount < buildingSlots) {
			buildings.add(toAdd);
			buildingCount++;
			return true;
		}
		return false;
	}
	
	public void removeBuilding(Building toDelete) {
		for (Building b : buildings) {
			if (b.equals(toDelete)) { // removes building from arrayList
				buildings.remove(toDelete);
			}
		}
	}
	
	public int getBuildingSlots() {
		return buildingSlots;
	}
	public void setBuildingSlots(int buildingSlots) {
		this.buildingSlots = buildingSlots;
	}
	
	
	
	
}
