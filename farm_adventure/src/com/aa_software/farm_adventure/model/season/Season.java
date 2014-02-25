package com.aa_software.farm_adventure.model.season;

import java.util.Random;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;

public class Season {

	/**
	 * Each season affects the growth rate of plants.
	 */
	public static final float SPRING_GROWTH_RATE_MOD = .5f;
	public static final float SUMMER_GROWTH_RATE_MOD = 1;
	public static final float FALL_GROWTH_RATE_MOD = 1;
	public static final float WINTER_GROWTH_RATE_MOD = 2;

	/**
	 * Summer and Fall apply a random chance for a plot to lose irrigation or
	 * become covered in leaves (resetting the plot, aside from the irrigation).
	 * These values are the percent chances of those events.
	 */
	public static final float SUMMER_WATER_LEVEL_MOD = .2f;
	public static final float FALL_LEAF_COVER_MOD = .2f;

	public static final int CYCLE_TIME_MILLIS = 60000;

	private float cycleTime;
	private SeasonType seasonType;
	private float growthRateMod = 1;

	public Season(SeasonType seasonType) {
		this.cycleTime = CYCLE_TIME_MILLIS;
		this.seasonType = seasonType;
	}

	public Season(SeasonType seasonType, float cycleTime) {
		this.cycleTime = cycleTime;
		this.seasonType = seasonType;
	}

	public float getCycleTime() {
		return cycleTime;
	}

	public float getGrowthRateMod() {
		return growthRateMod;
	}

	public SeasonType getSeasonType() {
		return seasonType;
	}

	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}

	public void setGrowthRateMod(float growthRateMod) {
		this.growthRateMod = growthRateMod;
	}

	public void setSeasonType(SeasonType seasonType) {
		this.seasonType = seasonType;
	}

	/**
	 * Updates the seasonal growth rate modifier depending on the season type.
	 * In addition, this method will apply seasonal effects, if they exist, to
	 * the given field.
	 * 
	 * @param field
	 *            the field to be updated by seasonal effects.
	 */
	public void update(Field field) {
		/* Reset the field, incase it has previously been affected */
		for (int x = 0; x < Field.COLUMNS; x++) {
			for (int y = 0; y < Field.ROWS; y++) {
				field.getPlot(x, y).setUsable(true);
				if (field.getPlot(x, y).getPlotType().equals(PlotType.LEAVES)) {
					field.getPlot(x, y).setPlotType(PlotType.GRASS);
				}
			}
		}

		if (seasonType == SeasonType.SPRING) {
			growthRateMod = SPRING_GROWTH_RATE_MOD;
		} else if (seasonType == SeasonType.SUMMER) {
			growthRateMod = SUMMER_GROWTH_RATE_MOD;
			Random random = new Random();
			int roll;
			for (int x = 0; x < Field.COLUMNS; x++) {
				for (int y = 0; y < Field.ROWS; y++) {
					roll = random.nextInt(10);
					if (roll < 10 * SUMMER_WATER_LEVEL_MOD) {
						if (field.getPlot(x, y).getIrrigation() != null) {
							Plot plot = field.getPlot(x, y);
							plot.setIrrigation(null);
							plot.setCrop(null);
							plot.setPlotType(PlotType.PLOWEDUNWATERED);
						}
					}
				}
			}
		} else if (seasonType == SeasonType.FALL) {
			growthRateMod = FALL_GROWTH_RATE_MOD;
			Random random = new Random();
			int roll;
			for (int x = 0; x < Field.COLUMNS; x++) {
				for (int y = 0; y < Field.ROWS; y++) {
					roll = random.nextInt(10);
					if (roll < 10 * FALL_LEAF_COVER_MOD) {
						Plot plot = field.getPlot(x, y);
						plot.setPlotType(PlotType.LEAVES);
						plot.setCrop(null);
						plot.setUsable(false);
					}
				}
			}
		} else if (seasonType == SeasonType.WINTER) {
			growthRateMod = WINTER_GROWTH_RATE_MOD;
		}
	}
}
