package com.aa_software.farm_adventure.model.selectable.item.crop;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public abstract class AbstractCrop extends AbstractItem {
	protected int growthTime;
	protected int output;

	public AbstractCrop() {
	}
	
	public int getGrowthTime() {
		return growthTime;
	}

	public int getOutput() {
		return output;
	}

	@Override
	public String getTextureName() {
		return "arbitrary";
		// TODO: change
	}

	public void setGrowthTime(int growthTime) {
		this.growthTime = growthTime;
	}

	public void setOutput(int output) {
		this.output = output;
	}
}
