package gameObjects;

public enum UnitProperties {

	//PREFAB (unitName, unitType, baseHealth, baseDmg, attkSpd, baseMovSpeed);
	GUNNER ("gunner", "Offensive", 100, 10, 30, 60),
	BAZOOKA ("bazooka", "Offensive", 120, 50, 120, 20);
	
	public String name;
	public String type;
	public int baseHealth;
	public int baseDmg;
	public int attkSpd;
	public int baseMovSpd;
	
	public Class<?> myUnitClass;
	
	UnitProperties(String name, String type, 
					int baseHealth, int baseDmg, 
					int attkSpd, int baseMovSpd) {
		
		this.name = name;
		this.type = type;
		this.baseHealth = baseHealth;
		this.baseDmg = baseDmg;
		this.attkSpd = attkSpd;
		this.baseMovSpd = baseMovSpd;
		this.myUnitClass = Unit.class;
	}
	
}
