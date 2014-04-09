package com.aa_software.farm_adventure.model.item.tool.plow;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractPlowTool extends AbstractTool {

	@Override
	public String getItemType() {
		return "PLOW TOOLS";
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
		if (plot.isUsable() && (plot.isGrass() || plot.isUnplowed())) {
			worker.setBusy(true);
			plot.setUsable(false);
			sounds.playClick();
			Timer.schedule(
					new Task() {
						@Override
						public void run() {
							if (plot.isIrrigated()) {
								plot.setTaskTexturePrefix(TaskType.PLOW_W);
							} else {
								plot.setTaskTexturePrefix(TaskType.PLOW_UW);
							}
							if (plot.getTaskTextureIndex() == plot
									.getWorkStatusTextureLength() - 1) {
								plot.setUsable(true);
								if (plot.isIrrigated()) {
									plot.setPlotType(PlotType.PLOWEDWATERED);
								} else {
									plot.setPlotType(PlotType.PLOWEDUNWATERED);
								}
								plot.setTaskTextureIndex(0);
								worker.addExperience();
								worker.setBusy(false);
							} else {
								plot.incrementTaskTextureIndex();
								Timer.schedule(
										this,
										(workTime * worker.getWorkRate())
												/ (plot.getWorkStatusTextureLength() - 1));
							}
							worker.resetTexture();
						}
					},
					workTime * worker.getWorkRate()
							/ (plot.getWorkStatusTextureLength() - 1));
		}
	}
}
