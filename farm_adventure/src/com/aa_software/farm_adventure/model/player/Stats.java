package com.aa_software.farm_adventure.model.player;

/**
 * 
 * Holds the player's current score.
 * 
 * @author Bebop
 * 
 */
public class Stats {
	// TODO: add more stats, like the total number of things planted or total
	// number of farms.
	int score = 0;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
