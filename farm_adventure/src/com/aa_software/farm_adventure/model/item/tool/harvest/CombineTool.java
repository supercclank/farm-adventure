package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class CombineTool extends AbstractHarvestTool {
	public static final String TEXTURE_NAME = "comibneTool";
	public static final String COMBINETOOL_NAME = "Combine";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 10;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public CombineTool() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = COMBINETOOL_NAME;
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
