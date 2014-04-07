package com.aa_software.farm_adventure.model.item.tool.harvest;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.PlotType;
import com.aa_software.farm_adventure.presenter.utility.TextureHelper;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public abstract class AbstractHarvestTool extends AbstractTool {

	@Override
	public String getItemType() {
		return "HARVEST TOOLS";
	}

	@Override
	public void update(final Plot plot, final Inventory inventory) {
		if (plot.isUsable() && plot.hasCrop()) {
			final AbstractWorker worker;
			if (workerIndex < 0
					|| ((AbstractWorker) inventory.getItems().get("WORKERS")
							.get(workerIndex)).isBusy()) {
				return;
			} else {
				worker = (AbstractWorker) inventory.getItems().get("WORKERS")
						.get(workerIndex);
			}
			worker.setBusy(true);
			plot.setUsable(false);
			// TODO: not the best way to do this, but to get the animation for
			// removing the crops, this
			// had to happen. Need a better solution here!
			plot.setTaskTexturePrefix(TextureHelper.getTaskTypeValue("h"
					+ plot.getCrop().getTextureName()));
			inventory.addItem(plot.getCrop());
			Timer.schedule(
					new Task() {
						@Override
						public void run() {
							if (plot.getTaskTextureIndex() == plot
									.getWorkStatusTextureLength() - 1) {
								plot.setUsable(true);
								plot.setCrop(null);
								plot.setPlotType(PlotType.UNPLOWEDWATERED);
								plot.setTaskTextureIndex(0);
								worker.addExperience();
								worker.setBusy(false);
								sounds.playClick();
							} else {
								plot.incrementTaskTextureIndex();
								plot.harvestRemoveCrop(plot.getCrop());
								Timer.schedule(
										this,
										(workTime * worker.getWorkRate())
												/ (plot.getWorkStatusTextureLength() - 1));
								worker.resetTexture();
							}
						}
					},
					(workTime * worker.getWorkRate())
							/ (plot.getWorkStatusTextureLength() - 1));
		}
	}
}
