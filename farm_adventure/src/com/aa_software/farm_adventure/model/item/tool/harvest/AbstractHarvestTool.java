package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractHarvestTool extends AbstractTool {
	
	@Override
	public void update(final Plot plot, final AbstractWorker worker) {
		if(plot.isUsable() && plot.hasCrop()) {
			plot.setUsable(false);
			Timer.schedule(new Task() {
			    @Override
			    public void run() {
					if(plot.getTaskTextureIndex() == Plot.WORK_STATUS_TEXTURES.length - 1) {
						plot.setUsable(true);
						// TODO: how to add the crop to inventory?
						plot.setCrop(null);
						plot.setPlotType(PlotType.UNPLOWEDWATERED);
						plot.setTaskTextureIndex(0);
						worker.addExperience();
						worker.setBusy(false);
					} else {
						plot.incrementTaskTextureIndex();
						Timer.schedule(this, (workTime * worker.getWorkRate())/(Plot.WORK_STATUS_TEXTURES.length-1));
					}
			    }
			}, (workTime * worker.getWorkRate())/(Plot.WORK_STATUS_TEXTURES.length - 1));
		}
	}
}
