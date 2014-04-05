package com.aa_software.farm_adventure.model.item.tool.irrigate;

import java.util.ArrayList;
import java.util.Arrays;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.upgrade.CopperToolsUpgrade;
import com.aa_software.farm_adventure.model.item.upgrade.SteelToolsUpgrade;
import com.aa_software.farm_adventure.model.item.upgrade.WaterPumpUpgrade;
import com.aa_software.farm_adventure.model.item.upgrade.WindmillUpgrade;
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
		final AbstractWorker worker;
		if(workerIndex<0 || ((AbstractWorker)inventory.getItems().get("WORKERS").get(workerIndex)).isBusy()) {
			return;
		}else{
			worker = (AbstractWorker)inventory.getItems().get("WORKERS").get(workerIndex);
		}
		if(plot.isUsable() || plot.isUnplowed()) {
			System.out.println("Plot Type: "+plot.getPlotType());
			worker.setBusy(true);
			plot.setUsable(false);
			plot.setTaskTexturePrefix(task);
			float delay = workTime * worker.getWorkRate() / (plot.getWorkStatusTextureLength() - 1);
			Timer.schedule(new IrrigationTask(plot, irrigationChoice, task, worker, delay), delay);
			worker.resetTexture();
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
		return "IRRIGATION TOOLS";
	}
}
