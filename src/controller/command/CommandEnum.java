package controller.command;

/*
 * CommandEnum:
 * 
 * PURPOSE: Holds all possible prefixes and their associated
 * 			ChatCommand classes.
 * 
 * USE: If you need a new type of command just give it a prefix
 * and it's associated ChatCommand class.
 * 
 */

public enum CommandEnum {

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~Defined Enums~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	BUILD ("B:", BuildCommand.class),
	VOTE ("V:", VoteCommand.class),
	ATTACK ("A:", AttackCommand.class),
	KREYGASM ("Kreygasm", KreygasmCommand.class),
	KAPPA ("Kappa", KappaCommand.class),
	UNIT ("U:", UnitCommand.class);
	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~Enum methods~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	private final String prefix;
	private final Class<?> myClass;
	
	CommandEnum(String prefix, Class<?> myClass) {
		this.prefix = prefix;
		this.myClass = myClass;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public Class<?> getMyClass() {
		return myClass;
	}
}
