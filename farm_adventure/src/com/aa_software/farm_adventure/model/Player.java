package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;

public class Player {

	public static final int STARTING_BANKROLL = 100;

	private static Player Instance;

	public static Player getInstance() {
		if (Instance == null) {
			Instance = new Player();
		}
		return Instance;
	}

	private int bankroll;
	private Preferences preferences;

	private Player() {
		this.bankroll = STARTING_BANKROLL;
		// this.campaign = new TutorialCampaign();
		this.preferences = new Preferences();
	}

	public Boolean buyItem(AbstractItem item) {
		int itemCost;
		if (item instanceof AbstractWorker) {
			itemCost = ((AbstractWorker) item).getWage();
		} else {
			itemCost = item.getCost();
		}
		if (this.bankroll < itemCost) {
			// TODO: Let the player know they do not have the funds.
			return false;
		} else {
			this.bankroll -= itemCost;
			return true;
		}
	}

	public int getBankroll() {
		return bankroll;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void sellItem(AbstractItem item) {
		int itemValue = item.getValue();
		this.bankroll += itemValue;
	}

	public void setBankroll(int bankroll) {
		this.bankroll = bankroll;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
