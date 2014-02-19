package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class Field {

	public static final int COLUMNS = 5;
	public static final int ROWS = 6;
	private Plot[][] plots2D;

	public Field() {
		initializePlots(COLUMNS, ROWS);
	}

	public Field(int columns, int rows) {
		initializePlots(columns, rows);
	}

	public Plot getPlot(int x, int y) {
		return plots2D[x][y];
	}

	public final void initializePlots(int columns, int rows) {
		plots2D = new Plot[columns][rows];
		for (Plot[] plots : plots2D) {
			for (int i = 0; i < plots.length; i++) {
				plots[i] = new Plot(PlotType.GRASS);
			}
		}
	}

	public void setPlot(int x, int y, Plot plot) {
		plots2D[x][y] = plot;
	}

}
