package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.state.PlantTask;
import com.badlogic.gdx.utils.Timer;

public abstract class AbstractPlantTool extends AbstractTool {
	protected AbstractCrop seed;

	public AbstractCrop getSeed() {
		return seed;
	}

	public void setSeed(AbstractCrop crop) {
		// TODO: we'll have to decide if we're making a distinction between
		// produce and seeds. If so, change crop to seed.
		seed = crop;
	}
	
	@Override
	public void update(final Plot plot) {
		if(plot.isUsable() && plot.isIrrigated()) {
			plot.setUsable(false);
			Timer.schedule(new PlantTask(plot, seed), workTime);
		}
	}
}
