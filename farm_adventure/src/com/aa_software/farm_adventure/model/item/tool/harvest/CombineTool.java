package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * A direct upgrade to the scythe tool.
 * 
 * @author Bebop
 *
 */
public class CombineTool extends AbstractHarvestTool {
	public static final String TEXTURE_NAME = "combineTool";
	public static final String NAME = "Combine";
	public static int COST = 30;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 3;
	protected static final String DESCRIPTION = "The combine tool will allow to harvest your crops faster.";

	public CombineTool() {
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
