package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.IrrigationTask;
import com.badlogic.gdx.utils.Timer;

public abstract class AbstractIrrigationTool extends AbstractTool {
	
	private Irrigation irrigationChoice;
	
	@Override
	public void update(final Plot plot, final AbstractWorker worker) {
		if(plot.isUsable()) {
			plot.setUsable(false);
			float delay = workTime * worker.getWorkRate() / (Plot.WORK_STATUS_TEXTURES.length - 1);
			Timer.schedule(new IrrigationTask(plot, irrigationChoice, worker, delay), delay);
		}
	}

	public Irrigation getIrrigationChoice() {
		return irrigationChoice;
	}

	public void setIrrigationChoice(Irrigation irrigationChoice) {
		this.irrigationChoice = irrigationChoice;
	}
}
