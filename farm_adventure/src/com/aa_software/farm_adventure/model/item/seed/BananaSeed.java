package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.BananaCrop;

/**
 * Represents banana seeds, which will grow into a bundle of bananas.
 * 
 * @author Bebop
 * 
 */
public class BananaSeed extends AbstractSeed {

	public static final int COST = 20;
	public static final int VALUE = 10;
	public static final int GROWTH_RATE = 5;
	public static final String NAME = "Banana Seed";
	public static final String CROP_TEXTURE_NAME = "bananaCrop";
	public static final String SEED_TEXTURE_NAME = "bananaSeed";
	public static final String DESCRIPTION = "Plant this seed and it will produce bananas over time.";

	public BananaSeed() {
		super(COST, VALUE, NAME, DESCRIPTION, GROWTH_RATE, 1, new BananaCrop(),
				SEED_TEXTURE_NAME);
	}
}
