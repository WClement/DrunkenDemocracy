package gameObjects;

public abstract class Building {

	// public accessible properties
	public String name; // enum name of building
	public String type; // defensive, offensive
	public int maxHealth; // initial max health
	public int maxBuildingProgress; // max building progress
	public int goldCost; // initial cost in gold
	public String team; // team (for colors and damage)
	public int teamId; // team id
	public int baseIncome; // base income!
	public int baseDamage; // base damage 
	
	// private vars for changing values
	private int currentHealth; // current health
	private int currentBuildingProgress; // current building progress
	private int currentIncome;

	
	private int progressFlag = 3; // used for pretty print
	private boolean isDead = false; // set if currentHealth <= 0
	private boolean finishedBuilding = false; // has finished building
	private boolean isBuilding = false; // is currently adding to building progress
	private int location; // location in location node for ordering
	private LocationNode myLoc; // my location node
	private int locationIndex;
	private int income;
	
	public Building(String name, int maxProgress) {
		this.name = name;
		myLoc = null;
	}
	
	public Building(BuildingProperties b) {
		this.name = b.name;
		this.type = b.type;
		this.maxHealth = b.maxHealth;
		this.maxBuildingProgress = b.maxBuildingProgress;
		this.goldCost = b.goldCost;
		this.baseIncome = b.baseIncome;
		this.baseDamage = b.baseDamage;
		
		income = baseIncome;
		currentBuildingProgress = 0;
	}
	
	public void setLocation(LocationNode newLoc) {
		myLoc = newLoc;
	}
	
	public LocationNode getLocation() {
		return myLoc;
	}
	
	public void setLocationIndex(int index) {
		this.locationIndex = index;
	}
	
	public int getLocationIndex() {
		return locationIndex;
	}
	
	public void destroy() {
		isDead = true;
		myLoc.removeBuilding(this);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addProgress() {
		
		if (!isBuilding) { // building is paused
			return true;
		}
		
		if (currentBuildingProgress < maxBuildingProgress) { // currently building
			currentBuildingProgress++;
			return true;
		}
		else {		// finished building
			isBuilding = false;
			finishedBuilding = true;
			return false;
		}
	}
	
	public int getProgress() {
		return currentBuildingProgress;
	}
	
	public int getMaxProgress() {
		return maxBuildingProgress;
	}
	
	public double getProgressPercentage() {
		return Math.round(((double)currentBuildingProgress/(double)maxBuildingProgress)*100);
	}
	
	public String printProgressPretty() {
		if (getProgressPercentage() == 25.0 && progressFlag > 2) {
			progressFlag--;
			return name + " is at 25%";
		}
		if (getProgressPercentage() == 50.0 && progressFlag > 1) {
			progressFlag--;
			return name + " is at 50%";
		}
		if (getProgressPercentage() == 75.0 && progressFlag > 0) {
			progressFlag--;
			return name + " is at 75%";
		}
		else {
			return "";
		}
	}
	
	public boolean repair(){
		return false;
	}
	
	public boolean construct() { // resume construction
		if (isBuilding) {
			return false;
		}
		isBuilding = true;
		return true;
	}
	
	public boolean stopConstructing() { // halt construction
		if (!isBuilding) {
			return false;
		}
		isBuilding = false;
		return true;
	}
	
	public boolean takeDamage(int hit){
		
		if (!finishedBuilding) {
			currentBuildingProgress -= hit;
			if (currentBuildingProgress < 0) {
				destroy();
			}
		}
		
		
		if (hit >= currentHealth) {
			isDead = true;
			destroy();
		}
		return false;
	}
	
	public void setTeamId(int id) {
		this.teamId = id;
	}
	
	public int getTeamId() {
		return teamId;
	}

	public int currentIncome() {
		return income;
	}
}
