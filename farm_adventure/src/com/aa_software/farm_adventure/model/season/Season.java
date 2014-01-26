package com.aa_software.farm_adventure.model.season;

public class Season {
	/* Measured in seconds. with 4 seasons at 120, it's 8 minutes */
	public static final int DEFAULT_CYCLE_TIME = 120;
	private int cycleTime;
	private SeasonType seasonType;
	
	public Season(SeasonType seasonType) {
		this.cycleTime = DEFAULT_CYCLE_TIME;
		this.seasonType = seasonType;
	}
	
	public Season(int cycleTime) {
		this.cycleTime = cycleTime;
	}
	
	public int getCycleTime() {
		return cycleTime;
	}
	
	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}

	public SeasonType getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(SeasonType seasonType) {
		this.seasonType = seasonType;
	}
	
}
