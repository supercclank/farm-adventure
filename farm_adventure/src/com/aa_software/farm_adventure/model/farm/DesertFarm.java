package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a desert farm.
 * @author AASoftware
 *
 */
public class DesertFarm extends AbstractFarm {

	public static final FarmType type = FarmType.DESERT;
	public static final SeasonType[] DEFAULT_SEASONS = { SeasonType.SUMMER,
		SeasonType.SUMMER, SeasonType.SUMMER, SeasonType.SUMMER };
	private final float WATER_PLOT_MOD = .02f;
	
	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public DesertFarm() {
		super();
		seasons = new Season[DEFAULT_SEASONS.length];
		for (int i = 0; i < seasons.length; i++) {
				seasons[i] = new Season(DEFAULT_SEASONS[i]);
		}
		field = new Field(WATER_PLOT_MOD);
		seasons[currentSeason].update(field);
	}

}
