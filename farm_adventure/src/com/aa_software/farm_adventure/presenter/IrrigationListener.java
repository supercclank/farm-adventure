package com.aa_software.farm_adventure.presenter;

import com.badlogic.gdx.scenes.scene2d.InputListener;

public class IrrigationListener extends InputListener {
	private int x;
	private int y;
	
	public IrrigationListener(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
