package com.aa_software.farm_adventure.model;

import java.util.Random;

import com.aa_software.farm_adventure.model.plot.AbstractPlot;
import com.aa_software.farm_adventure.model.plot.GrassPlot;
import com.aa_software.farm_adventure.model.plot.PlowedPlot;
import com.aa_software.farm_adventure.model.plot.UnplowedPlot;

public class Field {
	
	private final int COLUMNS = 8;
	private final int ROWS = 8;
	private AbstractPlot[][] plots2D;
	
	public Field() {
		initializePlots(COLUMNS, ROWS);
	}
	
	public Field(int columns, int rows) {
		initializePlots(columns, rows);
	}
	
	public void initializePlots(int columns, int rows) {
		plots2D = new AbstractPlot[columns][rows];
		for(AbstractPlot[] plots : plots2D) {  
			for(AbstractPlot plot : plots) {
				plot = new GrassPlot();
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
		for(AbstractPlot[] plots : plots2D) {  
			for(AbstractPlot plot : plots) {
				Random random = new Random();
				int randNum = random.nextInt(3);
				if(randNum == 0) {
					plot = new GrassPlot();
				} else if (randNum == 1) {
					plot = new UnplowedPlot();
				} else {
					randNum = random.nextInt(3);
					if(randNum == 0) {
						plot = new UnplowedPlot(Irrigation.TOP);
					} else if (randNum == 1) {
						plot = new UnplowedPlot(Irrigation.BOTTOM_LEFT);
					} else {
						plot = new UnplowedPlot(Irrigation.LEFT_RIGHT);
					}
					
				}
			}
		}
	}
	
}
