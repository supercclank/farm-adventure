package com.aa_software.farm_adventure.model.item.tool.plant;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.seed.AbstractSeed;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.TextureHelper;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractPlantTool extends AbstractTool {
	protected AbstractSeed seed = null;
	protected AbstractCrop crop = null;

	@Override
	public String getItemType() {
		return "PLANT TOOLS";
	}

	public AbstractSeed getSeed() {
		return this.seed;
	}

	public void setSeed(AbstractSeed seed) {
		this.seed = seed;
		this.crop = seed.getCrop();
	}

	public void update(final Plot plot) {
	}

	@Override
	public void update(final Plot plot, Inventory inventory) {
		final AbstractWorker worker;

		if (workerIndex < 0
				|| ((AbstractWorker) inventory.getItems().get("WORKERS")
						.get(workerIndex)).isBusy()) {
			return;
		} else {
			worker = (AbstractWorker) inventory.getItems().get("WORKERS")
					.get(workerIndex);
		}

		if (!plot.isGrass() && !plot.isUnplowed() && plot.isIrrigated()
				&& !plot.hasCrop() && plot.isUsable() && this.seed != null
				&& inventory.removeItem(seed)) {
			plot.setTaskTexturePrefix(TextureHelper.getTaskTypeValue("p"
					+ seed.getTextureName()));
			worker.setBusy(true);
			plot.setUsable(false);
			float delay = workTime * worker.getWorkRate()
					/ (plot.getWorkStatusTextureLength() - 1);
			Timer.schedule(new PlantTask(plot, seed, worker, delay), delay);
			sounds.playClick();
			this.seed = null;
			worker.resetTexture();
		}
	}
	
	private class PlantTask extends Task {

		private Plot plot;
		private AbstractSeed seed;
		private AbstractWorker worker;
		private float delay;

		public PlantTask(Plot plot, AbstractSeed seed, AbstractWorker worker,
				float delay) {
			this.plot = plot;
			this.seed = seed;
			this.worker = worker;
			this.delay = delay;
		}

		@Override
		public void run() {
			plot.setTaskTexturePrefix(TextureHelper.getTaskTypeValue("p"
					+ seed.getTextureName()));
			if (plot.getTaskTextureIndex() == plot.getWorkStatusTextureLength() - 1) {
				plot.setUsable(true);
				plot.setTaskTextureIndex(0);
				worker.addExperience();
				worker.setBusy(false);
			} else {
				plot.incrementTaskTextureIndex();
				Timer.schedule(this, delay);
			}
		}
	}
}


