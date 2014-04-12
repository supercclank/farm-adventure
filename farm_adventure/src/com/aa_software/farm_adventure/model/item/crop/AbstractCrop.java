package com.aa_software.farm_adventure.model.item.crop;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

/**
 * Represents the basis of a crop.
 * 
 * @author Bebop
 * 
 */
public abstract class AbstractCrop extends AbstractItem {

	private String texture;
	public static final String TYPE = "CROPS";

	public AbstractCrop(int cost, int value, String name, String description,
			String texture) {
		super(cost, value, name, description);
		this.texture = texture;
	}

	@Override
	public String getItemType() {
		return TYPE;
	}

	@Override
	public String getTextureName() {
		return texture;
	}

	@Override
	public void update(AbstractItem item) {
	}

	@Override
	public void update(Plot plot, Inventory inventory) {
	}

}
