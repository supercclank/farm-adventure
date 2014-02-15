package com.aa_software.farm_adventure.model;

import java.util.Random;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;
import com.aa_software.farm_adventure.model.selectable.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.IllusionistSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.BackhoeTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.item.worker.DefaultWorker;

/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private AbstractItem[] items;
	private AbstractWorker[] workers;

	public Inventory() {
		//TODO: REPLACE THIS!
		workers = new AbstractWorker[4];
		for(int i = 0; i < 4; i++)
			workers[i] = new DefaultWorker();
	}
	
	public int getWorkerCount() {
		return workers.length;
	}

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
			if (randNum == 0) {
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
