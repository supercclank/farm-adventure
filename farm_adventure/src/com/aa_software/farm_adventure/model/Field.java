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
		syncAllIrrigation();
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
		syncAllIrrigation();
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
		syncAllIrrigation();
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
		syncAllIrrigation();
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
		Plot plot = getPlot(x, y);
		EnumSet<Irrigation> selectedIrrigation = plot.getIrrigation();
		EnumSet<Irrigation> choices = EnumSet.noneOf(Irrigation.class);

		if(plot.isUsable()) {
			if(selectedIrrigation.size() == 1) {
				Irrigation[] irrigationArray = new Irrigation[1];
				irrigationArray = selectedIrrigation.toArray(irrigationArray);
				switch(irrigationArray[0]) {
					case TOP:
						if(canIrrigateBottom(plot, x, y)) {
							choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.BOTTOM)));
						} 
						choices.addAll(EnumSet.of(Irrigation.LEFT, Irrigation.RIGHT));
						break;
					case BOTTOM:
						if(canIrrigateTop(plot, x, y)) {
							choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.TOP)));
						} 
						choices.addAll(EnumSet.of(Irrigation.LEFT, Irrigation.RIGHT));
						break;
					case LEFT:
						if(canIrrigateRight(plot, x, y)) {
							choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.RIGHT)));
						} 
						choices.addAll(EnumSet.of(Irrigation.TOP, Irrigation.BOTTOM));
						break;
					case RIGHT:
						if(canIrrigateLeft(plot, x, y)) {
							choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.LEFT)));
						} 
						choices.addAll(EnumSet.of(Irrigation.TOP, Irrigation.BOTTOM));
						break;
				} 
			} else if (selectedIrrigation.isEmpty()) {
				//TODO: this should depend on all of the surrounding squares, including PlotType.WATER
				if(canIrrigateBottom(plot, x, y)) {
					choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.BOTTOM)));
				} 
				if(canIrrigateTop(plot, x, y)) {
					choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.TOP)));
				} 
				if(canIrrigateRight(plot, x, y)) {
					choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.RIGHT)));
				} 
				if(canIrrigateLeft(plot, x, y)) {
					choices.addAll(EnumSet.complementOf(EnumSet.of(Irrigation.LEFT)));
				} 
			} else {
				return EnumSet.complementOf(selectedIrrigation);
			}
		}
		return choices;
	}
	
	private boolean canIrrigateBottom(Plot plot, int x, int y) {
		boolean bottomNeighborHasLeftAndRight = y-1 >= 0 &&
				(getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT) ||
				getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT));
		
		boolean leftNeighborHasBottom = x-1 >= 0 &&
				getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM);
				
		boolean rightNeighborHasBottom = x+1 < Field.COLUMNS &&
				getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM);
		
		boolean canIrrigateBottom = bottomNeighborHasLeftAndRight || leftNeighborHasBottom ||
				rightNeighborHasBottom;
		
		return canIrrigateBottom;
	}
	
	private boolean canIrrigateTop(Plot plot, int x, int y) {
		boolean topNeighborHasLeftAndRight = y+1 < Field.ROWS &&
				(getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT) ||
				getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT));
		
		boolean leftNeighborHasTop = x-1 >= 0 &&
				getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP);
		
		boolean rightNeighborHasTop = x+1 < Field.COLUMNS &&
		getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP);
		
		boolean canIrrigateTop = topNeighborHasLeftAndRight || leftNeighborHasTop 
				|| rightNeighborHasTop;
		
		return canIrrigateTop;
	}
	
	private boolean canIrrigateRight(Plot plot, int x, int y) {
		boolean rightNeighborHasTopAndBottom = x+1 < Field.COLUMNS &&
				(getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP) ||
				getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM));
		
		boolean topNeighborHasRight = y+1 < Field.ROWS &&
				getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT);
		
		boolean bottomNeighborHasRight = y-1 >= 0 &&
				getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT);
		
		boolean canIrrigateRight = rightNeighborHasTopAndBottom || topNeighborHasRight ||
				bottomNeighborHasRight;
		
		return canIrrigateRight;
	}
	
	private boolean canIrrigateLeft(Plot plot, int x, int y) {
		boolean leftNeighborHasTopAndBottom = x-1 >= 0 &&
		(getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP) ||
		getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM));
		
		boolean topNeighborHasLeft = y+1 < Field.ROWS &&
		getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT);
		
		boolean bottomNeighborHasLeft = y-1 >= 0 &&
		getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT);
		
		boolean canIrrigateLeft = leftNeighborHasTopAndBottom || topNeighborHasLeft ||
				bottomNeighborHasLeft;
		
		return canIrrigateLeft;
	}
	
	public void syncNeighborIrrigation(int x, int y) {
		if (plots2D[x][y].getPlotType() == PlotType.WATER) {
			/* if neighbor does not have right */
			if(x-1 >= 0 &&
					!(plots2D[x-1][y].getPlotType() == PlotType.WATER) &&
					!plots2D[x-1][y].getIrrigation().contains(Irrigation.RIGHT)) {
				plots2D[x-1][y].addIrrigation(Irrigation.RIGHT);
			}
			/* if neighbor does not have left */
			if(x+1 < Field.COLUMNS &&
					!(plots2D[x+1][y].getPlotType() == PlotType.WATER) &&
					!plots2D[x+1][y].getIrrigation().contains(Irrigation.LEFT)) {
				plots2D[x+1][y].addIrrigation(Irrigation.LEFT);
			}
			/* if neighbor does not have top */
			if(y+1 < Field.ROWS &&
					!(plots2D[x][y+1].getPlotType() == PlotType.WATER) &&
					!plots2D[x][y+1].getIrrigation().contains(Irrigation.BOTTOM)) {
				plots2D[x][y+1].addIrrigation(Irrigation.BOTTOM);
			}
			/* if neighbor does not have bottom */
			if(y-1 >= 0 &&
					!(plots2D[x][y-1].getPlotType() == PlotType.WATER) &&
					!plots2D[x][y-1].getIrrigation().contains(Irrigation.TOP)) {
				plots2D[x][y-1].addIrrigation(Irrigation.TOP);
			}
		} else if (plots2D[x][y].isIrrigated()) {
			/* if selected has left and neighbor does not have right */
			if(plots2D[x][y].getIrrigation().contains(Irrigation.LEFT) && 
					x-1 >= 0 &&
					!(plots2D[x-1][y].getPlotType() == PlotType.WATER) &&
					!plots2D[x-1][y].getIrrigation().contains(Irrigation.RIGHT)) {
				plots2D[x-1][y].addIrrigation(Irrigation.RIGHT);
			}
			/* if selected has right and neighbor does not have left */
			if(plots2D[x][y].getIrrigation().contains(Irrigation.RIGHT) &&
					x+1 < Field.COLUMNS &&
					!(plots2D[x+1][y].getPlotType() == PlotType.WATER) &&
					!plots2D[x+1][y].getIrrigation().contains(Irrigation.LEFT)) {
				plots2D[x+1][y].addIrrigation(Irrigation.LEFT);
			}
			/* if selected has top and neighbor does not have top */
			if(plots2D[x][y].getIrrigation().contains(Irrigation.TOP) && 
					y+1 < Field.ROWS  &&
					!(plots2D[x][y+1].getPlotType() == PlotType.WATER) &&
					!plots2D[x][y+1].getIrrigation().contains(Irrigation.BOTTOM)) {
				plots2D[x][y+1].addIrrigation(Irrigation.BOTTOM);
			}
			/* if selected has bottom and neighbor does not have bottom */
			if(plots2D[x][y].getIrrigation().contains(Irrigation.BOTTOM) && 
					y-1 >= 0  &&
					!(plots2D[x][y-1].getPlotType() == PlotType.WATER) &&
					!plots2D[x][y-1].getIrrigation().contains(Irrigation.TOP)) {
				plots2D[x][y-1].addIrrigation(Irrigation.TOP);
			}
		}
	}
	
	public void syncAllIrrigation() {
		for (int i = 0; i < plots2D.length; i++) {
			for (int j = 0; j < plots2D[i].length; j++) {
				if (plots2D[i][j].getPlotType() == PlotType.WATER ||
						plots2D[i][j].isIrrigated()) {
					syncNeighborIrrigation(i, j);
				} 
			}
		}
	}
}
