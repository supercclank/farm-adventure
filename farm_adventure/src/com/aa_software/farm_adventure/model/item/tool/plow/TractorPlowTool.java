package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class TractorPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "tractorPlowTool";
	public static final String TRACTOR_PLOW_TOOL_NAME = "Tractor Plow";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 3;

	public TractorPlowTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = TRACTOR_PLOW_TOOL_NAME;
		this.upgrade = null;
		this.description = "The tractor will allow you to plow faster.";
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
