package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractSeed extends AbstractItem {

	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;
	public static final float DEFAULT_GROWTH_TIME = 5;
	public static final int DEFAULT_OUTPUT = 4;
	
	protected float growthRateMod;
	protected int output;
	protected String texture = null;

	public AbstractSeed() {
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.growthRateMod = 1;
		this.output = DEFAULT_OUTPUT;
	}
	
	public abstract AbstractCrop getCrop();

	@Override
	public String getItemType() {
		return "SEEDS";
	}

	public String getSeedName() {
		return "seed";
	}

	@Override
	public String getTextureName() {
		return texture;
	}

	@Override
	public void update(AbstractItem item) {}

	@Override
	public void update(Plot plot, Inventory inventory) {}
	
	public void setGrowthRateMod(float growthRateMod) {
		this.growthRateMod = growthRateMod;
	}
	
	public float getGrowthTime() {
		return DEFAULT_GROWTH_TIME * growthRateMod;
	}
	
	public int getOutput() {
		return output;
	}
}
