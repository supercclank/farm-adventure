package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * A direct upgrade to the shovel tool.
 * 
 * @author Bebop
 *
 */
public class BackhoeTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "backhoeTool";
	public static final String NAME = "Backhoe";
	public static int COST = 30;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 5;
	protected static final String DESCRIPTION = "The backhoe tool will allow you to irrigate your plot faster.";

	public BackhoeTool() {
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
