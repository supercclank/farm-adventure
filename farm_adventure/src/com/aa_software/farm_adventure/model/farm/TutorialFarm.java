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
public class TutorialFarm extends AbstractFarm {

	public static final FarmType type = FarmType.TUTORIAL;
	public static final SeasonType[] DEFAULT_SEASONS = { SeasonType.SPRING,
			SeasonType.SPRING, SeasonType.SPRING, SeasonType.SPRING };
	private static final float WATER_PLOT_MOD = .10f;

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public TutorialFarm() {
		super();
		seasons = new Season[DEFAULT_SEASONS.length];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(DEFAULT_SEASONS[i]);
		}
		seasons[currentSeason].update(field);

		field = new Field(WATER_PLOT_MOD);
	}
}
