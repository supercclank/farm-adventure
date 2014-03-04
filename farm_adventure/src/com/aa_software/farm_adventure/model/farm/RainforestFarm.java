package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a rainforest farm.
 * @author AASoftware
 *
 */
public class RainforestFarm extends AbstractFarm {
	
	public static final FarmType type = FarmType.RAINFOREST;
	public static final SeasonType[] DEFAULT_SEASONS = { SeasonType.SUMMER,
		SeasonType.SPRING, SeasonType.SUMMER, SeasonType.SPRING };
	private final float WATER_PLOT_MOD = .40f;
	
	/**
	 * Constructs a farm with the correct seasons and field.
	 */
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
		field = new Field(WATER_PLOT_MOD);
		seasons[currentSeason].update(field);
		setupSeasonTimer();
	}
}
