package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * The lowest level of plowing tool.
 * 
 * @author Bebop
 * 
 */
public class HandPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "handPlowTool";
	public static final String NAME = "Hand Plow";
	public static final int COST = 0;
	public static final int VALUE = 0;
	public static final int WORK_TIME = 5;
	protected static final String DESCRIPTION = "A hand plow which allows you to plow land at an average pace.";

	public HandPlowTool() {
		super(COST, VALUE, NAME, DESCRIPTION, WORK_TIME, new TractorPlowTool());
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
