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
	
	private ArrayList<AbstractItem> defaultItems = new ArrayList<AbstractItem>(Arrays.asList(new AbstractItem[]{ new DefaultWorker(),
			new ShovelTool(), new HandPlowTool(), new TrowelTool(), new ScytheTool(), new MuleTool(), new SeederTool(),
			new BackhoeTool(), new CombineTool(), new IllusionistSpell(), new LocustSwarmSpell(), new MolesSpell(), 
			new RainCallSpell(), new TimeFreezeSpell(), new BananaCrop(), new BeetCrop(), new CarrotCrop(), new RiceCrop()}));
	
	private Map<String, ArrayList<AbstractItem>> inventoryItems = new HashMap<String, ArrayList<AbstractItem>>();
	
	/**
	 * Create inventory from default items
	 */
	public Inventory(){
		int itemCount = this.defaultItems.size();
		for (int i=0; i<itemCount; i++){
			addItem(this.defaultItems.get(i));
		}
	}
	
	/**
	 * Create inventory from provided list of AbstractItems
	 * @param items
	 */
	public Inventory (ArrayList<AbstractItem> items){
		int itemCount = items.size();
		for (int i=0; i<itemCount; i++){
			addItem(items.get(i));
		}
	}

	/**
	 * Add AbstractItem to the inventory based on its type (e.g. plow tool, irrigation tool, ...)
	 * @param item
	 */
	public void addItem(AbstractItem item) {
		if (inventoryItems.size()==0){
			String itemType = item.getItemType();
			ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
			items.add(item);
			inventoryItems.put(itemType, items);
		} else {
			String itemType = item.getItemType();
			if (inventoryItems.containsKey(itemType)){
				inventoryItems.get(itemType).add(item);
			} else {
				ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
				items.add(item);
				inventoryItems.put(itemType, items);
			}
		}
	}
	
	/**
	 * Removes item from the inventory
	 * @param item
	 * @return true if item was deleted and false if the item was not found
	 */
	public boolean removeItem(AbstractItem item) {
		if (inventoryItems.size()!=0){
			String itemType = item.getItemType();
			if (inventoryItems.containsKey(itemType)){
				int del = 0;
				int itemsCount = inventoryItems.get(itemType).size();
				for (int i = 0; i<itemsCount; i++){
					if (inventoryItems.get(itemType).get(i).compareTo(item)==0){
						inventoryItems.get(itemType).remove(i);
						del++;
					}
					if (del>0){
						System.out.println(item.toString()+" was Removed from inventory");
						return true;
					}
				}
			} 
		}
		System.out.println("You don't have more of this item to sell");
		return false;
	}
	
	/**
	 * Returns a hashMap of all items in the inventory (the key is the itemType)
	 * @return inventoryItems
	 */
	public Map<String, ArrayList<AbstractItem>> getItems() {
		return this.inventoryItems;
	}
	
	/**
	 * Returns the number of all items in the inventory
	 * @return count of all items in the inventory
	 */
	public int getItemsCount(){
		if (inventoryItems.size()==0){
			return 0;
		} else {
			int count = 0;
			Object [] keyset = inventoryItems.keySet().toArray();
			int countItemType = keyset.length;
			for (int i = 0; i< countItemType; i++){
				count = inventoryItems.get(keyset[i]).size();
			}
			return count;
		}
	}
	
	/**
	 * Searches through the inventory to get and return the count of the specific item
	 * It first searches for the item type and then iterates through the list of that 
	 * type of items to get the count 
	 * @param item
	 * @return number of items in the inventory
	 */
	public int getCount(AbstractItem item){
		if (inventoryItems.size()==0){
			return 0;
		} else {
			String itemType = item.getItemType();
			if (inventoryItems.containsKey(itemType)){
				ArrayList<AbstractItem> typeList= inventoryItems.get(itemType);
				int typeCount = typeList.size();
				int itemCount = 0;
				for (int i = 0; i<typeCount;i++){
					if (typeList.get(i).compareTo(item)==0){
						itemCount++;
					}
				}
				return itemCount;				
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Searches through the inventory to get and return the count of AbstractWorkers
	 * It first searches for the key of type "WORKERS" that returns the number 
	 * of items in the mapped list
	 * @return
	 */
	public int getWorkerCount(){
		String itemType = "WORKERS";
		if (inventoryItems.containsKey(itemType)){
			return inventoryItems.get(itemType).size();				
		} else {
			return 0;
		}
	}
}
