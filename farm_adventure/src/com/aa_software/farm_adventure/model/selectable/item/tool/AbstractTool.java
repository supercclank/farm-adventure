package com.aa_software.farm_adventure.model.selectable.item.tool;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public class AbstractTool extends AbstractItem {

	protected float workTime;

	@Override
	public String getTextureName() {
		// TODO remove
		System.out.println(getClass().getSimpleName());
		return this.getClass().getSimpleName();
	}

	public float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}

}
