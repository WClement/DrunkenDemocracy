package model;

public abstract class Building {

	private int maxProgress;
	private int currentProgress;
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
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public int getProgress() {
		return currentProgress;
	}
	
	public int getMaxProgress() {
		return maxProgress;
	}
	
	public double getProgressPercentage() {
		return (double)currentProgress/(double)maxProgress;
	}
	
	
}
