package com.aa_software.farm_adventure.model.selectable.item.crop;

public class BananaCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "banana_crop";
	public static final int DEFAULT_GROWTH_TIME = 60;
	public static final int DEFAULT_OUTPUT = 500;
	public static final int DEFAULT_COST = 20;
	public static final int DEFAULT_VALUE = 10;

	public BananaCrop() {
		this.growthTime = DEFAULT_GROWTH_TIME;
		this.output = DEFAULT_OUTPUT;
		this.cost = DEFAULT_COST;
		this.value = DEFAULT_VALUE;
	}

	public BananaCrop(int growthTime, int output, int cost, int value) {
		super();
	}
}
