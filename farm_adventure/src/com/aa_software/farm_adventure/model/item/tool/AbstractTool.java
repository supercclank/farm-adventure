package com.aa_software.farm_adventure.model.item.tool;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractTool extends AbstractItem {

	protected float workTime;
	protected int workerIndex;

	public float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}
	
	@Override
	public void update(Plot plot, Inventory inventory) {}

	public void setWorkerIndex(int selectedWorker) {
		this.workerIndex = selectedWorker;
	}
}
