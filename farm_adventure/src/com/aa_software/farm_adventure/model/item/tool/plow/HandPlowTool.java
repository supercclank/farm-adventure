package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class HandPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "handPlowTool";
	public static final String HANDPLOWTOOL_NAME = "Hand Plow";
	public static final int DEFAULT_COST = 10;
	public static final int DEFAULT_VALUE = 0;
	public static final int DEFAULT_WORK_TIME = 5;

	public HandPlowTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = HANDPLOWTOOL_NAME;
		this.upgrade = new TractorPlowTool();
		this.upgrade.setPredecessor(this);
		this.description = "A hand plow which allows you to plow land at an average pace.";
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
