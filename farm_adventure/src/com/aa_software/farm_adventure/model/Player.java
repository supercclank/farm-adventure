package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.campaign.AbstractCampaign;
import com.aa_software.farm_adventure.model.item.AbstractItem;

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

	public int getBankroll() {
		return bankroll;
	}

	public AbstractCampaign getCampaign() {
		return campaign;
	}

	public Preferences getPreferences() {
		return preferences;
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
	
	public Boolean buyItem(AbstractItem item){
		
		int itemCost = item.getCost();
    	if(this.bankroll<itemCost){
    		System.out.println("You don't have enough funds");
            return false;
    	} else{
            System.out.println("Buy: "+item.toString());
            this.bankroll-=itemCost;
    		return true;
    	}
	}
	
	public void sellItem(AbstractItem item){
		int itemValue = item.getValue();
	    System.out.println("Sell: "+item.toString());
	    this.bankroll+=itemValue;
	}
}
