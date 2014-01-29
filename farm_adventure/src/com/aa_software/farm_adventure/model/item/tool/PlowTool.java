package com.aa_software.farm_adventure.model.item.tool;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;

public class PlowTool extends AbstractItem {
	//TODO: arbitrary values
	public static String TEXTURE_NAME = "plow_tool";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;

	public PlowTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
	}
	
	public void update(Plot plot) {
		plot.setPlotType(PlotType.PLOWED);
	}
	
}
