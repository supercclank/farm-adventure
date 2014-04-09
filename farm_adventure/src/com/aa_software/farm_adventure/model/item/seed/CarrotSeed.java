package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;

public class CarrotSeed extends AbstractSeed {

	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;

	public CarrotSeed() {
		super();
		this.crop = new CarrotCrop();
		this.texture = "carrotCrop";
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = "Carrot Seed";
		this.description = "Plant this seed and it will produce Carrots over time.";
	}

	@Override
	public AbstractCrop getCrop() {
		return new CarrotCrop();
	}

	@Override
	public String getSeedName() {
		return "carrotSeed";
	}
}
