package gameObjects;

public class BuildingSlot {

	private Building myBuilding;
	private boolean hasBuilding;
	
	public Building getBuilding() {
		if (hasBuilding) {
			return myBuilding;
		}
		return null;
	}
	
	public boolean addBuilding(Building myBuilding) {
		if (!hasBuilding()) {
			this.myBuilding = myBuilding;
			return true;
		}
		return false;
	}
	
	public boolean hasBuilding() {
		return hasBuilding;
	}
}
