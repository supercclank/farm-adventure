package com.aa_software.farm_adventure.model.item.crop;

public class BeetCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "beetCrop";
	public static final String SEED_NAME = "beetSeed";
	public static final String BEET_NAME = "Beet";
	public static final int DEFAULT_VALUE = 60;

	public BeetCrop() {
		super();
		this.value = DEFAULT_VALUE;
		this.name = BEET_NAME;
		this.description = "Beets that you can sell in the market place";

	}

	public BeetCrop(int growthTime, int output, int cost, int value) {
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
