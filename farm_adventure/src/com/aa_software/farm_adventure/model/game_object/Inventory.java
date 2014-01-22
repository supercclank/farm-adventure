package com.aa_software.farm_adventure.model.game_object;

import java.util.Random;
/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private Item[] items;
	
	public Inventory() {}
	
	public Inventory(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
	
	/*
	 * Test method.
	 */
	public void randomizeInventory() {
		for(Item item : items) {
			Random random = new Random();
			int randNum = random.nextInt(2);
			if(randNum == 0) {
				randNum = random.nextInt(3);
				if (randNum == 0) {
					item = new Plant(PlantType.CARROT);
				} else if (randNum == 1) {
					item = new Plant(PlantType.POTATO);
				} else {
					item = new Plant(PlantType.CORN);
				}
			} else if (randNum == 1) {
				item = new Equipment(EquipmentType.SCYTHE);
			} else {
				item = new Spell(SpellType.ILLUSIONIST);
			}
		}
	}
}
