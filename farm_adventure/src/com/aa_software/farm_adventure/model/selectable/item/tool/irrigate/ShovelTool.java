package com.aa_software.farm_adventure.model.selectable.item.tool.irrigate;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Irrigation;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class ShovelTool extends AbstractTool {
	public static final String TEXTURE_NAME = "hoe";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 10;

	public ShovelTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}

	@Override
	public void update(Plot plot) {
		// Irrigation oldIrrigation = plot.getIrrigation();
		// TODO: ARBITRARY. Add code for telling where to irrigate...
		if (plot.getPlotType().equals(PlotType.PLOWEDUNWATERED)) {
			plot.setPlotType(PlotType.PLOWEDWATERED);
			plot.setIrrigation(Irrigation.northSouthEastWest);
		}
	}
}
