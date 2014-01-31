package com.aa_software.farm_adventure.model.selectable.item.tool.plant;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

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
		//TODO: inventory pops up and you choose the plant to use.
		// AbstractCrop crop = the selected crop.
		// plot.setCrop(crop);
	}
	
	public void update(Plot plot, AbstractCrop crop) {
		//TODO: we'll have to decide if we're making a distinction between
		// produce and seeds. If so, change crop to seed.
		plot.setCrop(crop);
	}
	
}
