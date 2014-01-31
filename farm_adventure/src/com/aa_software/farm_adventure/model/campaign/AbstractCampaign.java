package com.aa_software.farm_adventure.model.campaign;

import com.aa_software.farm_adventure.model.farm.AbstractFarm;

public abstract class AbstractCampaign {
	public static final int DEFAULT_NUMBER_OF_FARMS = 4;
	protected AbstractFarm[] farms;
	protected int progress;
	
	public AbstractCampaign() {
		this.farms = new AbstractFarm[DEFAULT_NUMBER_OF_FARMS];
		this.progress = 0;
	}

	public int getProgress() {
		return progress;
	}

	public void completeFarm() {
		/* comp0, prog1; comp1,prog2; comp2,prog3; comp3,prog */
		if(farms != null && progress+1 < farms.length) {
			this.progress++;
		}
	}

	public AbstractFarm[] getFarms() {
		return farms;
	}
}
