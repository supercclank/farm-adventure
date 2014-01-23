package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class Market {
	private AbstractItem[] items;

	public AbstractItem[] getItems() {
		return items;
	}

	public void setItems(AbstractItem[] items) {
		this.items = items;
	}
}
