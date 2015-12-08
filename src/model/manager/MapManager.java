package model.manager;

import java.util.ArrayList;

import controller.command.ChatCommand;
import model.Model;
import gameObjects.Kingdom;
import gameObjects.LocationNode;
import gameObjects.Map;


/*
 * MapManager:
 * Manages the currentMap, as well as creating it
 * Manages the list of Kingdoms
 */
public class MapManager extends Manager {	
	
	public MapManager(Model myModel) {
		super(myModel);
		// TODO Auto-generated constructor stub
	}

	Map currentMap;
	
	public void createMap1() {
		
		currentMap = new Map();
		
		// Get a kingdom from the kingdomManager
		Kingdom firstKingdom = getMyModel().kingdomManager.createNewKingdom();
		
		LocationNode kingdomNode;
		LocationNode awayNode;

		// create two nodes and join them with a track
		kingdomNode = currentMap.newLocationNode();
		awayNode = currentMap.newLocationNode();
		currentMap.newTrack(kingdomNode, awayNode);
		
		firstKingdom.setMyLocation(kingdomNode);
		
	}
	
	public LocationNode getKingdomLocation(int kingdomId) {
		if (getMyModel().kingdomManager.getKingdom(kingdomId) != null) {
			return getMyModel().kingdomManager.getKingdom(kingdomId).getMyLocation();
		}
		return null;
	}
	
	public ArrayList<LocationNode> getLocations() {
		return currentMap.getLocationNodes();
	}
	
	public LocationNode getLocation(int nodeId) {
		return currentMap.getLocationNode(nodeId);
	}

	public Map getMap() {
		return currentMap;
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeCommand(ChatCommand cmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementTimers() {
		// TODO Auto-generated method stub
		
	}
	
}
