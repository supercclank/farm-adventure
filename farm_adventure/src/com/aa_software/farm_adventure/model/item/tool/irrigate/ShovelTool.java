package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class ShovelTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "shovelTool";
	public static final String SHOVELTOOL_NAME = "Shovel";
	public static int DEFAULT_COST = 0;
	public static int DEFAULT_VALUE = 0;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 10;

	public ShovelTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = SHOVELTOOL_NAME;
		this.upgrade = new BackhoeTool();
		this.upgrade.setPredecessor(this);
		this.description = "A shovel allows you to irrigate land at an average pace.";
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
