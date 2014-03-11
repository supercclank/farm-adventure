package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.plot.Plot;

public class SeederTool extends AbstractPlantTool {
	public static final String SEEDERTOOL_NAME = "Seeder";
	public static final String TEXTURE_NAME = "seederTool";
	public static int DEFAULT_COST = 10;
	public static int DEFAULT_VALUE = 5;
	/* measured in seconds */
	public static final int DEFAULT_WORK_TIME = 5;

	public SeederTool() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.workTime = DEFAULT_WORK_TIME;
		this.seed = new CarrotCrop();
		this.name = SEEDERTOOL_NAME;
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
