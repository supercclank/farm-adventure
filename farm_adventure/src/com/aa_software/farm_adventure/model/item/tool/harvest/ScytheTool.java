package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * The lowest level of harvesting tool.
 * 
 * @author Bebop
 *
 */
public class ScytheTool extends AbstractHarvestTool {
	public static final String TEXTURE_NAME = "scytheTool";
	public static final String NAME = "Scythe";
	public static int COST = 0;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 5;
	protected static final String DESCRIPTION = "A tool that allows you to harvest at an average pace";

	public ScytheTool() {
		super(COST, VALUE, NAME, DESCRIPTION, WORK_TIME, new CombineTool());
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
