package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class HandPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "handPlowTool";
	// TODO: arbitrary values
	public static final int DEFAULT_COST = 10;
	public static final int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public HandPlowTool() {
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
