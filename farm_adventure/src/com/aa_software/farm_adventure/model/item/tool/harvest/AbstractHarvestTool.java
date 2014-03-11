package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractHarvestTool extends AbstractTool {
	
	@Override
	public void update(final Plot plot) {

	}
	
	public void update(final Plot plot, final Inventory inventory) {
		if(plot.isUsable() && plot.hasCrop()) {
			plot.setUsable(false);
			Timer.schedule(new Task() {
			    @Override
			    public void run() {
					plot.setUsable(true);
					inventory.addItem(plot.getCrop());
					plot.setCrop(null);
					plot.setPlotType(PlotType.UNPLOWEDWATERED);
					this.cancel();

			    }
			}, workTime);
		}
	}
	
	public String getItemType() {
		return "Harvest Tools";
	}
}
