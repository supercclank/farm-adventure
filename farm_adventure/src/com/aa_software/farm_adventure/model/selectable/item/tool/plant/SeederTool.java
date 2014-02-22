package com.aa_software.farm_adventure.model.selectable.item.tool.plant;

import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;

public class SeederTool extends AbstractPlantTool {
	public static final String SEEDERTOOL_NAME = "Seeder";
	public static final String TEXTURE_NAME = "seederTool";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public SeederTool() {
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.workTime = DEFAULT_WORK_TIME;
		this.seed = new CarrotCrop();
		this.name = SEEDERTOOL_NAME;
	}
	
	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
}
