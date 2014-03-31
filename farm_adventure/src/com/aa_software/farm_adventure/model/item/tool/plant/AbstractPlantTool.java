package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.PlantTask;
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
	public void update(final Plot plot, Inventory inventory) {
		final AbstractWorker worker;
		if(workerIndex<0 || ((AbstractWorker)inventory.getItems().get("WORKERS").get(workerIndex)).isBusy()) {
			return;
		}else{
			worker = (AbstractWorker)inventory.getItems().get("WORKERS").get(workerIndex);
		}
		if(!plot.isGrass() && !plot.isUnplowed() && plot.isIrrigated() && 
			!plot.hasCrop() && plot.isUsable() && inventory.removeItem(seed)) {
			worker.setBusy(true);
			plot.setUsable(false);
			float delay = workTime * worker.getWorkRate()/(Plot.WORK_STATUS_TEXTURES.length - 1);
			Timer.schedule(new PlantTask(plot, seed, worker, delay), delay);
		}
	}
	
	public void update(final Plot plot) {
	}
	
	public String getItemType() {
		return "Plant Tools";
	}
}
