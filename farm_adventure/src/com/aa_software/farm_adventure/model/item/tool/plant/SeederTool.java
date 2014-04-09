package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class SeederTool extends AbstractPlantTool {
	public static final String SEEDERTOOL_NAME = "seederTool";
	public static final String TEXTURE_NAME = "seederTool";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public SeederTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
		this.workTime = DEFAULT_WORK_TIME;
		this.seed = null;
		this.name = SEEDERTOOL_NAME;
		this.upgrade = null;
		this.description = "The seeder tool will allow you to plant seeds faster.";
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
