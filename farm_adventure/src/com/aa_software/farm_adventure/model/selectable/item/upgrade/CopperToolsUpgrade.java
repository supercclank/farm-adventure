package com.aa_software.farm_adventure.model.selectable.item.upgrade;

import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;

public class CopperToolsUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "copper_tools_upgrade";
	public static final float WORK_RATE_MOD = .8f;
	
	public void update(AbstractTool tool) {
		float oldWorkTime = tool.getWorkTime();
		float newWorkTime = oldWorkTime * WORK_RATE_MOD;
		tool.setWorkTime(newWorkTime);
	}
}
