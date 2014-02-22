package com.aa_software.farm_adventure.model.selectable.item.tool.plow;

public class MuleTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "muleTool";
	public static final String MULETOOL_NAME = "Mule";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public MuleTool() {
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = MULETOOL_NAME;
	}
	
	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}

}
