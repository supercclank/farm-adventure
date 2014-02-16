package com.aa_software.farm_adventure.model.selectable.item.tool.harvest;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class ScytheTool extends AbstractTool {
	public static final String TEXTURE_NAME = "scythe";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public ScytheTool() {
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
		AbstractCrop crop = plot.getCrop();
		if (crop != null) {
			// TODO: how to add the crop to inventory?
			plot.setCrop(null);
			plot.setPlotType(PlotType.GRASS);
		}
	}
}
