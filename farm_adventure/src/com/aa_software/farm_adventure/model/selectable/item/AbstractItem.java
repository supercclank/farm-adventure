package com.aa_software.farm_adventure.model.selectable.item;

import com.aa_software.farm_adventure.model.selectable.ISelectable;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public abstract class AbstractItem implements ISelectable {

	protected int cost;
	protected int value;

	public int getCost() {
		return cost;
	}

	public int getValue() {
		return value;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void update(AbstractItem item) {

	}

	public void update(Plot plot) {

	}

}
