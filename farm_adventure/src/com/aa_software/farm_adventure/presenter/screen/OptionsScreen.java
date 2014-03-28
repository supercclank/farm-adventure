package com.aa_software.farm_adventure.presenter.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class OptionsScreen extends AbstractScreen {

	private Slider masterVolume;
	private Slider gameVolume;
	private Slider musicVolume;
	
	public OptionsScreen() {
		super();
	}


	public void show() {
		super.show();
		
		// Create table
		Table table = new Table(super.getSkin());
		table.setFillParent(true);
		table.debug();
		
		// Register table
		super.addActor(table);
		
		// Add label to table
		table.add("Option Screen").spaceBottom(50).colspan(2);
		table.row();
		
		table.row().spaceBottom(50);
		
		//Create masterVolume label
		table.add("Master Volume: ");
		masterVolume = new Slider(0, 100, 5, false, super.getSkin());
		table.add(masterVolume);
		table.row();
		
		table.row().spaceBottom(50);
		
		//Create gameVolume label
		table.add("Game Volume: ");
		gameVolume = new Slider(0, 100, 5, false, super.getSkin());
		table.add(gameVolume);
		table.row();
		
		//Create musicVolume label
		table.add("Music Volume: ");
		musicVolume = new Slider(0, 100, 5, false, super.getSkin());
		table.add(musicVolume);
		table.row();
	}

}
