package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.item.worker.DefaultWorker;

/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private AbstractItem[] items;
	private AbstractWorker[] workers;

	public Inventory() {
		// TODO: REPLACE THIS!
		workers = new AbstractWorker[4];
		for (int i = 0; i < 4; i++)
			workers[i] = new DefaultWorker();
	}

	public Inventory(AbstractItem[] items) {
		this.items = items;
	}

	public AbstractItem[] getItems() {
		return items;
	}

	public int getWorkerCount() {
		return workers.length;
	}

	public void setItems(AbstractItem[] items) {
		this.items = items;
	}

}
