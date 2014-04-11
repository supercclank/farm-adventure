package com.aa_software.farm_adventure.model.item.crop;

/**
 * Represents a bundle of harvested carrots.
 * 
 * @author Bebop
 *
 */
public class CarrotCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "carrotCrop";
	public static final String SEED_NAME = "carrotSeed";
	public static final String NAME = "Carrot";
	public static final int VALUE = 60;
	public static final int COST = 0;
	public static final String DESCRIPTION = "Carrots that you can sell in the market place";

	public CarrotCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
