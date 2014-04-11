package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.BeetCrop;

/**
 * Represents beet seeds, which will grow into a bundle of beets.
 * 
 * @author Bebop
 *
 */
public class BeetSeed extends AbstractSeed {

	public static final int COST = 20;
	public static final int VALUE = 10;
	public static final int GROWTH_RATE = 5;
	public static final String NAME = "Beet Seed";
	public static final String DESCRIPTION = "Plant this seed and it will produce beets over time.";
	public static final String SEED_TEXTURE_NAME = "beetSeed";

	public BeetSeed() {
		super(COST, VALUE, NAME, DESCRIPTION, GROWTH_RATE, 1, new BeetCrop(),
				SEED_TEXTURE_NAME);
	}
}
