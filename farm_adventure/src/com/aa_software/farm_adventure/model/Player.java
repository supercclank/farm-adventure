package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.campaign.AbstractCampaign;

public class Player {
	private Score score;
	private AbstractCampaign campaign;
	private Preferences preferences;
	
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
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
}
