package com.aa_software.farm_adventure.model.player;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * Represents the player, with his preferences and stats. This is a singleton class.
 * 
 * @author Bebop
 *
 */
public class Player {

	public static final int STARTING_BANKROLL = 100;

	private static Player Instance;

	public static Player getInstance() {
		if (Instance == null) {
			Instance = new Player();
		}
		return Instance;
	}
	private Stats stats;

	private Preferences preferences;

	private Player() {
		stats = new Stats();
		this.stats.setScore(STARTING_BANKROLL);
		// this.campaign = new TutorialCampaign();
		this.preferences = new Preferences();
	}

	public Boolean buyItem(AbstractItem item) {
		int itemCost = item.getCost();
		if (this.stats.getScore() < itemCost) {
			// TODO: Let the player know they do not have the funds.
			return false;
		} else {
			this.stats.setScore(this.stats.getScore() - itemCost);
			return true;
		}
	}

	public int getBankroll() {
		return this.stats.getScore();
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void sellItem(AbstractItem item) {
		this.stats.setScore(this.stats.getScore() + item.getValue());
	}

	public void setBankroll(int bankroll) {
		this.stats.setScore(bankroll);
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
