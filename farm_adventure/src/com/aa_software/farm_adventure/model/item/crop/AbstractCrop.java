package com.aa_software.farm_adventure.model.item.crop;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractCrop extends AbstractItem {
	public static final float DEFAULT_GROWTH_TIME = 5;
	protected float growthTime;
	protected int output;
	protected boolean isHarvestable;

	public AbstractCrop() {
		isHarvestable = false;
	}

	public float getGrowthTime() {
		return growthTime;
	}

	@Override
	public String getItemType() {
		return "CROPS";
	}

	public int getOutput() {
		return output;
	}

	public String getSeedName() {
		return "crops";
	}

	@Override
	public String getTextureName() {
		return "texture";
	}

	public boolean isHarvestable() {
		return isHarvestable;
	}

	public void setGrowthTime(float growthTime) {
		this.growthTime = growthTime;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	@Override
	public void update(AbstractItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Plot plot, Inventory inventory) {
	}
}
