package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.campaign.AbstractCampaign;
import com.aa_software.farm_adventure.model.campaign.TutorialCampaign;

public class Player {
	
	public static int STARTING_BANKROLL = 100;
	
	private static Player Instance;
	private int bankroll;
	private AbstractCampaign campaign;
	private Preferences preferences;
	private Inventory inventory;

	private Player() {
		this.bankroll = STARTING_BANKROLL;
		this.inventory = new Inventory();
		this.campaign = new TutorialCampaign();
		this.preferences = new Preferences();
	}

	public static Player getInstance() {
		if (Instance == null) {
			Instance = new Player();
		}
		return Instance;
	}

	public AbstractCampaign getCampaign() {
		return campaign;
	}

	public void setCampaign(AbstractCampaign campaign) {
		this.campaign = campaign;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public int getBankroll() {
		return bankroll;
	}

	public void setBankroll(int bankroll) {
		this.bankroll = bankroll;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
