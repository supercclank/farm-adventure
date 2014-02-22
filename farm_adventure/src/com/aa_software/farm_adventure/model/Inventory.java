package com.aa_software.farm_adventure.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.item.worker.DefaultWorker;

/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private ArrayList<AbstractItem> items;
	private ArrayList<AbstractWorker> workers;

	/**
	 * This constructor defaults the items and workers in the inventory
	 */
	public Inventory() {
		this.workers = new ArrayList<AbstractWorker>();
		for (int i = 0; i < 4; i++){
			this.workers.add(new DefaultWorker());
		}
		this.items = new ArrayList<AbstractItem>();
	}
	
	/**
	 * This constructor is used to define the items and workers that can be 
	 * in the inventory.
	 * @param items
	 * @param workers
	 */
	public Inventory(ArrayList<AbstractItem> items, ArrayList<AbstractWorker> workers) {
		this.items = new ArrayList<AbstractItem>();
		this.workers = workers;
	}

	public ArrayList<AbstractItem> getItems() {
		return this.items;
	}
	
	public ArrayList<AbstractWorker> getWorkers() {
		return this.workers;
	}

	public void addItem(AbstractItem item) {
		this.items.add(item);
	}

	public void addWorker(AbstractWorker worker) {
		this.workers.add(worker);
	}
	
	public int getWorkerCount(){
		return this.workers.size();
	}
	
	public int getItemCount (AbstractItem item){
		return 0;
	}

}
