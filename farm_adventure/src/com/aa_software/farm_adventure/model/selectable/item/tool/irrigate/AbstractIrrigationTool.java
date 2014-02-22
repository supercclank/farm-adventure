package com.aa_software.farm_adventure.model.selectable.item.tool.irrigate;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Irrigation;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class AbstractIrrigationTool extends AbstractTool {

	@Override
	public void update(Plot plot) {
		// Irrigation oldIrrigation = plot.getIrrigation();
		// TODO: ARBITRARY. Add code for telling where to irrigate...
		if (plot.getPlotType().equals(PlotType.PLOWEDUNWATERED)) {
			plot.setPlotType(PlotType.PLOWEDWATERED);
			plot.setIrrigation(Irrigation.TOP_LEFT_RIGHT_BOTTOM);
		}
	}
	
	public String getTextureName() {
		return "arbitrary";
	}
	
	public String getToolType() {
		return "IRRIGATION TOOL";
	}
}
