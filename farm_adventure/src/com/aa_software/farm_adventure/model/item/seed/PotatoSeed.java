package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.PotatoCrop;

/**
 * Represents potato seeds, which will grow into a bundle of potatoes.
 * 
 * @author Bebop
 * 
 */
public class PotatoSeed extends AbstractSeed {

	public static final int COST = 20;
	public static final int VALUE = 10;
	public static final int GROWTH_RATE = 5;
	public static final String NAME = "Potato Seed";
	public static final String CROP_TEXTURE_NAME = "potatoCrop";
	public static final String SEED_TEXTURE_NAME = "potatoSeed";
	public static final String DESCRIPTION = "Plant this seed and it will produce potatoes over time.";

	public PotatoSeed() {
		super(COST, VALUE, NAME, DESCRIPTION, GROWTH_RATE, 1, new PotatoCrop(),
				SEED_TEXTURE_NAME);
	}
}
