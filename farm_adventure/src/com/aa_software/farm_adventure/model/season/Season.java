package com.aa_software.farm_adventure.model.season;

public class Season {

	public static final int CYCLE_TIME_MINUTES = 2;
	private int cycleTime;
	private SeasonType seasonType;

	public Season(SeasonType seasonType) {
		this.cycleTime = CYCLE_TIME_MINUTES;
		this.seasonType = seasonType;
	}

	public Season(SeasonType seasonType, int cycleTime) {
		this.cycleTime = cycleTime;
		this.seasonType = seasonType;
	}

	public int getCycleTime() {
		return cycleTime;
	}

	public SeasonType getSeasonType() {
		return seasonType;
	}

	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}

	public void setSeasonType(SeasonType seasonType) {
		this.seasonType = seasonType;
	}

}
