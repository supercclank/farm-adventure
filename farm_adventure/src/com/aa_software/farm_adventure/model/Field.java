package com.aa_software.farm_adventure.model;

import java.util.Random;

import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

/**
 * Creates a field of plots with a field size and allows individual plots in the
 * field to be set.
 * 
 * @author AASoftware
 * 
 */
public class Field {

	/** The number of plots in the x direction. */
	public static final int COLUMNS = 5;
	/** The number of plots in the y direction. */
	public static final int ROWS = 6;
	/** The modifier which determines if a plot is to be a water plot. */
	private static final float DEFAULT_WATER_PLOT_MOD = .15f;
	/** The array containing the plots of this field. */
	private Plot[][] plots2D;

	/**
	 * Constructs a field with the default columns, rows, and water plot
	 * modifier.
	 */
	public Field() {
		initializePlots(COLUMNS, ROWS, DEFAULT_WATER_PLOT_MOD);
	}

	/**
	 * Constructs a field with the given dimensions and the default water plot
	 * modifier.
	 * 
	 * @param columns
	 *            - the number of plots wide the field should be
	 * @param rows
	 *            - the number of plots high the field should be
	 */
	public Field(int columns, int rows) {
		initializePlots(columns, rows, DEFAULT_WATER_PLOT_MOD);
	}

	/**
	 * Constructs a field with the given water plot modifier and the default
	 * columns and rows.
	 * 
	 * @param waterPlotMod
	 *            - the decimal percentage of how often water plots should
	 *            appear on the field
	 */
	public Field(float waterPlotMod) {
		initializePlots(COLUMNS, ROWS, waterPlotMod);
	}

	/**
	 * Constructs a filed with the given dimensions and the given water plot
	 * modifier.
	 * 
	 * @param columns
	 *            - the number of plots wide the field should be
	 * @param rows
	 *            - the number of plots high the field should be
	 * @param waterPlotMod
	 *            - the decimal percentage of how often water plots should
	 *            appear on the field
	 */
	public final void initializePlots(int columns, int rows, float waterPlotMod) {
		Random random = new Random();
		int waterCount = 0;
		float roll;
		plots2D = new Plot[COLUMNS][ROWS];
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				roll = random.nextFloat();
				if (roll < waterPlotMod) {
					plots2D[i][j] = new Plot(PlotType.WATER);
					waterCount++;
				} else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
		if (waterCount == 0) {
			int c = random.nextInt(columns);
			int r = random.nextInt(rows);
			plots2D[c][r] = new Plot(PlotType.WATER);
		}
	}

	// Old way to create fields
	/*
	 * public void createTutorialField() { plots2D = new Plot[COLUMNS][ROWS];
	 * for (int i = 0; i < plots2D.length; i++) { for (int j = 0; j <
	 * plots2D[i].length; j++) { if (i == 2) { plots2D[i][j] = new
	 * Plot(PlotType.WATER); } else { plots2D[i][j] = new Plot(PlotType.GRASS);
	 * } } } }
	 * 
	 * public void createRainforestField() { plots2D = new Plot[COLUMNS][ROWS];
	 * for (int i = 0; i < plots2D.length; i++) { for (int j = 0; j <
	 * plots2D[i].length; j++) { if (((i + j) % 3) == 0) { plots2D[i][j] = new
	 * Plot(PlotType.WATER); } else { plots2D[i][j] = new Plot(PlotType.GRASS);
	 * } } } }
	 * 
	 * public void createDesertField() { plots2D = new Plot[COLUMNS][ROWS]; for
	 * (int i = 0; i < plots2D.length; i++) { for (int j = 0; j <
	 * plots2D[i].length; j++) { if (i == COLUMNS - 1 && j == 0) { plots2D[i][j]
	 * = new Plot(PlotType.WATER); } else { plots2D[i][j] = new
	 * Plot(PlotType.GRASS); } } } }
	 * 
	 * public void createSnowField() { plots2D = new Plot[COLUMNS][ROWS]; for
	 * (int i = 0; i < plots2D.length; i++) { for (int j = 0; j <
	 * plots2D[i].length; j++) { if (j == 2 && (i == 1 || i ==2 || i ==3)) {
	 * plots2D[i][j] = new Plot(PlotType.WATER); } else { plots2D[i][j] = new
	 * Plot(PlotType.GRASS); } } } }
	 */

	public void setPlot(int x, int y, Plot plot) {
		plots2D[x][y] = plot;
	}

	public Plot getPlot(int x, int y) {
		return plots2D[x][y];
	}

}
