package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;

public class CopperToolsUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "copper_tools_upgrade";
	public static final String COPPERTOOL_NAME = "Copper Tools";
	public static final float WORK_RATE_MOD = .8f;
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	
	public CopperToolsUpgrade() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.name = COPPERTOOL_NAME;
	}
	
	public String getTextureName() {
		return TEXTURE_NAME;
	}

	public void update(AbstractTool tool) {
		float oldWorkTime = tool.getWorkTime();
		float newWorkTime = oldWorkTime * WORK_RATE_MOD;
		tool.setWorkTime(newWorkTime);
	}
}
