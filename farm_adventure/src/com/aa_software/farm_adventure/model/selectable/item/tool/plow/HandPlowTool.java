package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

public class HandPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "handPlowTool";
	public static final String HANDPLOWTOOL_NAME = "Hand Plow";
	public static final int DEFAULT_COST = 10;
	public static final int DEFAULT_VALUE = 5;
	public static final int DEFAULT_WORK_TIME = 5;

	public HandPlowTool() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = HANDPLOWTOOL_NAME;
	}

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
}
