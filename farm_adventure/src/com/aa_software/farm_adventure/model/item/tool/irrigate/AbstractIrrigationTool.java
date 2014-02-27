package com.aa_software.farm_adventure.model.item.tool.irrigate;

import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class AbstractIrrigationTool extends AbstractTool {
	
	@Override
	public void update(final Plot plot) {
		if(plot.isUsable() && !plot.isIrrigated()) {
			plot.setUsable(false);
			Timer.schedule(new Task() {
			    @Override
			    public void run() {
					plot.setUsable(true);
					switch(plot.getPlotType()) {
						case PLOWEDUNWATERED:
							plot.setPlotType(PlotType.PLOWEDWATERED);
							break;
						case UNPLOWEDUNWATERED:
							plot.setPlotType(PlotType.UNPLOWEDWATERED);
							break;
						default:
							break;
					}
					plot.setIrrigation(EnumSet.allOf(Irrigation.class));
					this.cancel();
			    }
			}, workTime);
		}
	}
}
