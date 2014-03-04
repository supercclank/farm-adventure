package com.aa_software.farm_adventure.model.item;

import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractItem {

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

	abstract public void update(AbstractItem item);

	abstract public void update(Plot plot, AbstractWorker worker);
	
	abstract public String getTextureName();
	
}
