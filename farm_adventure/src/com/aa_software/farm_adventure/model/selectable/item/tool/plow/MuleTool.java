package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

public class MuleTool extends HandPlowTool {
	public static final String TEXTURE_NAME = "mule_tool";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public MuleTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}

}
