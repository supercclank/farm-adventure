package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class PlantTask extends Task {

	private Plot plot;
	private AbstractCrop seed;
	private AbstractWorker worker;
	private float delay;

	public PlantTask(Plot plot, AbstractCrop seed, AbstractWorker worker,
			float delay) {
		this.plot = plot;
		this.seed = seed;
		this.worker = worker;
		this.delay = delay;
	}

	@Override
    public void run() {
		plot.setTaskTexturePrefix(0);
		if(plot.getTaskTextureIndex() == plot.getWorkStatusTextureLength() - 1) {
			plot.setUsable(true);
			plot.setCrop(seed);
			plot.setTaskTextureIndex(0);
			worker.addExperience();
			worker.setBusy(false);
		} else {
			plot.incrementTaskTextureIndex();
			Timer.schedule(this, delay);
		}
    }
}