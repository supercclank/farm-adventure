package com.aa_software.farm_adventure.model.selectable.item.tool.harvest;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class AbstractHarvestTool extends AbstractTool {
	
	@Override
	public void update(Plot plot) {
		AbstractCrop crop = plot.getCrop();
		if (crop != null) {
			// TODO: how to add the crop to inventory?
			plot.setCrop(null);
			plot.setPlotType(PlotType.UNPLOWEDWATERED);
		}
	}
	
	public String getTextureName() {
		return "arbitrary";
	}
}
