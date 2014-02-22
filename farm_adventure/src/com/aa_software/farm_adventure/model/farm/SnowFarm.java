package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a snow farm.
 * @author AASoftware
 *
 */
public class SnowFarm extends AbstractFarm {

	public static final SeasonType[] seasonTypes = { SeasonType.FALL, SeasonType.WINTER, SeasonType.FALL, SeasonType.WINTER };

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public SnowFarm() {
		super();
		seasons = new Season[seasonTypes.length];
		for (int i = 0; i < seasons.length; i++) {
				seasons[i] = new Season(seasonTypes[i]);
		}
		field.createSnowField();
		seasons[currentSeason].update(field);
	}

}
