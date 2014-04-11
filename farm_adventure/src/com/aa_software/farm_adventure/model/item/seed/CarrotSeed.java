package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;

public class CarrotSeed extends AbstractSeed {

	public static final int COST = 20;
	public static final int VALUE = 10;
	public static final float GROWTH_RATE = 5;
	public static final String NAME = "Carrot Seed";
	public static final String DESCRIPTION = "Plant this seed and it will produce carrots over time.";
	public static final String SEED_TEXTURE_NAME = "carrotSeed";

	public CarrotSeed() {
		super(COST, VALUE, NAME, DESCRIPTION, GROWTH_RATE, 1, new CarrotCrop(),
				SEED_TEXTURE_NAME);
	}
}
