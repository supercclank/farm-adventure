package com.aa_software.farm_adventure.model;

import java.util.ArrayList;
import java.util.Map;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.ShovelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plow.HandPlowTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;

public class Market {
	private ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
	private ArrayList<AbstractWorker> workers = new ArrayList<AbstractWorker>();
	
	public Market(){
		addItem(new ShovelTool());
		addItem(new HandPlowTool());
		addItem(new TrowelTool());
		addItem(new ScytheTool());	
	}

	public AbstractItem getItem(int index) {
		return this.items.get(index);
	}
	
	public AbstractWorker getWorker(int index) {
		return this.workers.get(index);
	}
	
	public ArrayList<AbstractItem> getItems(int index) {
		return this.items;
	}

	public ArrayList<AbstractWorker> getWorkers(int index) {
		return this.workers;
	}
	
	public void addItem(AbstractItem item) {
		this.items.add(item);
	}
	
	public void addWorder(AbstractWorker worker) {
		this.workers.add(worker);
	}
	
	public int getItemsCount(){
		return this.items.size();
	}
	
	public int getWorkersCount(){
		return this.workers.size();
	}
}
