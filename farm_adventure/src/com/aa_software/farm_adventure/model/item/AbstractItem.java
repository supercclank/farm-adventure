package com.aa_software.farm_adventure.model.item;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractItem implements Comparable<AbstractItem>{

	/* Sound */
	public static final Sounds sounds = Sounds.getInstance();
	protected int cost;
	protected int value;
	protected String name = "N/A";
	protected String description = "No description available";

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
		 return this.name.compareTo(item.getName());
	 }
	
	/**
	 * Changes the status of the given plot and inventory
	 * @param plot
	 */
	 abstract public void update(Plot plot, Inventory inventory);
	
	/**
	 * Returns the itemType as a String
	 * @return
	 */
	public String getItemType(){
		return "";
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	abstract public void update(AbstractItem item);
	abstract public String getTextureName();
}
