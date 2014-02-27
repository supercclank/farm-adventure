package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class AbstractIrrigationTool extends AbstractTool {
	
	private Irrigation irrigationChoice;
	
	@Override
	public void update(final Plot plot) {
		if(plot.isUsable()) {
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
					plot.addIrrigation(irrigationChoice);
					this.cancel();
			    }
			}, workTime);
		}
	}

	public Irrigation getIrrigationChoice() {
		return irrigationChoice;
	}

	public void setIrrigationChoice(Irrigation irrigationChoice) {
		this.irrigationChoice = irrigationChoice;
	}
}
