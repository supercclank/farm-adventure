package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.campaign.AbstractCampaign;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.badlogic.gdx.Gdx;

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
	private AbstractCampaign campaign;
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
			System.out.println("You don't have enough funds");
			return false;
		} else {
			System.out.println("Buy: " + item.toString());
			this.bankroll -= itemCost;
			return true;
		}
	}

	public int getBankroll() {
		return bankroll;
	}

	public AbstractCampaign getCampaign() {
		return campaign;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void sellItem(AbstractItem item) {
		int itemValue = item.getValue();
		System.out.println("Sell: " + item.toString());
		this.bankroll += itemValue;
	}

	public void setBankroll(int bankroll) {
		this.bankroll = bankroll;
	}

	public void setCampaign(AbstractCampaign campaign) {
		this.campaign = campaign;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	
	public void saveData() {
		com.badlogic.gdx.Preferences prefs = Gdx.app.getPreferences("FarmAdventure.Player.Preferences");
		Sounds s = Sounds.getInstance();
		
		prefs.putInteger("BANKROLL", bankroll);
		prefs.putFloat("MASTERVOLUME", s.getMasterVolume());
		prefs.putFloat("GAMEVOLUME", s.getMusicVolume());
		prefs.putFloat("SOUNDVOLUME", s.getSoundVolume());
		
		// bulk update your preferences
		prefs.flush();
	}
	
	public void loadData() {
		com.badlogic.gdx.Preferences prefs = Gdx.app.getPreferences("FarmAdventure.Player.Preferences");
		Sounds s = Sounds.getInstance();
		
		if(prefs.contains("BANKROLL")) {
			prefs.getInteger("BANKROLL");
			s.setMasterVolume(prefs.getFloat("MASTERVOLUME"));
			s.setMusicVolume(prefs.getFloat("GAMEVOLUME"));
			s.setSoundVolume(prefs.getFloat("SOUNDVOLUME"));
		}
		else {
			s.setMasterVolume(1.0f);
			s.setMusicVolume(1.0f);
			s.setSoundVolume(1.0f);
		}
	}
}
