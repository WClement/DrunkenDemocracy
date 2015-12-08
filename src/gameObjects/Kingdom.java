package gameObjects;

import java.util.ArrayList;


public class Kingdom {
	
	private final int DEFAULT_BUILDING_SLOTS = 6;
	private int kingdomId;
	
	private int gold;
	private LocationNode myLocation;
	private int buildingSlots;
	private ArrayList<Building> buildings;

	// defaults
	public Kingdom() {
		gold = 10000;
		buildings = new ArrayList<Building>();
	}
	
	public LocationNode getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(LocationNode myLocation) {
		this.myLocation = myLocation;
		buildingSlots = myLocation.getBuildingSlots();
	}
	
	public void addGold(int gold) {
		this.setGold(this.getGold() + gold);
	}
	
	public void removeGold(int gold) {
		this.setGold(this.getGold() - gold);
	}
	
	public void addBuilding(Building newBuilding) {
		buildings.add(newBuilding);
	}
	
	public void removeBuilding(Building toRemove) {
		buildings.remove(toRemove);
	}

	public int getKingdomId() {
		return kingdomId;
	}

	public void setKingdomId(int kingdomId) {
		this.kingdomId = kingdomId;
	}

	public int getBuildingSlots() {
		return buildingSlots;
	}


	public void setBuildingSlots(int buildingSlots) {
		this.buildingSlots = buildingSlots;
	}


	public void doIncome() {
		// TODO Auto-generated method stub
		for (Building b : buildings) {
			setGold(getGold() + b.currentIncome());
		}
	}


	public int getGold() {
		return gold;
	}


	public void setGold(int gold) {
		this.gold = gold;
	}
	
	
	
	
	
}
