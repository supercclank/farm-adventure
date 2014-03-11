package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.presenter.FarmAdventure;

public class TutorialFarmScreen extends AbstractFarmScreen {

	/**
	 * Constructs a farm screen using the specifications of TutorialFarm.
	 */
	public TutorialFarmScreen() {
		super();
		farm = new TutorialFarm();
	}

}
