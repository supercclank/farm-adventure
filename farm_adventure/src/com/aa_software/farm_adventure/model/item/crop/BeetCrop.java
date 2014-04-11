package com.aa_software.farm_adventure.model.item.crop;

public class BeetCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "beetCrop";
	public static final String SEED_NAME = "beetSeed";
	public static final String NAME = "Beet";
	public static final int VALUE = 60;
	public static final int COST = 0;
	public static final String DESCRIPTION = "Beets that you can sell in the market place";

	public BeetCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
