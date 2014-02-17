package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class AbstractPlowTool extends AbstractTool{
	@Override
	public void update(Plot plot) {
		if (plot.getPlotType().equals(PlotType.GRASS)) {
			if (plot.getIrrigation() == null) {
				plot.setPlotType(PlotType.PLOWEDUNWATERED);
			} else {
				plot.setPlotType(PlotType.PLOWEDWATERED);
			}
		}
	}
	
	public String getTextureName() {
		return "arbitrary";
	}
}
