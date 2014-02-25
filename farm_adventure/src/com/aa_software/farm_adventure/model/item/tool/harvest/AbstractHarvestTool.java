package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class AbstractHarvestTool extends AbstractTool {
	
	@Override
	public void update(final Plot plot) {
		if(plot.isUsable() && plot.hasCrop()) {
			plot.setUsable(false);
			Timer.schedule(new Task() {
			    @Override
			    public void run() {
					plot.setUsable(true);
					// TODO: how to add the crop to inventory?
					plot.setCrop(null);
					plot.setPlotType(PlotType.UNPLOWEDWATERED);
					this.cancel();

			    }
			}, workTime);
		}
	}
}
