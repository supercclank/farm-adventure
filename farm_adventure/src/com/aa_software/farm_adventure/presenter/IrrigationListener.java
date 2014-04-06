package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class IrrigationListener extends InputListener {
	private final int x;
	private final int y;
	private final Irrigation irrigation;
	private final TaskType task;

	public IrrigationListener(int x, int y, Irrigation irrigation, TaskType task) {
		this.x = x;
		this.y = y;
		this.irrigation = irrigation;
		this.task = task;
	}

	public Irrigation getIrrigation() {
		return irrigation;
	}

	public TaskType getTaskType() {
		return task;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
