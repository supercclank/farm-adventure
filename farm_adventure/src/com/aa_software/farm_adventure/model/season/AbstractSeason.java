package com.aa_software.farm_adventure.model.season;

public class AbstractSeason {
	/* Measured in seconds. with 4 seasons at 120, it's 8 minutes */
	protected static final int DEFAULT_CYCLE_TIME = 120;
	protected int cycleTime;
	
	public AbstractSeason() {
		this.cycleTime = DEFAULT_CYCLE_TIME;
	}
	
	public AbstractSeason(int cycleTime) {
		this.cycleTime = cycleTime;
	}
	
	public int getCycleTime() {
		return cycleTime;
	}
	
	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}
	
}
