package com.aa_software.farm_adventure.model.item.crop;

public class RiceCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "riceCrop";
	public static final String SEED_NAME = "riceSeed";
	public static final String RICE_NAME = "Rice";
	public static final int DEFAULT_VALUE = 80;

	public RiceCrop() {
		super();
		this.value = DEFAULT_VALUE;
		this.name = RICE_NAME;
		this.description = "Rice that you can sell in the market place";
	}

	public RiceCrop(int growthTime, int output, int cost, int value) {
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
