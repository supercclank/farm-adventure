package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class TutorialFarm extends AbstractFarm {
	
	public TutorialFarm() {
		super();
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SPRING);
		}
		/*
		startingCropCount.put(new BeetCrop(), 5);
		startingToolCount.put(new BackhoeTool(), 1);
		startingSpellCount.put(new MolesSpell(), 1);
		startingWorkerCount.put(new DefaultWorker(), 1);
		*/
	}
}
