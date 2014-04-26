package com.aa_software.farm_adventure.model.item.crop;

/**
 * Represents a bundle of harvested potatoes.
 * 
 * @author Bebop
 * 
 */
public class PotatoCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "potatoCrop";
	public static final String NAME = "Potato";
	public static final String DESCRIPTION = "Potatoes that you can sell in the market place";
	public static final int VALUE = 80;
	public static final int COST = 0;

	public PotatoCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
