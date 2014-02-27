package com.aa_software.farm_adventure.model;

import java.util.EnumSet;

import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;

public class Field {

	public static final int COLUMNS = 5;
	public static final int ROWS = 6;
	private final Plot[][] plots2D;

	public Field() {
		plots2D = new Plot[COLUMNS][ROWS];
	}

	public Field(int columns, int rows) {
		plots2D = new Plot[columns][rows];
	}

	public Plot getPlot(int x, int y) {
		return plots2D[x][y];
	}

	public void createTutorialField() {
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

	/**
	 * Calculates and returns the enumeration set corresponding to the selected
	 * plot's available irrigation choices. The takes into account the current
	 * state of the plot's irrigation, then reaches out to neighboring plots if 
	 * more information is needed.
	 * 
	 * @param x		the x value of the selected plot.
	 * @param y		the y value of the selected plot.
	 * @return		the available choices for irrigation.
	 */
	public EnumSet<Irrigation> getIrrigationChoices(int x, int y) {
		EnumSet<Irrigation> selectedIrrigation = getPlot(x, y).getIrrigation();
		EnumSet<Irrigation> choices = EnumSet.noneOf(Irrigation.class);
		if(selectedIrrigation.size() == 1) {
			Irrigation[] irrigationArray = new Irrigation[1];
			irrigationArray = selectedIrrigation.toArray(irrigationArray);
			boolean outOfBounds = false;
			switch(irrigationArray[0]) {
				case TOP:
					if(!outOfBounds &&
					(y+1 <= Field.ROWS &&
					getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT) ||
					getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT)) ||
					(x-1 >= 0 &&
					getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM)) ||
					(x+1 <= Field.COLUMNS &&
					getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM))) {
						choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.TOP)));
					} 
					choices.addAll(EnumSet.of(Irrigation.LEFT, Irrigation.RIGHT));
					break;
				case BOTTOM:
					if((y-1 >= 0 &&
					getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT) ||
					getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT)) ||
					(x-1 >= 0 &&
					getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP)) ||
					(x+1 <= Field.COLUMNS &&
					getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP))) {
						choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.BOTTOM)));
					} 
					choices.addAll(EnumSet.of(Irrigation.LEFT, Irrigation.RIGHT));
					break;
				case LEFT:
					if((x+1 <= Field.COLUMNS &&
					getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP) ||
					getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM)) ||
					(y-1 >= 0 &&
					getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT)) ||
					(y+1 <= Field.ROWS &&
					getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT))) {
						choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.LEFT)));
					} 
					choices.addAll(EnumSet.of(Irrigation.TOP, Irrigation.BOTTOM));
					break;
				case RIGHT:
					if((x-1 >= 0 &&
					getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP) ||
					getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM)) ||
					(y-1 >= 0 &&
					getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT)) ||
					(y+1 <= Field.ROWS &&
					getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT))) {
						choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.RIGHT)));
					} 
					choices.addAll(EnumSet.of(Irrigation.TOP, Irrigation.BOTTOM));
					break;
			}
		} else if (selectedIrrigation.isEmpty()) {
			//TODO: this should depend on all the surrounding squares.
			return EnumSet.allOf(Irrigation.class);
		} else {
			return EnumSet.complementOf(selectedIrrigation);
		}
		return choices;
	}
}
