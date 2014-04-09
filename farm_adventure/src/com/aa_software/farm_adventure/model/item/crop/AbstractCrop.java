package com.aa_software.farm_adventure.model.item.crop;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractCrop extends AbstractItem {

	protected static final String TYPE = "CROPS";

	@Override
	public String getItemType() {
		return TYPE;
	}
	
	public abstract String getSeedName();

	public abstract String getTextureName();

	@Override
	public void update(AbstractItem item) {
	}

	@Override
	public void update(Plot plot, Inventory inventory) {
	}

}
