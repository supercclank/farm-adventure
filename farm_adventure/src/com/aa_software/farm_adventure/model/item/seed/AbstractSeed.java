package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractSeed extends AbstractItem {

	protected String texture;
	protected AbstractCrop crop;
	protected float growthTime;
	protected float growthRateMod;
	public final static String TYPE = "SEEDS";

	public AbstractSeed(int cost, int value, String name, String description,
			float growthTime, float growthRateMod, AbstractCrop crop,
			String texture) {
		super(cost, value, name, description);
		this.growthTime = growthTime;
		this.growthRateMod = growthRateMod;
		this.crop = crop;
		this.texture = texture;
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public float getGrowthTime() {
		return growthTime * growthRateMod;
	}

	@Override
	public String getItemType() {
		return TYPE;
	}

	@Override
	public String getTextureName() {
		return texture;
	}

	public void setGrowthRateMod(float growthRateMod) {
		this.growthRateMod = growthRateMod;
	}

	@Override
	public void update(AbstractItem item) {
	}

	@Override
	public void update(Plot plot, Inventory inventory) {
	}
}
