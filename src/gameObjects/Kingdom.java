package gameObjects;

import java.util.ArrayList;


public class Kingdom {
	
	private final int DEFAULT_BUILDING_SLOTS = 6;
	
	
	private LocationNode myLocation;
	private int buildingSlots;
	
	
	private ArrayList<Building> buildings;


	public LocationNode getMyLocation() {
		return myLocation;
	}


	public void setMyLocation(LocationNode myLocation) {
		this.myLocation = myLocation;
	}
	
	
	
	
	
}
