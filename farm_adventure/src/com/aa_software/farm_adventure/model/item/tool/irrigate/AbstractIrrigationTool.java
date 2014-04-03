package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.IrrigationTask;
import com.badlogic.gdx.utils.Timer;

public abstract class AbstractIrrigationTool extends AbstractTool {
	
	private Irrigation irrigationChoice;
	private TaskType task;
	
	@Override
	public void update(Plot plot, Inventory inventory) {
		final AbstractWorker worker = inventory.getFreeWorker();
		if(worker == null) {
			return;
		}
		if(plot.isUsable()) {
			worker.setBusy(true);
			plot.setUsable(false);
			plot.setTaskTexturePrefix(task);
			float delay = workTime * worker.getWorkRate() / (plot.getWorkStatusTextureLength() - 1);
			Timer.schedule(new IrrigationTask(plot, irrigationChoice, task, worker, delay), delay);
		}
	}

	public Irrigation getIrrigationChoice() {
		return irrigationChoice;
	}
	
	public TaskType getIrrTaskType() {
		return task;
	}

	public void setIrrigationChoice(Irrigation irrigationChoice) {
		this.irrigationChoice = irrigationChoice;
	}
	
	public void setTaskType(TaskType task) {
		this.task = task;
	}
	
	public String getItemType() {
		return "Irrigation Tools";
	}
}
