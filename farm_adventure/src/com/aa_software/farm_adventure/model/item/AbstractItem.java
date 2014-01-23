package com.aa_software.farm_adventure.model.item;

public abstract class AbstractItem {

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
	
}
