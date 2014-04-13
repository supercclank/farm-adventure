package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.RiceCrop;

/**
 * Represents rice seeds, which will grow into a basket of rice.
 * 
 * @author Bebop
 * 
 */
public class RiceSeed extends AbstractSeed {

	public static final int COST = 20;
	public static final int VALUE = 10;
	public static final float GROWTH_RATE = 5;
	public static final String NAME = "Rice Seed";
	public static final String DESCRIPTION = "Plant this seed and it will produce rice over time.";
	public static final String SEED_TEXTURE_NAME = "riceSeed";

	public RiceSeed() {
		super(COST, VALUE, NAME, DESCRIPTION, GROWTH_RATE, 1, new RiceCrop(),
				SEED_TEXTURE_NAME);
	}
}
