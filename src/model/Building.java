package model;

public abstract class Building {

	private int maxProgress;
	private int currentProgress;
	private int progressFlag = 3;
	
	
	private String name;
	
	public Building(String name, int maxProgress) {
		this.name = name;
		if (maxProgress > 3600) {
			this.maxProgress = 3600;
		}
		else {
			this.maxProgress = maxProgress;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addProgress() {
		if (currentProgress < maxProgress) {
			currentProgress++;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public int getProgress() {
		return currentProgress;
	}
	
	public int getMaxProgress() {
		return maxProgress;
	}
	
	public double getProgressPercentage() {
		return Math.round(((double)currentProgress/(double)maxProgress)*100);
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
	
	
}
