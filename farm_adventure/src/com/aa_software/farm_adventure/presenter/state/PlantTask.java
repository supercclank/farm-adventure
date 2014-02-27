package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.badlogic.gdx.utils.Timer.Task;

public class PlantTask extends Task {

	private Plot plot;
	private AbstractCrop seed;
	
	public PlantTask(Plot plot, AbstractCrop seed) {
		this.plot = plot;
		this.seed = seed;
	}
	
	@Override
    public void run() {
		plot.setUsable(true);
		plot.setCrop(seed);
		this.cancel();
    }
}
