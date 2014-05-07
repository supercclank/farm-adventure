package com.aa_software.farm_adventure.model.season;

import java.util.EnumSet;
import java.util.Random;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;

public class Season {

	public enum Type {
		FALL, WINTER, SPRING, SUMMER
	}

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
	private Type type;
	private float growthRateMod;

	public Season(Type type) {
		this.cycleTime = CYCLE_TIME_MILLIS;
		this.type = type;
		this.growthRateMod = 1;
	}
	public Season() {
		this.cycleTime = CYCLE_TIME_MILLIS;
		this.type = type.SPRING;
		this.growthRateMod = 1;
	}
	
	public Season(Type type, float cycleTime) {
		this.cycleTime = cycleTime;
		this.type = type;
	}

	public float getCycleTime() {
		return cycleTime;
	}

	public float getGrowthRateMod() {
		return growthRateMod;
	}

	public Type getType() {
		return type;
	}

	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}

	public void setGrowthRateMod(float growthRateMod) {
		this.growthRateMod = growthRateMod;
	}

	public void setType(Type type) {
		this.type = type;
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
				if (field.getPlot(x, y).getPlotType() != Plot.Type.WATER)
					field.getPlot(x, y).setUsable(true);
				if (field.getPlot(x, y).getPlotType() == Plot.Type.LEAVES) {
					field.getPlot(x, y).setPlotType(Plot.Type.GRASS);
				}
			}
		}

		switch (type) {
		case SPRING:
			growthRateMod = SPRING_GROWTH_RATE_MOD;
			break;
		case SUMMER: {
			growthRateMod = SUMMER_GROWTH_RATE_MOD;
			Random random = new Random();
			int roll;
			for (int x = 0; x < Field.COLUMNS; x++) {
				for (int y = 0; y < Field.ROWS; y++) {
					roll = random.nextInt(10);
					if (roll < 10 * SUMMER_WATER_LEVEL_MOD) {
						if (!field.getPlot(x, y).getIrrigation().isEmpty()) {
							Plot plot = field.getPlot(x, y);
							plot.setIrrigation(EnumSet.noneOf(Irrigation.class));
							plot.setCrop(null);
						}
					}
				}
			}
			break;
		}
		case FALL: {
			growthRateMod = FALL_GROWTH_RATE_MOD;
			Random random = new Random();
			int roll;
			for (int x = 0; x < Field.COLUMNS; x++) {
				for (int y = 0; y < Field.ROWS; y++) {
					roll = random.nextInt(10);
					if (roll < 10 * FALL_LEAF_COVER_MOD) {
						Plot plot = field.getPlot(x, y);
						plot.setPlotType(Plot.Type.LEAVES);
						plot.setCrop(null);
						plot.setUsable(false);
					}
				}
			}
			break;
		}
		case WINTER:
			growthRateMod = WINTER_GROWTH_RATE_MOD;
			break;
		default:
		}
	}
}
