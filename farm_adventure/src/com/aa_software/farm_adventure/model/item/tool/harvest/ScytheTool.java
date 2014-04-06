package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class ScytheTool extends AbstractHarvestTool {
	public static final String TEXTURE_NAME = "scytheTool";
	public static final String SYTHETOOL_NAME = "Scythe";
	public static int DEFAULT_COST = 0;
	public static int DEFAULT_VALUE = 0;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public ScytheTool() {
		super();
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
		this.workTime = DEFAULT_WORK_TIME;
		this.name = SYTHETOOL_NAME;
		this.upgrade = new CombineTool();
		this.upgrade.setPredecessor(this);
		this.description = "A tool that allows you to harvest at an average pace";
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
