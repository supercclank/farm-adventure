package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class IrrigationListener extends InputListener {
	private final int x;
	private final int y;
	private final Irrigation irrigation;
	
	public IrrigationListener(int x, int y, Irrigation irrigation) {
		this.x = x;
		this.y = y;
		this.irrigation = irrigation;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public Irrigation getIrrigation() {
		return irrigation;
	}

}
