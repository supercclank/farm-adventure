package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.campaign.AbstractCampaign;
import com.aa_software.farm_adventure.model.campaign.TutorialCampaign;

public class Player {
	private static Player instance;
	private int bankroll;
	private AbstractCampaign campaign;
	private Preferences preferences;
	
	protected Player() {}
	
	public static Player getInstance() {
		if(instance == null) {
			instance = new Player();
			instance.setCampaign(new TutorialCampaign());
			instance.setPreferences(new Preferences());
		}
		return instance;
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
}
