package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.crop.BeetCrop;

public class BeetSeed extends AbstractSeed {

	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;

	public BeetSeed() {
		super();
		this.texture = "beetCrop";
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = "Beet Seed";
		this.description = "Plant this seed and it will produce Beets over time.";
	}

	public AbstractCrop getCrop() {
		return new BeetCrop();
	}
	
	@Override
	public String getSeedName() {
		return "beetSeed";
	}
}
