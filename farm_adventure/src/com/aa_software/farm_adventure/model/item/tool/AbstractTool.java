package com.aa_software.farm_adventure.model.item.tool;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.badlogic.gdx.utils.Timer;

/**
 * Represents the basis of a tool.
 * 
 * @author Bebop
 * 
 */
public abstract class AbstractTool extends AbstractItem {

	public static final Timer TIMER = new Timer();
	protected float workTime;
	protected int workerIndex;
	protected AbstractTool upgrade;
	protected AbstractTool predecessor;

	public AbstractTool(int cost, int value, String name, String description,
			float workTime, AbstractTool upgrade) {
		super(cost, value, name, description);
		this.workTime = workTime;
		this.upgrade = upgrade;
		if (upgrade != null) {
			upgrade.setPredecessor(this);
		}
	}

	public AbstractTool getPredecessor() {
		return this.predecessor;
	}

	public AbstractTool getUpgrade() {
		return this.upgrade;
	}

	public float getWorkTime() {
		return workTime;
	}

	public AbstractTool setPredecessor(AbstractTool preTool) {
		return this.predecessor = preTool;
	}

	public void setWorkerIndex(int selectedWorker) {
		this.workerIndex = selectedWorker;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}

	@Override
	public void update(Plot plot, Inventory inventory) {
	}

}
