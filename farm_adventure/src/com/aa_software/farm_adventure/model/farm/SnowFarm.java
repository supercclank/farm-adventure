package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class SnowFarm extends AbstractFarm {

	public SnowFarm() {
		super();
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		for (int i = 0; i < seasons.length; i++) {
			if (i % 2 == 0) {
				seasons[i] = new Season(SeasonType.FALL);
			} else {
				seasons[i] = new Season(SeasonType.WINTER);
			}
		}
		field.createSnowField();
		seasons[currentSeason].update(field);
		setupSeasonTimer();
	}

}
