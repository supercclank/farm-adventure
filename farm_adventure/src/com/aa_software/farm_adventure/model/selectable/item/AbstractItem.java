package com.aa_software.farm_adventure.model.selectable.item;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public abstract class AbstractItem implements Comparable<AbstractItem>{

	protected int cost;
	protected int value;
	protected String name;

	/**
	 * Rerun the item's buying cost
	 * @return cost
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * Return the item's selling value
	 * @return
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the item's buying cost to the provided amount
	 * @param cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Sets the item's selling value to the provided amount 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Returns item's name as its string representation
	 */
	public String toString(){
		return this.name;
	}
	
	/**
	 * Compare items based on their name
	 */
	 public int compareTo(AbstractItem item) {
		 return this.name.compareTo(item.name);
	 }

	public void update(AbstractItem item) {

	}

	/**
	 * Changes the status of the given plot 
	 * @param plot
	 */
	public void update(Plot plot) {

	}
	
	/**
	 * Changes the status of the given plot and inventory
	 * @param plot
	 */
	public void update(Plot plot, Inventory inventory) {

	}
	
	/**
	 * Returns the itemType as a String
	 * @return
	 */
	public String getItemType(){
		return "";
	}
	
	public String getTextureName() {
		return "";
	}
}
