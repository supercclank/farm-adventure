package com.aa_software.farm_adventure.model.item.tool;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public abstract class AbstractTool extends AbstractItem {

	protected float workTime;

	public float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}

}
