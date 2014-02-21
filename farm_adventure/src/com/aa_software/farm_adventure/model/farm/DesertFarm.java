package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class DesertFarm extends AbstractFarm {

	public DesertFarm() {
		super();
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SUMMER);
		}
		field.createDesertField();
		seasons[currentSeason].update(field);
		setupSeasonTimer();
	}

}
