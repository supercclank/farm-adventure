package com.aa_software.farm_adventure.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;
import com.aa_software.farm_adventure.model.selectable.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.RiceCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.IllusionistSpell;
import com.aa_software.farm_adventure.model.selectable.item.spell.LocustSwarmSpell;
import com.aa_software.farm_adventure.model.selectable.item.spell.MolesSpell;
import com.aa_software.farm_adventure.model.selectable.item.spell.RainCallSpell;
import com.aa_software.farm_adventure.model.selectable.item.spell.TimeFreezeSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.CombineTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.BackhoeTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.ShovelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.SeederTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plow.HandPlowTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plow.MuleTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.item.worker.DefaultWorker;

/*
 * Inventory: the items that the farm has on hand.
 */
public class Inventory {
	private ArrayList<AbstractItem> inventoryItems;
	private ArrayList<AbstractItem> defaultItems = new ArrayList<AbstractItem>(Arrays.asList(new AbstractItem[]{ new DefaultWorker(),
			new ShovelTool(), new HandPlowTool(), new TrowelTool(), new ScytheTool(), new MuleTool(), new SeederTool(),
			new BackhoeTool(), new CombineTool(), new IllusionistSpell(), new LocustSwarmSpell(), new MolesSpell(),
			new BackhoeTool(), new CombineTool(), new IllusionistSpell(), new LocustSwarmSpell(), new MolesSpell()}));
	
	/**
	 * This constructor defaults the items and workers in the inventory
	 */
	public Inventory() {
		this.inventoryItems = defaultItems;
	}
	
	/**
	 * This constructor is used to define the items and workers that can be 
	 * in the inventory.
	 * @param items
	 * @param workers
	 */
	public Inventory(ArrayList<AbstractItem> items) {
		this.inventoryItems = items;
	}

	public ArrayList<AbstractItem> getItems() {
		return this.inventoryItems;
	}
	
	public void addItem(AbstractItem item) {
		this.inventoryItems.add(item);
	}
	
	public int getItemCount (AbstractItem item){
		return this.inventoryItems.size();
	}

	public int getWorkerCount(){
		int workerCount = 0;
		int itemCount = this.inventoryItems.size();
		for(int i = 0; i < itemCount; i++){
			if (inventoryItems.get(i) instanceof AbstractWorker){
				workerCount++;
			}
		}
		return workerCount;
	}
}
