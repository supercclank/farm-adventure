package com.aa_software.farm_adventure.model.item.crop;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractCrop extends AbstractItem {

	@Override
	public String getItemType() {
		return "CROPS";
	}

	public String getSeedName() {
		return "crops";
	}

	@Override
	public String getTextureName() {
		return "texture";
	}

	@Override
	public void update(AbstractItem item) {};

	@Override
	public void update(Plot plot, Inventory inventory) {};
}
