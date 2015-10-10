package controller;

public class CommandFactory {
	
	public ChatCommand createCommand(String raw) {
		if (raw.startsWith("B:")) {
			return new BuildCommand(raw);
		}
		return null;
	}
}
