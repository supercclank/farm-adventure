package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.SnowFarm;
import com.aa_software.farm_adventure.presenter.FarmAdventure;

public class SnowFarmScreen extends AbstractFarmScreen {

	/**
	 * Constructs a farm screen using the specifications of SnowFarm.
	 */
	public SnowFarmScreen(FarmAdventure game) {
		super(game);
		farm = new SnowFarm();
		// TODO Auto-generated constructor stub
	}

}
