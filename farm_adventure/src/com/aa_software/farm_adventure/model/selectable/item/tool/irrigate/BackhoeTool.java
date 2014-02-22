package com.aa_software.farm_adventure.model.selectable.item.tool.irrigate;

public class BackhoeTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "backhoeTool";
	public static final String BACKHOE_NAME = "Backhoe";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 10;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public BackhoeTool() {
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = BACKHOE_NAME;
	}
	
	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
}
