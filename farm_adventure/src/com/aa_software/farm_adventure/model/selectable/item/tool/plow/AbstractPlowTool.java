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
		}else if (plot.getPlotType().equals(PlotType.UNPLOWEDUNWATERED)){
			plot.setPlotType(PlotType.PLOWEDUNWATERED);
		}else if (plot.getPlotType().equals(PlotType.UNPLOWEDWATERED)){
			plot.setPlotType(PlotType.PLOWEDWATERED);
		}
	}
	
	public String getTextureName() {
		return "arbitrary";
	}
	
	public String getItemType() {
		return "PLOW TOOL";
	}
}
