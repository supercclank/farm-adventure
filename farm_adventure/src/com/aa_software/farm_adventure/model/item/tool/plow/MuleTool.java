package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class MuleTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "muleTool";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public MuleTool() {
		cost = DEFAULT_COST;
		value = DEFAULT_VALUE;
		workTime = DEFAULT_WORK_TIME;
	}

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}

	@Override
	public void update(AbstractItem item) {
		// TODO Auto-generated method stub
		
	}

}
