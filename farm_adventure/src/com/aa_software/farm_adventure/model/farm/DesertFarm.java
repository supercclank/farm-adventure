package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a desert farm.
 * @author AASoftware
 *
 */
public class DesertFarm extends AbstractFarm {

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
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
