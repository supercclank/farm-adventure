package com.aa_software.farm_adventure.model;

import java.util.EnumSet;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import java.util.Random;

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

	/*public void createTutorialField() {
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
	}*/
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
					plots2D[i][j].setUsable(false);
					waterCount++;
				}
				else {
					plots2D[i][j] = new Plot(PlotType.GRASS);
				}
			}
		}
		if (waterCount == 0) {
			int c = random.nextInt(columns);
			int r = random.nextInt(rows);
			plots2D[c][r] = new Plot(PlotType.WATER);
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
			if(selectedIrrigation.size() <= 1) {
				if(canIrrigateBottom(x, y)) {
					choices.add(Irrigation.BOTTOM);
				} 
				if(canIrrigateTop(x, y)) {
					choices.add(Irrigation.TOP);
				} 
				if(canIrrigateRight(x, y)) {
					choices.add(Irrigation.RIGHT);
				} 
				if(canIrrigateLeft(x, y)) {
					choices.add(Irrigation.LEFT);
				} 
			} else {
				return EnumSet.complementOf(selectedIrrigation);
			}
		}
		return choices;
	}
	
	/**
	 * Checks whether the bottom of a particular plot is open to irrigation by
	 * checking its neighboring plots for irrigation or water.
	 * 
	 * @param x		the x-coordinate of the selected plot.
	 * @param y		the y-coordinate of the selected plot.
	 * @return		whether the plot is open to being irrigated across the bottom.
	 */
	private boolean canIrrigateBottom(int x, int y) {
		Plot plot = getPlot(x, y);
		
		if(plot.getIrrigation().contains(Irrigation.BOTTOM)) {
			return false;
		}
		
		if(plot.getIrrigation().contains(Irrigation.LEFT) || 
				plot.getIrrigation().contains(Irrigation.RIGHT)) {
			return true;
		}
		
		boolean bottomNeighborHasLeftOrRight = y-1 >= 0 &&
				(getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT) ||
				getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT));
		
		boolean leftNeighborHasBottom = x-1 >= 0 &&
				getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM);
				
		boolean rightNeighborHasBottom = x+1 < Field.COLUMNS &&
				getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM);
		
		boolean canIrrigateBottom = bottomNeighborHasLeftOrRight || leftNeighborHasBottom ||
				rightNeighborHasBottom;
		
		return canIrrigateBottom;
	}
	
	/**
	 * Checks whether the top of a particular plot is open to irrigation by
	 * checking its neighboring plots for irrigation or water.
	 * 
	 * @param x		the x-coordinate of the selected plot.
	 * @param y		the y-coordinate of the selected plot.
	 * @return		whether the plot is open to being irrigated across the top.
	 */
	private boolean canIrrigateTop(int x, int y) {
		Plot plot = getPlot(x, y);
		
		if(plot.getIrrigation().contains(Irrigation.TOP)) {
			return false;
		}
		
		if(plot.getIrrigation().contains(Irrigation.LEFT) || 
				plot.getIrrigation().contains(Irrigation.RIGHT)) {
			return true;
		}
		
		boolean topNeighborHasLeftOrRight = y+1 < Field.ROWS &&
				(getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT) ||
				getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT));
		
		boolean leftNeighborHasTop = x-1 >= 0 &&
				getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP);
		
		boolean rightNeighborHasTop = x+1 < Field.COLUMNS &&
		getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP);
		
		boolean canIrrigateTop = topNeighborHasLeftOrRight || leftNeighborHasTop 
				|| rightNeighborHasTop;
		
		return canIrrigateTop;
	}
	
	/**
	 * Checks whether the right of a particular plot is open to irrigation by
	 * checking its neighboring plots for irrigation or water.
	 * 
	 * @param x		the x-coordinate of the selected plot.
	 * @param y		the y-coordinate of the selected plot.
	 * @return		whether the plot is open to being irrigated across the right.
	 */
	private boolean canIrrigateRight(int x, int y) {
		Plot plot = getPlot(x, y);
		
		if(plot.getIrrigation().contains(Irrigation.RIGHT)) {
			return false;
		}
		
		if(plot.getIrrigation().contains(Irrigation.TOP) || 
				plot.getIrrigation().contains(Irrigation.BOTTOM)) {
			return true;
		}
		
		boolean rightNeighborHasTopOrBottom = x+1 < Field.COLUMNS &&
				(getPlot(x+1, y).getIrrigation().contains(Irrigation.TOP) ||
				getPlot(x+1, y).getIrrigation().contains(Irrigation.BOTTOM));
		
		boolean topNeighborHasRight = y+1 < Field.ROWS &&
				getPlot(x, y+1).getIrrigation().contains(Irrigation.RIGHT);
		
		boolean bottomNeighborHasRight = y-1 >= 0 &&
				getPlot(x, y-1).getIrrigation().contains(Irrigation.RIGHT);
		
		boolean canIrrigateRight = rightNeighborHasTopOrBottom || topNeighborHasRight ||
				bottomNeighborHasRight;
		
		return canIrrigateRight;
	}
	
	/**
	 * Checks whether the left of a particular plot is open to irrigation by
	 * checking its neighboring plots for irrigation or water.
	 * 
	 * @param x		the x-coordinate of the selected plot.
	 * @param y		the y-coordinate of the selected plot.
	 * @return		whether the plot is open to being irrigated across the left.
	 */
	private boolean canIrrigateLeft(int x, int y) {
		Plot plot = getPlot(x, y);
		
		if(plot.getIrrigation().contains(Irrigation.LEFT)) {
			return false;
		}
		
		if(plot.getIrrigation().contains(Irrigation.TOP) || 
				plot.getIrrigation().contains(Irrigation.BOTTOM)) {
			return true;
		}
		
		boolean leftNeighborHasTopOrBottom = x-1 >= 0 &&
		(getPlot(x-1, y).getIrrigation().contains(Irrigation.TOP) ||
		getPlot(x-1, y).getIrrigation().contains(Irrigation.BOTTOM));
		
		boolean topNeighborHasLeft = y+1 < Field.ROWS &&
		getPlot(x, y+1).getIrrigation().contains(Irrigation.LEFT);
		
		boolean bottomNeighborHasLeft = y-1 >= 0 &&
		getPlot(x, y-1).getIrrigation().contains(Irrigation.LEFT);
		
		boolean canIrrigateLeft = leftNeighborHasTopOrBottom || topNeighborHasLeft ||
				bottomNeighborHasLeft;
		
		return canIrrigateLeft;
	}
	
	/**
	 * Syncs neighboring square's irrigation with the plot at the given
	 * x-and-y-coordinate. If the given plot has irrigation that neighbors a square
	 * or the plot is water, then the neighbor plot should share the irrigation.
	 * 
	 * @param x		the x-coordinate of the selected plot.
	 * @param y		the y-coordinate of the selected plot.
	 * 
	 */
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
	
	/**
	 * Syncs all square's and their neighbor's irrigation.
	 * 
	 * @see syncNeighborIrrigation
	 * 
	 */
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
	public Plot getPlot(int x, int y) {
		return plots2D[x][y];
	}
}
