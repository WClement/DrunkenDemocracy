package model.manager;

import java.util.ArrayList;

import controller.command.ChatCommand;
import model.Model;
import gameObjects.Kingdom;
import gameObjects.LocationNode;
import gameObjects.Map;

public class MapManager extends Manager {

	public MapManager(Model myModel) {
		super(myModel);
		// TODO Auto-generated constructor stub
	}

	Map currentMap;
	ArrayList<Kingdom> kingdomList;
	
	public void createMap1() {
		
		currentMap = new Map();
		kingdomList = new ArrayList<Kingdom>();
		
		Kingdom firstKingdom = new Kingdom();
		
		LocationNode kingdomNode;
		LocationNode awayNode;

		kingdomNode = currentMap.newLocationNode();
		awayNode = currentMap.newLocationNode();
		
		currentMap.newTrack(kingdomNode, awayNode);
		
		firstKingdom.setMyLocation(kingdomNode);
		
		kingdomList.add(firstKingdom);
	}
	
	public LocationNode getKingdomLocation(int kingdomId) {
		if (kingdomList.get(kingdomId) != null) {
			return kingdomList.get(kingdomId).getMyLocation();
		}
		return null;
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeCommand(ChatCommand cmd) {
		// TODO Auto-generated method stub
		
	}
	
}
