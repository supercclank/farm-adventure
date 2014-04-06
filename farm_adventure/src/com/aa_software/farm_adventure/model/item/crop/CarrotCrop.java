package com.aa_software.farm_adventure.model.item.crop;

public class CarrotCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "carrotCrop";
	public static final String SEED_NAME = "carrotSeed";
	public static final String CARROT_NAME = "Carrot";
	public static final int DEFAULT_GROWTH_TIME = 60;
	public static final int DEFAULT_OUTPUT = 500;
	public static final int DEFAULT_COST = 60;
	public static final int DEFAULT_VALUE = 60;

	public CarrotCrop() {
		super();
		this.growthTime = DEFAULT_GROWTH_TIME;
		this.output = DEFAULT_OUTPUT;
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = CARROT_NAME;
		this.description = "Carrots that you can sell in the market place";
	}

	public CarrotCrop(int growthTime, int output, int cost, int value) {
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
	public String getSeedName() {
		return SEED_NAME;
	}

	@Override
	public String getTextureName() {
		return TEXTURE_NAME;
	}
}
