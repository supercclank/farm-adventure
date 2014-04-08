package com.aa_software.farm_adventure.model.item.crop;

public class BananaCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "bananaCrop";
	public static final String SEED_NAME = "bananaSeed";
	public static final String BANANA_NAME = "Banana";
	public static final int DEFAULT_VALUE = 80;

	public BananaCrop() {
		super();
		this.value = DEFAULT_VALUE;
		this.name = BANANA_NAME;
		this.description = "Bananas that you can sell in the market place";
	}

	public BananaCrop(int growthTime, int output, int cost, int value) {
		super();
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
