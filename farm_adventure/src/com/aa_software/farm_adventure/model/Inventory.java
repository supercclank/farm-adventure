package com.aa_software.farm_adventure.model;

import java.util.Random;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.item.spell.IllusionistSpell;
import com.aa_software.farm_adventure.model.item.tool.BackhoeTool;
/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private AbstractItem[] items;
	
	public Inventory() {}
	
	public Inventory(AbstractItem[] items) {
		this.items = items;
	}

	public AbstractItem[] getItems() {
		return items;
	}

	public void setItems(AbstractItem[] items) {
		this.items = items;
	}
	
	/*
	 * Test method.
	 */
	public void randomizeInventory() {
		for (int i = 0; i < items.length; i++) {
			Random random = new Random();
			int randNum = random.nextInt(2);
			if(randNum == 0) {
				randNum = random.nextInt(3);
				if (randNum == 0) {
					items[i] = new CarrotCrop();
				} else if (randNum == 1) {
					items[i] = new BeetCrop();
				} else {
					items[i] = new BananaCrop();
				}
			} else if (randNum == 1) {
				items[i] = new BackhoeTool();
			} else {
				items[i] = new IllusionistSpell();
			}
		}
	}
}
