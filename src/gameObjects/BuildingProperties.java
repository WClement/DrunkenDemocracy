package gameObjects;

public enum BuildingProperties {

	TOWER	("tower", "Defensive", 200, 1200, 200, 0, 20, GenericBuilding.class),
	GOLDMINE	("goldmine", "Resource", 3000, 400, 1000, 10, 0);
	
	public final String name;
	public final int maxHealth;
	public final int maxBuildingProgress;
	
	public final int goldCost;
	//resource costs?
	
	private Class<?> myClass;

	public String type;
	public final int baseIncome;
	//private final Resource resourceType;
	public int baseDamage;
	
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
		this.setMyClass(GenericBuilding.class);
	}
	
	BuildingProperties(String name, String type,
			int maxHealth, int maxBuildingProgress,
			int goldCost, int baseIncome, int baseDamage, Class<?> myClass) {
this.name = name;
this.type = type;
this.maxHealth = maxHealth;
this.maxBuildingProgress = maxBuildingProgress;
this.goldCost = goldCost;
this.baseIncome = baseIncome;
this.baseDamage = baseDamage;
this.setMyClass(myClass);
}

	public Class<?> getMyClass() {
		return myClass;
	}

	public void setMyClass(Class<?> myClass) {
		this.myClass = myClass;
	}

}
