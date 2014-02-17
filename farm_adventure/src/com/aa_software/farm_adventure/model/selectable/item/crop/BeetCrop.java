package com.aa_software.farm_adventure.model.selectable.item.crop;

public class BeetCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "beetCrop";
	public static final String SEED_NAME = "beetSeed";
	public static final int DEFAULT_GROWTH_TIME = 60;
	public static final int DEFAULT_OUTPUT = 500;
	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;

	public BeetCrop() {
		this.growthTime = DEFAULT_GROWTH_TIME;
		this.output = DEFAULT_OUTPUT;
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
	}

	public BeetCrop(int growthTime, int output, int cost, int value) {
		if (growthTime != 0) {
			this.growthTime = growthTime;
		} else {
			this.growthTime = DEFAULT_GROWTH_TIME;
		}
		if (output != 0) {
			this.output = output;
		} else {
			this.output = DEFAULT_OUTPUT;
		}
		if (cost != 0) {
			this.cost = cost;
		} else {
			this.cost = DEFAULT_COST;
		}
		if (value != 0) {
			this.value = value;
		} else {
			this.value = DEFAULT_VALUE;
		}
	}
	
	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
	
	@Override
	public String getSeedName() {
		return SEED_NAME;
	}
}
