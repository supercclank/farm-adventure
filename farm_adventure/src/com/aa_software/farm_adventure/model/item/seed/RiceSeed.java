package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.crop.RiceCrop;

public class RiceSeed extends AbstractSeed {

	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;

	public RiceSeed() {
		super();
		this.texture = "riceCrop";
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = "Rice Seed";
		this.description = "Plant this seed and it will produce rice over time.";
	}
	
	public AbstractCrop getCrop() {
		return new RiceCrop();
	}

	@Override
	public String getSeedName() {
		return "riceSeed";
	}
}
