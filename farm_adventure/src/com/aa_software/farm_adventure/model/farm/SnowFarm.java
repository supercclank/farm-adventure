package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a snow farm.
 * 
 * @author AASoftware
 * 
 */
public class SnowFarm extends AbstractFarm {

	public static final FarmType type = FarmType.SNOW;
	public static final SeasonType[] DEFAULT_SEASONS = { SeasonType.FALL,
			SeasonType.WINTER, SeasonType.FALL, SeasonType.WINTER };
	private final float WATER_PLOT_MOD = .10f;

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public SnowFarm() {
		super();
		seasons = new Season[DEFAULT_SEASONS.length];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(DEFAULT_SEASONS[i]);
		}
		field = new Field(WATER_PLOT_MOD);
		seasons[currentSeason].update(field);
	}

}
