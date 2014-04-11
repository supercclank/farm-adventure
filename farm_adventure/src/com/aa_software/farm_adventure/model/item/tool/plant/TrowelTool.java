package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * The lowest level of planting tool.
 * 
 * @author Bebop
 *
 */
public class TrowelTool extends AbstractPlantTool {
	public static final String TEXTURE_NAME = "trowelTool";
	public static final String NAME = "Trowel";
	public static int COST = 0;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 5;
	public static final String DESCRIPTION = "The trowel tool will allow you to plant seeds at an average pace.";

	public TrowelTool() {
		super(COST, VALUE, NAME, DESCRIPTION, WORK_TIME, new SeederTool());
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
