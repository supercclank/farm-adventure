package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.item.crop.BananaCrop;

public class BananaSeed extends AbstractSeed{
	
	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;
	

	public BananaSeed(){
		super();
		this.crop = new BananaCrop();
		this.texture = "bananaCrop";
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
		this.name = "Banana Seed";
		this.description = "Plant this seed and it will produce Bananas over time.";
	}	
	
	public String getSeedName() {
		return "bananaSeed";
	}
}
