package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * A direct upgrade to the seeder tool.
 * 
 * @author Bebop
 *
 */
public class SeederTool extends AbstractPlantTool {
	public static final String NAME = "Seeder";
	public static final String TEXTURE_NAME = "seederTool";
	public static int COST = 10;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 5;
	protected static final String DESCRIPTION = "The seeder tool will allow you to plant seeds faster.";

	public SeederTool() {
		super(COST, VALUE, NAME, DESCRIPTION, WORK_TIME, null);
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
