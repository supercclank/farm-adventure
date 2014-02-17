package com.aa_software.farm_adventure.model.selectable.item.tool.plant;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public class AbstractPlantTool extends AbstractTool {
	protected static AbstractCrop seed;
	@Override
	public void update(Plot plot) {
		// TODO: inventory pops up and you choose the plant to use.
		// AbstractCrop crop = the selected crop.
		// plot.setCrop(crop);
		if (plot.getIrrigation() != null) {
			plot.setCrop(seed.getSeed());
		}
	}

	public void update(AbstractCrop crop) {
		// TODO: we'll have to decide if we're making a distinction between
		// produce and seeds. If so, change crop to seed.
		seed = crop;
	}
	
	public AbstractCrop getSeed(){
		return seed.getSeed();
	}
	
	public String getTextureName() {
		return "arbitrary";
	}
}
