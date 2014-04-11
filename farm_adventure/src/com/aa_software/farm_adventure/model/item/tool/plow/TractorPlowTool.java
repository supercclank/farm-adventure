package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * A direct upgrade to the hand plow tool.
 * 
 * @author Bebop
 *
 */
public class TractorPlowTool extends AbstractPlowTool {
	public static final String TEXTURE_NAME = "tractorPlowTool";
	public static final String NAME = "Tractor Plow";
	public static int COST = 30;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 3;
	protected static final String DESCRIPTION = "The tractor will allow you to plow faster.";

	public TractorPlowTool() {
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
