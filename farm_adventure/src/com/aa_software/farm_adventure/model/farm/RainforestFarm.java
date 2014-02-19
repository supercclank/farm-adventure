package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class RainforestFarm extends AbstractFarm {
	public RainforestFarm() {
		super();
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		for (int i = 0; i < seasons.length; i++) {
			if (i % 2 == 0) {
				seasons[i] = new Season(SeasonType.SUMMER);
			} else {
				seasons[i] = new Season(SeasonType.SPRING);
			}
		}
		seasons[currentSeason].update(field);
		setupSeasonTimer();
	}
}
