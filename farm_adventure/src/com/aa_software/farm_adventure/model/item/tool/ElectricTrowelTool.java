package com.aa_software.farm_adventure.model.item.tool;

public class ElectricTrowelTool extends TrowelTool {
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 10;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public ElectricTrowelTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}
	
}
