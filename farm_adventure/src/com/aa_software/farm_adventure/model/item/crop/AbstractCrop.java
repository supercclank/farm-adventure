package com.aa_software.farm_adventure.model.item.crop;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public abstract class AbstractCrop extends AbstractItem {
	protected int growthTime;
	protected int output;

	public int getGrowthTime() {
		return growthTime;
	}

	public void setGrowthTime(int growthTime) {
		this.growthTime = growthTime;
	}
	
	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}
}
