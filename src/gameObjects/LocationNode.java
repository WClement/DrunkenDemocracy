package gameObjects;

import java.util.ArrayList;

public class LocationNode {

	final int MAX_BUILDINGS = 6;
	
	Track[] myTracks = new Track[6];
	private int buildingSlots;
	private int buildingCount = 0;
	ArrayList<Building> buildings;
	
	public int id;
	
	
	//
	// TEMPORARY FOR DRAWING
	public int x = 200;
	public int y = 200;
	// END TEMPORARY
	//
	
	public LocationNode() {
		buildings = new ArrayList<Building>(MAX_BUILDINGS);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	
	public boolean addBuilding(Building toAdd) {
		if (buildingCount < buildingSlots) {
			buildings.add(toAdd);
			buildingCount++;
			toAdd.setLocationIndex(buildings.indexOf(toAdd));
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
	
	public Building getBuilding(int slot) {
		if (slot < buildingSlots) {
			if (buildings.get(slot) != null) {
				return buildings.get(slot);
			}
		}
		return null;
	}
	
	
}
