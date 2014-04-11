package com.aa_software.farm_adventure.model.item.crop;

public class RiceCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "riceCrop";
	public static final String SEED_NAME = "riceSeed";
	public static final String NAME = "Rice";
	public static final int VALUE = 80;
	public static final int COST = 0;
	public static final String DESCRIPTION = "Rice that you can sell in the market place";

	public RiceCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
