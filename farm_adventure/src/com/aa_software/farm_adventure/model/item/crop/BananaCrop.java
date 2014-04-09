package com.aa_software.farm_adventure.model.item.crop;

public class BananaCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "bananaCrop";
	public static final String SEED_NAME = "bananaSeed";
	public static final String NAME = "Banana";
	public static final String DESCRIPTION = "Bananas that you can sell in the market place";
	public static final int VALUE = 80;

	public BananaCrop() {
		super();
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
