package com.aa_software.farm_adventure.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

public class Market {
	private ArrayList<AbstractItem> defaultItems = new ArrayList<AbstractItem>(Arrays.asList(new AbstractItem[]{ new DefaultWorker(),
			new ShovelTool(), new HandPlowTool(), new TrowelTool(), new ScytheTool(), new MuleTool(), new SeederTool(),
			new BackhoeTool(), new CombineTool(), new IllusionistSpell(), new LocustSwarmSpell(), new MolesSpell(), 
			new RainCallSpell(), new TimeFreezeSpell(), new BananaCrop(), new BeetCrop(), new CarrotCrop(), new RiceCrop()}));
	
	private Map<String, ArrayList<AbstractItem>> marketItems = new HashMap<String, ArrayList<AbstractItem>>();
	
	/**
	 * Create market from default items
	 */
	public Market(){
		int itemCount = this.defaultItems.size();
		for (int i=0; i<itemCount; i++){
			addItem(this.defaultItems.get(i));
		}
	}
	
	/**
	 * Create market from provided list of AbstractItems
	 * @param items
	 */
	public Market (ArrayList<AbstractItem> items){
		int itemCount = items.size();
		for (int i=0; i<itemCount; i++){
			addItem(items.get(i));
		}
	}

	/**
	 * Add AbstractItem to the market based on its type (e.g. plow tool, irrigation tool, ...)
	 * @param item
	 */
	public void addItem(AbstractItem item) {
		if (marketItems.size()==0){
			String itemType = item.getItemType();
			ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
			items.add(item);
			marketItems.put(itemType, items);
		} else {
			String itemType = item.getItemType();
			if (marketItems.containsKey(itemType)){
				marketItems.get(itemType).add(item);
			} else {
				ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
				items.add(item);
				marketItems.put(itemType, items);
			}
		}
	}
	
	/**
	 * Returns a hashMap of all items in the market (the key is the itemType)
	 * @return marketItems
	 */
	public Map<String, ArrayList<AbstractItem>> getItems() {
		return this.marketItems;
	}
	
	/**
	 * Returns the number of all items in the market
	 * @return count of all items in the market
	 */
	public int getItemsCount(){
		if (marketItems.size()==0){
			return 0;
		} else {
			int count = 0;
			Object [] keyset = marketItems.keySet().toArray();
			int countItemType = keyset.length;
			for (int i = 0; i< countItemType; i++){
				count = marketItems.get(keyset[i]).size();
			}
			return count;
		}
	}	
}
