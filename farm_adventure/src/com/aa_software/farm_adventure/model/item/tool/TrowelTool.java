package com.aa_software.farm_adventure.model.item.tool;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;

public class TrowelTool extends AbstractTool{
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public TrowelTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}
	
	public void update(Plot plot) {
		AbstractCrop crop = plot.getCrop();
		if(crop != null) {
			//TODO: how to add the crop to inventory?
			plot.setCrop(null);
		}
	}
}
