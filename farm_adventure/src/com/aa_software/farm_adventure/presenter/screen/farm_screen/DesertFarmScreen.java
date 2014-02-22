package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.DesertFarm;
import com.aa_software.farm_adventure.presenter.FarmAdventure;

public class DesertFarmScreen extends AbstractFarmScreen {

	/**
	 * Constructs a farm screen using the specifications of DesertFarm.
	 */
	public DesertFarmScreen(FarmAdventure game) {
		super(game);
		farm = new DesertFarm();
		// TODO Auto-generated constructor stub
	}

}
