package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class BackhoeTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "backhoeTool";
	public static final String BACKHOE_NAME = "Backhoe";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 10;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public BackhoeTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = BACKHOE_NAME;
		this.upgrade = null;
		this.description = "The backhoe tool will allow you to irrigate your plot faster.";
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
