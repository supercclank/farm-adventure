package com.aa_software.farm_adventure.model.item.crop;

public class CarrotCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "carrotCrop";
	public static final String SEED_NAME = "carrotSeed";
	public static final String CARROT_NAME = "Carrot";
	public static final int DEFAULT_VALUE = 60;

	public CarrotCrop() {
		super();
		this.value = DEFAULT_VALUE;
		this.name = CARROT_NAME;
		this.description = "Carrots that you can sell in the market place";
	}

	public CarrotCrop(int growthTime, int output, int cost, int value) {
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
