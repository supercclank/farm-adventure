package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.seed.AbstractSeed;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.PlantTask;
import com.aa_software.farm_adventure.presenter.TextureHelper;
import com.badlogic.gdx.utils.Timer;

public abstract class AbstractPlantTool extends AbstractTool {
	protected AbstractSeed seed = null;
	protected AbstractCrop crop = null;

	public AbstractSeed getSeed() {
		return this.seed;
	}

	public void setSeed(AbstractSeed seed) {
		// TODO: we'll have to decide if we're making a distinction between
		// produce and seeds. If so, change crop to seed.
		this.seed = seed;
		this.crop = seed.getCrop();
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
			!plot.hasCrop() && plot.isUsable() && this.seed!=null && inventory.removeItem(seed)) {		
			plot.setTaskTexturePrefix(TextureHelper.getTaskTypeValue("p" + seed.getTextureName()));
			worker.setBusy(true);
			plot.setUsable(false);
			float delay = workTime * worker.getWorkRate()/(plot.getWorkStatusTextureLength() - 1);
			Timer.schedule(new PlantTask(plot, seed, worker, delay), delay);
			sounds.playClick();
			this.seed = null;
		}
	}
	
	public void update(final Plot plot) {
	}
	
	public String getItemType() {
		return "PLANT TOOLS";
	}
}
