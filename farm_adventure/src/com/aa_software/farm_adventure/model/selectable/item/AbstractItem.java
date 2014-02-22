package com.aa_software.farm_adventure.model.selectable.item;

import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public abstract class AbstractItem implements Comparable<AbstractItem>{

	protected int cost;
	protected int value;
	protected String name;

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
	
	
	public String toString(){
		return this.name;
	}
	
	 public int compareTo(AbstractItem item) {
		 return this.name.compareTo(item.name);
	 }

	public void update(AbstractItem item) {

	}

	public void update(Plot plot) {

	}
	
	public String getToolType(){
		return "No working";
	}
}
