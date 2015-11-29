package gameLogic;


public class Team {
	
	private String longName, shortName;
	private boolean attacking = false, underAttack = false, alive = true;
	
	//sets the size of the array needed to store all of the buildings
	private final int totalBuildings = 10;
	
	private Building[] buildingList = new Building[totalBuildings];
	
	public Team(String longName, String shortName) {
		// TODO Auto-generated constructor stub
		this.longName = longName;
		this.shortName = shortName;
	}

	public boolean attack(Team toAttack){
		//if the enemy is under attack, you can't attack them.
		if (toAttack.underAttack){
			return false;
		}
		//while there are units attacking
		while(true){
			int buildingToAttack = totalBuildings;
			
			// TODO get attack strength from units
			int attackStrength = 90;
			// finds the farthest out building to attack, while there are units left to attack
			while ( buildingToAttack >= 0 && toAttack.buildingList[buildingToAttack] != null && attackStrength > 0){
				
			}
			
			// if there aren't any buildings left to attack, the enemy must be dead
			break;
		}
		return true;
	}
	
	public boolean retreat(){
		return false;
	}
	
	public boolean stillAlive(){
		return alive;
	}
	
	public boolean build(Building building){
		return false;
	}
	
	public boolean buildUnit(Unit toBuild){
		return false;
	}
	
	public boolean advanceTech(){
		return false;
	}
	
	public boolean repairBase(){
		return false;
	}

	public String getLongName() {
		// TODO Auto-generated method stub
		return longName;
	}

	public String getShortName() {
		// TODO Auto-generated method stub
		return shortName;
	}
}
