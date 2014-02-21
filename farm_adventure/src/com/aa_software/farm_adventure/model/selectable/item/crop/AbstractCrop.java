package com.aa_software.farm_adventure.model.selectable.item.crop;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public abstract class AbstractCrop extends AbstractItem {
	public static final float DEFAULT_GROWTH_TIME = 5;
	protected float growthTime;
	protected int output;

	public AbstractCrop() {
	}

	public float getGrowthTime() {
		return growthTime;
	}

	public int getOutput() {
		return output;
	}

	public String getSeedName() {
		return "seed";
	}

	public String getTextureName() {
		return "texture";
	}

	public void setGrowthTime(float growthTime) {
		this.growthTime = growthTime;
	}

	public void setOutput(int output) {
		this.output = output;
	}
}
