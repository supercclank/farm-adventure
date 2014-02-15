package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class SnowFarm extends AbstractFarm {

	public SnowFarm() {
		super();
		int everyOther = 1;
		for (int i = 0; i < seasons.length; i++) {
			if (everyOther % 2 == 1) {
				seasons[i] = new Season(SeasonType.FALL);
			} else {
				seasons[i] = new Season(SeasonType.WINTER);
			}
			everyOther++;
		}
	}

}
