package com.aa_software.farm_adventure.model.selectable.item.tool.harvest;

public class ScytheTool extends AbstractHarvestTool {
	public static final String TEXTURE_NAME = "scytheTool";
	public static final String SYTHETOOL_NAME = "Scythe";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public ScytheTool() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = SYTHETOOL_NAME;
	}
	

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}

}
