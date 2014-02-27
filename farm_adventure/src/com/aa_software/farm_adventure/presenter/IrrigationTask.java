package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.badlogic.gdx.utils.Timer.Task;

public class IrrigationTask extends Task {

	private Irrigation irrigationChoice;
	private Plot plot;
	
	public IrrigationTask(Plot plot, Irrigation irrigationChoice) {
		this.irrigationChoice = irrigationChoice;
		this.plot = plot;
	}
	
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
}
