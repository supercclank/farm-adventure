package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;

public class TrowelTool extends AbstractPlantTool {
	public static final String TEXTURE_NAME = "trowelTool";
	public static final String TROWELTOOL_NAME = "Trowel";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 0;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public TrowelTool() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.seed = new CarrotCrop();
		this.name = TROWELTOOL_NAME;
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
