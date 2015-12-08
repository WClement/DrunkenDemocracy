package gameObjects;

public class Unit {
	
	public int maxHealth;
	public int attkSpd;
	public int baseDmg;
	public int baseMovSpd;
	public String name;
	public String type;
	public int teamId; // team id
	public int unitId;
	
	private int currentHealth;
	private Building currentBuildingTarget;
	private Unit currentUnitTarget;
	private boolean hasTarget = false;
	private boolean isDead = false;
	
	//location vars
	private boolean isOnTrack = false;
	private boolean isOnNode = false;
	private LocationNode currentNode;
	private Track currentTrack;
	
	public Unit(UnitProperties u) {
		this.name = u.name;
		this.type = u.type;
		this.baseMovSpd = u.baseMovSpd;
		this.maxHealth = u.baseHealth;
		this.baseDmg = u.baseDmg;
		this.attkSpd = u.attkSpd;
	}
	
	public void setUnitId(int id) {
		this.unitId = id;
		this.name = name + " " + id;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public boolean dealDamage(int dmg) {
		if (currentHealth <= 0) {
			return false;
		}
		if (dmg >= currentHealth) {
			currentHealth = 0;
			setDead(true);
			return true;
		}
		currentHealth -= dmg;
		return true;
	}

	public Building getCurrentBuildingTarget() {
		return currentBuildingTarget;
	}

	public void setCurrentBuildingTarget(Building currentBuildingTarget) {
		this.currentBuildingTarget = currentBuildingTarget;
	}

	public Unit getCurrentUnitTarget() {
		return currentUnitTarget;
	}

	public void setCurrentUnitTarget(Unit currentUnitTarget) {
		this.currentUnitTarget = currentUnitTarget;
	}

	public boolean isHasTarget() {
		return hasTarget;
	}

	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isOnTrack() {
		return isOnTrack;
	}

	public void setOnTrack() {
		isOnNode = false;
		this.isOnTrack = true;
	}

	public boolean isOnNode() {
		return isOnNode;
	}

	public void setOnNode() {
		isOnTrack = false;
		this.isOnNode = true;
	}

	public LocationNode getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(LocationNode currentNode) {
		this.currentNode = currentNode;
	}

	public Track getCurrentTrack() {
		return currentTrack;
	}

	public void setCurrentTrack(Track currentTrack) {
		this.currentTrack = currentTrack;
	}
	
}
