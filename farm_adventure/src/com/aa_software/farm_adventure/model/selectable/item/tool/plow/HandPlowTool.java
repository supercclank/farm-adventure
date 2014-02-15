package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class HandPlowTool extends AbstractTool {
	public static final String TEXTURE_NAME = "handPlowTool";
	// TODO: arbitrary values
	public static final int DEFAULT_COST = 10;
	public static final int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public HandPlowTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}

	public void update(Plot plot) {
		if (plot.getPlotType().equals(PlotType.GRASS)) {
			if (plot.getIrrigation() == null)
				plot.setPlotType(PlotType.PLOWEDUNWATERED);
			else
				plot.setPlotType(PlotType.PLOWEDWATERED);
		}
	}

	public String getTextureName() {
		// TODO remove
		// System.out.println(getClass().getSimpleName());
		// return this.getClass().getSimpleName();
		return TEXTURE_NAME;
	}

}
