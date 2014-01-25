package com.aa_software.farm_adventure.model.item;

import com.aa_software.farm_adventure.model.ISelectable;
import com.aa_software.farm_adventure.model.plot.AbstractPlot;

public abstract class AbstractItem implements ISelectable {

	protected int cost;
	protected int value;
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void update(AbstractItem item) {
		
	}
	
	public void update(AbstractPlot plot) {
		
	}
	
}
