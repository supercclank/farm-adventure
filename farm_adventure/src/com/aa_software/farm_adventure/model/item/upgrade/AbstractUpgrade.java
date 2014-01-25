package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.item.AbstractItem;

/*
 * Upgrades: purchasable improvements for equipment, plants, and workers.
 */
public abstract class AbstractUpgrade extends AbstractItem {

	abstract void applyEffect(AbstractItem item);
	
}
