package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.crop.BananaCrop;

public class BananaSeed extends AbstractSeed {

	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;
	
	private static final String NAME = "Banana Seed";
	//TODO: Move these sort of calls into the TextureHelper. Seed doesn't need to know
	// the crop's texture name.
	private static final String CROP_TEXTURE_NAME = "bananaCrop";
	private static final String SEED_TEXTURE_NAME = "bananaSeed";
	private static final String DESCRIPTION = "Plant this seed and it will produce Bananas over time.";

	public BananaSeed() {
		super();
		this.crop = new BananaCrop();
		this.texture = CROP_TEXTURE_NAME;
		this.name = NAME;
		this.description = DESCRIPTION;
	}

	@Override
	public String getSeedName() {
		return SEED_TEXTURE_NAME;
	}
}
