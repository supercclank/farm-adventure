package com.aa_software.farm_adventure.presenter;

import com.aa_software.farm_adventure.presenter.screen.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GLTexture;

public class FarmAdventure extends Game {

	private static FarmAdventure Instance = null;
	
	// constant useful for logging
	public static final String LOG = FarmAdventure.class.getSimpleName();

	// whether we are in development mode
	public static final boolean DEV_MODE = true;

	// a libgdx helper class that logs the current FPS each second
	private static FPSLogger FpsLogger;
	
	protected FarmAdventure() {}

	public static FarmAdventure getInstance() {
		if (Instance == null) {
			Instance = new FarmAdventure();
		}
		return Instance;
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
			Gdx.app.log(FarmAdventure.class.getSimpleName(), message);
	}

	/**
	 * Logs FPS whenever in DEV_MODE
	 */
	public static void logFPS() {
		if (DEV_MODE)
			FpsLogger.log();
	}

	@Override
	public void create() {
		GLTexture.setEnforcePotImages(false);
		// here is where we need to render the start screen
		// setScreen(new TutorialFarmScreen());

		log("Creating game");
		FpsLogger = new FPSLogger();
		// setScreen(new MainMenuScreen(this));
		MainMenuScreen mms = new MainMenuScreen();
		super.setScreen(mms);
	}

	@Override
	public void dispose() {
		super.dispose();

		log("Disposing Game");
	}

	@Override
	public void pause() {
		super.pause();

		log("Pausing Game");
	}

	@Override
	public void render() {
		super.render();

		logFPS();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		log("Resizing game to: " + width + " x " + height);
	}

	@Override
	public void resume() {
		super.resume();

		log("Resuming Game");
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);

		log("Setting screen: " + screen.getClass().getSimpleName());
	}
}
