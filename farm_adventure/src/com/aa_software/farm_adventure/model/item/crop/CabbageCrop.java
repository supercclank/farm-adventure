package com.aa_software.farm_adventure.model.item.crop;

/**
 * Represents a basket of harvested cabbage.
 * 
 * @author Bebop
 * 
 */
public class CabbageCrop extends AbstractCrop {
	public static final String TEXTURE_NAME = "cabbageCrop";
	public static final String SEED_NAME = "cabbageSeed";
	public static final String NAME = "Cabbage";
	public static final int VALUE = 80;
	public static final int COST = 0;
	public static final String DESCRIPTION = "Cabbage that you can sell in the market place";

	public CabbageCrop() {
		super(COST, VALUE, NAME, DESCRIPTION, TEXTURE_NAME);
	}
}
