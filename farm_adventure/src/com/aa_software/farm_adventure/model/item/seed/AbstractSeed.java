package com.aa_software.farm_adventure.model.item.seed;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.plot.Plot;

public class AbstractSeed extends AbstractItem{

	protected AbstractCrop crop = null;
	protected String texture = null;
	
	public AbstractCrop getCrop(){
		return crop;
	}
	
	@Override
	public String getTextureName() {
		return texture;
	}
	
	public String getItemType() {
		return "SEEDS";
	}
	
	public String getSeedName() {
		return "seed";
	}
	
	@Override
	public void update(Plot plot, Inventory inventory) {}

	@Override
	public void update(AbstractItem item) {}

}
