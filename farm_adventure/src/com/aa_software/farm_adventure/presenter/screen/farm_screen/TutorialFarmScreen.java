package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.presenter.FarmAdventure;

public class TutorialFarmScreen extends AbstractFarmScreen {

	public TutorialFarmScreen(FarmAdventure game) {
		super(game);
		farm = new TutorialFarm();
	}

}
