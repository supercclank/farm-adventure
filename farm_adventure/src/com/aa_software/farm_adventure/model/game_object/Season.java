package com.aa_software.farm_adventure.model.game_object;

public class Season {
	/* Measured in seconds. with 4 seasons at 120, it's 8 minutes */
	public static final int DEFAULT_CYCLE_TIME = 120;
	private int cycleTime;
	private SeasonType seasonType;
	
	public Season() {
		this.cycleTime = DEFAULT_CYCLE_TIME;
	}
	
	public Season(SeasonType seasonType) {
		this.seasonType = seasonType;
		this.cycleTime = DEFAULT_CYCLE_TIME;
	}
	
	public Season(int cycleTime) {
		this.cycleTime = cycleTime;
	}
	
	public Season(int cycleTime, SeasonType seasonType) {
		this.cycleTime = cycleTime;
		this.seasonType = seasonType;
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
