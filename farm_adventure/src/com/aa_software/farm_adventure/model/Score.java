package com.aa_software.farm_adventure.model;

/*
 * At the end of the farm’s play time, all of the inventory items that can be sold will be, and the 

profits will be added to the farm’s bank. The amount of money that the farm then has in the 

bank will be the player’s numerical score for that farm. That score will then be checked 

against a table that determines how many stars the player earned for that farm, up to five 

stars per farm.
 */
public class Score {
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
