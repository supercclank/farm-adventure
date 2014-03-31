package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

/*
 * Upgrades: purchasable improvements for equipment, plants, and workers.
 */
public abstract class AbstractUpgrade extends AbstractItem {

	public String getTextureName() {
		return "arbitrary";
		// TODO: change
	}

	@Override
	public void update(AbstractItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Plot plot, Inventory inventory) {}
}
