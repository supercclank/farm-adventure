package com.aa_software.farm_adventure.model.game_object;

import java.util.Random;

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
			for(Plot plot : plots) {
				plot = new Plot();
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
			for(Plot plot : plots) {
				Random random = new Random();
				int randNum = random.nextInt(3);
				if(randNum == 0) {
					plot = new Plot();
				} else if (randNum == 1) {
					plot = new Plot(PlotType.PLOWED);
				} else {
					randNum = random.nextInt(3);
					if(randNum == 0) {
						plot = new Plot(Irrigation.TOP);
					} else if (randNum == 1) {
						plot = new Plot(Irrigation.BOTTOM_LEFT);
					} else {
						plot = new Plot(Irrigation.LEFT_RIGHT);
					}
					
				}
			}
		}
	}
	
}
