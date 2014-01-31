package com.aa_software.farm_adventure.model.selectable.item.tool;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public class AbstractTool extends AbstractItem {

	protected float workTime;
	
	public float getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}
	
}
