package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;

public class Biome {

	public enum Type {
		TROPICAL, BOREAL, GRASSLAND, TEMPERATE
	};

	/**
	 * Each biome has a set of seasons.
	 */
	private static final Season.Type[] TEMPERATE_SEASONS = {
			Season.Type.SUMMER, Season.Type.SUMMER, Season.Type.SUMMER,
			Season.Type.SUMMER };
	private static final Season.Type[] TROPICAL_SEASONS = { Season.Type.SUMMER,
			Season.Type.SPRING, Season.Type.SUMMER, Season.Type.SPRING };
	private static final Season.Type[] GRASSLAND_SEASONS = {
			Season.Type.SPRING, Season.Type.SPRING, Season.Type.SPRING,
			Season.Type.SPRING };
	private static final Season.Type[] BOREAL_SEASONS = { Season.Type.FALL,
			Season.Type.WINTER, Season.Type.FALL, Season.Type.WINTER };

	/**
	 * Each biome has a water modifier which determines how much water is placed on the farm.
	 */
	private static final float TEMPERATE_WATER_MOD = .02f,
			TROPICAL_WATER_MOD = .3f, GRASSLAND_WATER_MOD = .1f,
			BOREAL_WATER_MOD = .1f;

	public static Season.Type[] getSeasons(Type type) {
		switch (type) {
		case TROPICAL:
			return TROPICAL_SEASONS;
		case BOREAL:
			return BOREAL_SEASONS;
		case GRASSLAND:
			return GRASSLAND_SEASONS;
		case TEMPERATE:
			return TEMPERATE_SEASONS;
		default:
			return null;
		}
	}

	public static float getWaterMod(Type type) {
		switch (type) {
		case TROPICAL:
			return TROPICAL_WATER_MOD;
		case BOREAL:
			return BOREAL_WATER_MOD;
		case GRASSLAND:
			return GRASSLAND_WATER_MOD;
		case TEMPERATE:
			return TEMPERATE_WATER_MOD;
		default:
			return 0;
		}
	}

}
