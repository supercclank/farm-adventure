package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/**
 * The lowest level of irrigation tool.
 * 
 * @author Bebop
 *
 */
public class ShovelTool extends AbstractIrrigationTool {
	public static final String TEXTURE_NAME = "shovelTool";
	public static final String NAME = "Shovel";
	public static int COST = 0;
	public static int VALUE = 0;
	/* measured in seconds */
	public static final int WORK_TIME = 10;
	protected static final String DESCRIPTION = "A shovel allows you to irrigate land at an average pace.";

	public ShovelTool() {
		super(COST, VALUE, NAME, DESCRIPTION, WORK_TIME, new BackhoeTool());
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
