package com.aa_software.farm_adventure.model;

import java.util.Random;

import com.aa_software.farm_adventure.model.selectable.plot.Irrigation;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class Field {
	
	private final int COLUMNS = 8;
	private final int ROWS = 8;
	private Plot[][] plots2D;
	
	public Field() {
		initializePlots(COLUMNS, ROWS);
	}
	
	public Field(int columns, int rows) {
		initializePlots(columns, rows);
	}
	
	public void initializePlots(int columns, int rows) {
		plots2D = new Plot[columns][rows];
		for(Plot[] plots : plots2D) {  
			for (int i = 0; i < plots.length; i++) {
				plots[i] = new Plot(PlotType.GRASS);
			}
		}
	}
	
	/*
	 *  This is just a method for testing purposes.
	 */
	public void randomizeField() {
		if(plots2D == null) {
			initializePlots(COLUMNS, ROWS);
		}
		for(Plot[] plots : plots2D) {  
			for (int i = 0; i < plots.length; i++) {
				Random random = new Random();
				int randNum = random.nextInt(3);
				if(randNum == 0) {
					plots[i] = new Plot(PlotType.GRASS);
				} else if (randNum == 1) {
					plots[i] = new Plot(PlotType.UNPLOWED);
				} else {
					randNum = random.nextInt(3);
					if(randNum == 0) {
						plots[i] = new Plot(PlotType.UNPLOWED, Irrigation.TOP);
					} else if (randNum == 1) {
						plots[i] = new Plot(PlotType.UNPLOWED, Irrigation.BOTTOM_LEFT);
					} else {
						plots[i] = new Plot(PlotType.UNPLOWED, Irrigation.LEFT_RIGHT);
					}
					
				}
			}
		}
	}
	
}
