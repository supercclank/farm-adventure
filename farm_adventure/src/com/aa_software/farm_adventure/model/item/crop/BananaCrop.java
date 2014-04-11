package com.aa_software.farm_adventure.model.item.crop;

/**
 * Represents a bundle of harvested bananas.
 * 
 * @author Bebop
 *
 */
public class BananaCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "bananaCrop";
	public static final String NAME = "Banana";
	public static final String DESCRIPTION = "Bananas that you can sell in the market place";
	public static final int VALUE = 80;
	public static final int COST = 0;

	public BananaCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
