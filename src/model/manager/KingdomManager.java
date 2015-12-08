package model.manager;

import gameObjects.Building;
import gameObjects.Kingdom;

import java.util.ArrayList;

import model.Model;
import controller.command.ChatCommand;

public class KingdomManager extends Manager {

	public final int INCOME_TIME = 600;
	private int incomeTimer = 0;
	
	ArrayList<Kingdom> kingdomList;
	int kingdomCount;
	
	public KingdomManager(Model myModel) {
		super(myModel);
		kingdomList = new ArrayList<Kingdom>();
		kingdomCount = 0;
	}
	
	
	public Kingdom createNewKingdom() {
		Kingdom newKingdom = new Kingdom();
		kingdomList.add(newKingdom);
		kingdomCount++;
		return newKingdom;
	}
	
	public void addGold(int kingdomId, int gold) {
		kingdomList.get(kingdomId).addGold(gold);
	}
	
	public Kingdom getKingdom(int kingdomId) {
		return kingdomList.get(kingdomId);
	}


	@Override
	public void manage() {
		// TODO Auto-generated method stub
		
		if (incomeTimer >= INCOME_TIME) {
			incomeTimer = 0;
			for (Kingdom k : kingdomList) {
				k.doIncome();
			}
		}
		incrementTimers();
	}


	@Override
	public void executeCommand(ChatCommand cmd) {
		// TODO Auto-generated method stub
		
	}

	// remove gold and add building to kingdom
	public void addBuilding(Building newBuilding, int kingdomIndex) {
		// TODO Auto-generated method stub
		if (!canAfford(newBuilding.goldCost, kingdomIndex)) {
			return;
		}
		kingdomList.get(kingdomIndex).removeGold(newBuilding.goldCost);
		kingdomList.get(kingdomIndex).addBuilding(newBuilding);
	}
	
	public void removeBuilding(int kingdomIndex, Building toRemove) {
		kingdomList.get(kingdomIndex).removeBuilding(toRemove);
	}

	public int getKingdomGold(int kingdomId) {
		return kingdomList.get(kingdomId).getGold();
	}
	
	public boolean canAfford(int price, int kingdomId) {
		if (kingdomList.get(kingdomId).getGold() >= price) {
			return true;
		}
		return false;
	}
	
	@Override
	public void incrementTimers() {
		// TODO Auto-generated method stub
		incomeTimer++;
	}
}
