package gameObjects;

public enum BuildingProperties {

	TOWER	("Tower", "Defensive", 200, 200, 200, 0, 20),
	GOLDMINE	("Gold Mine", "Resource", 100, 400, 1000, 10, 0);
	
	private final String name;
	private final int maxHealth;
	private final int maxBuildingProgress;
	
	private final int goldCost;
	//resource costs?
	
	
	private String type;
	private final int baseIncome;
	//private final Resource resourceType;
	private int baseDamage;
	
	BuildingProperties(String name, String type,
						int maxHealth, int maxBuildingProgress,
						int goldCost, int baseIncome, int baseDamage) {
		this.name = name;
		this.type = type;
		this.maxHealth = maxHealth;
		this.maxBuildingProgress = maxBuildingProgress;
		this.goldCost = goldCost;
		this.baseIncome = baseIncome;
		this.baseDamage = baseDamage;
	}

}
