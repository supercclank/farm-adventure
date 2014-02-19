package com.aa_software.farm_adventure.model.selectable.item.tool.irrigate;

public class ShovelTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "shovelTool";
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
}
