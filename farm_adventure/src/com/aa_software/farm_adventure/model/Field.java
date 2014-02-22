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
		/*plots2D = new Plot[columns][rows];
		for (Plot[] plots : plots2D) {
			for (int i = 0; i < plots.length; i++) {
				plots[i] = new Plot(PlotType.GRASS);
			}
		}*/
	}

	public void createTutorialField() {
		plots2D = new Plot[COLUMNS][ROWS];
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				if (i == 2) {
					plots2D[i][j] = new Plot(PlotType.WATER);
					plots2D[i][j].setUsable(false);
				}
				else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
	}
	
	public void createRainforestField() {
		plots2D = new Plot[COLUMNS][ROWS];
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				if (((i + j) % 3) == 0) {
					plots2D[i][j] = new Plot(PlotType.WATER);
					plots2D[i][j].setUsable(false);
				}
				else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
	}
	
	public void createDesertField() {
		plots2D = new Plot[COLUMNS][ROWS];
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				if (i == COLUMNS - 1 && j == 0) {
					plots2D[i][j] = new Plot(PlotType.WATER);
					plots2D[i][j].setUsable(false);
				}
				else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
	}
	
	public void createSnowField() {
		plots2D = new Plot[COLUMNS][ROWS];
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				if (j == 2 && (i == 1 || i ==2 || i ==3)) {
					plots2D[i][j] = new Plot(PlotType.WATER);
					plots2D[i][j].setUsable(false);
				}
				else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
	}
	
	public void setPlot(int x, int y, Plot plot) {
		plots2D[x][y] = plot;
	}

}
