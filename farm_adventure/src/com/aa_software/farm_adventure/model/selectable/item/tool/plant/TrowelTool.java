package com.aa_software.farm_adventure.model.selectable.item.tool.plant;

import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;

public class TrowelTool extends AbstractPlantTool {
	public static final String TEXTURE_NAME = "trowelTool";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public TrowelTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
		seed = new CarrotCrop();
	}

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
}
