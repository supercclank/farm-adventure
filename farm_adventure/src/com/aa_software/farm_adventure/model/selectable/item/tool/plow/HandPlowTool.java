package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class HandPlowTool extends AbstractTool {
	//TODO: arbitrary values
	public static String TEXTURE_KEY = "ground";
	public static String TEXTURE_VALUE = "grass";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public HandPlowTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}
	
	public void update(Plot plot) {
		if(plot.getPlotType().equals(PlotType.UNPLOWED)) {
			plot.setPlotType(PlotType.PLOWED);
		}
	}
	
}
