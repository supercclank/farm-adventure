package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.presenter.screen.farm_screen.TutorialFarmScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class FarmAdventure extends Game {

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
	// here is where we need to render the start screen
		setScreen(new TutorialFarmScreen());
	}
	
	public void dispose() {
		super.dispose();
	}
	
	public void render() {
		super.render();
	}
	
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	public void pause() {
		super.pause();
	}
	
	public void resume() {
		
	}

}
