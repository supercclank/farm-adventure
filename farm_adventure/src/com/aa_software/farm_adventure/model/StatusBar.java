package com.aa_software.farm_adventure.model;

public class StatusBar {
	public static final int COLUMNS = 5;
	public static final int ROWS = 1;
	private int score;
	private int time;

	/*
	 * not sure if I like this class. May want to move its functions elsewhere
	 * and have the View update from the "main game" class, which will hold
	 * these values.
	 */
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
