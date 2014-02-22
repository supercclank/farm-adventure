package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.RainforestFarm;
import com.aa_software.farm_adventure.presenter.FarmAdventure;

public class RainforestFarmScreen extends AbstractFarmScreen {

	/**
	 * Constructs a farm screen using the specifications of RainforestFarm.
	 */
	public RainforestFarmScreen(FarmAdventure game) {
		super(game);
		farm = new RainforestFarm();
		// TODO Auto-generated constructor stub
	}

}
