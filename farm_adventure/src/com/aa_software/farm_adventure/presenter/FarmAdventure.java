package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.presenter.screen.farm_screen.AbstractFarmScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;

public class FarmAdventure extends Game {

	// constant useful for logging
	public static final String LOG = FarmAdventure.class.getSimpleName();

	// whether we are in development mode
	public static final boolean DEV_MODE = true;

	// a libgdx helper class that logs the current FPS each second
	private static FPSLogger fpsLogger;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		// here is where we need to render the start screen
		// setScreen(new TutorialFarmScreen());

		log("Creating game");
		fpsLogger = new FPSLogger();
		// setScreen(new MainMenuScreen(this));
		super.setScreen(new AbstractFarmScreen(this));
	}

	public void dispose() {
		super.dispose();

		log("Disposing Game");
	}

	public void render() {
		super.render();

		logFPS();
	}

	public void resize(int width, int height) {
		super.resize(width, height);

		log("Resizing game to: " + width + " x " + height);
	}

	public void pause() {
		super.pause();

		log("Pausing Game");
	}

	public void resume() {
		super.resume();

		log("Resuming Game");
	}

	public void setScreen(Screen screen) {
		super.setScreen(screen);

		log("Setting screen: " + screen.getClass().getSimpleName());
	}

	/**
	 * Whenever the game is in developer mode everything is logged in console or
	 * logcat
	 * 
	 * @param message
	 *            message to be logged
	 */
	public static void log(String message) {
		if (DEV_MODE)
			Gdx.app.log(FarmAdventure.LOG, message);
	}

	/**
	 * Logs FPS whenever in DEV_MODE
	 */
	public static void logFPS() {
		if (DEV_MODE)
			fpsLogger.log();
	}
}
