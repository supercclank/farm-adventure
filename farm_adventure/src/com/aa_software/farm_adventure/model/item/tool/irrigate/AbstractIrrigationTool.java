package com.aa_software.farm_adventure.model.item.tool.irrigate;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractIrrigationTool extends AbstractTool {

	private class IrrigationTask extends Task {

		private Irrigation irrigationChoice;
		private TaskType task;
		private Plot plot;
		private AbstractWorker worker;
		private float delay;

		public IrrigationTask(Plot plot, Irrigation irrigationChoice,
				TaskType task, AbstractWorker worker, float delay) {
			this.irrigationChoice = irrigationChoice;
			this.task = task;
			this.plot = plot;
			this.worker = worker;
			this.delay = delay;
		}

		@Override
		public void run() {
			plot.setTaskTexturePrefix(task);
			if (plot.getTaskTextureIndex() == plot.getWorkStatusTextureLength() - 1) {
				plot.setUsable(true);
				switch (plot.getPlotType()) {
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
				plot.setTaskTextureIndex(0);
				worker.addExperience();
				worker.setBusy(false);
			} else {
				plot.incrementTaskTextureIndex();
				Timer.schedule(this, delay);
			}
		}
	}
	
	private Irrigation irrigationChoice;

	private TaskType task;

	public Irrigation getIrrigationChoice() {
		return irrigationChoice;
	}

	public TaskType getIrrTaskType() {
		return task;
	}

	@Override
	public String getItemType() {
		return "IRRIGATION TOOLS";
	}

	public void setIrrigationChoice(Irrigation irrigationChoice) {
		this.irrigationChoice = irrigationChoice;
	}

	public void setTaskType(TaskType task) {
		this.task = task;
	}
	
	@Override
	public void update(final Plot plot, final Inventory inventory) {
		final AbstractWorker worker;
		if (workerIndex < 0
				|| ((AbstractWorker) inventory.getItems().get("WORKERS")
						.get(workerIndex)).isBusy()) {
			return;
		} else {
			worker = (AbstractWorker) inventory.getItems().get("WORKERS")
					.get(workerIndex);
		}
		if (plot.isUsable()) {
			worker.setBusy(true);
			plot.setUsable(false);
			plot.setTaskTexturePrefix(task);
			float delay = workTime * worker.getWorkRate()
					/ (plot.getWorkStatusTextureLength() - 1);
			Timer.schedule(new IrrigationTask(plot, irrigationChoice, task,
					worker, delay), delay);
			worker.resetTexture();
		}
	}
	
}
