package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractScreen implements Screen {
	protected final FarmAdventure game;
	protected final Stage stage;

	protected OrthographicCamera camera;
	protected OrthogonalTiledMapRenderer renderer;
	private Skin skin;

	public static final int WIDTH = 800, HEIGHT = 480;

	public AbstractScreen(FarmAdventure game) {
		// TODO: initiate cameras and maps
		this.game = game;
		this.stage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
	}

	public void hide() {

	}

	public void show() {
		FarmAdventure.log("Showing screen: " + getName());

		// Responsible for all touch and click events
		Gdx.input.setInputProcessor(stage);
	}

	public void dispose() {

	}

	public void render(float delta) {
		// (1) process the game logic

		// update the actors
		stage.act(delta);

		// (2) draw the result
		// the following code clears the screen with the given RGB color (black)
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the actors
		stage.draw();

		// draw the table debug lines, will do nothing if not in debug mode
		Table.drawDebug(stage);
	}

	public void resize(int width, int height) {

	}

	public void pause() {

	}

	public void resume() {

	}

	/* Getters/Setters for resources */

	/**
	 * 
	 * @return the custom skin created for this game
	 */
	protected Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
			skin = new Skin(skinFile);
		}
		return skin;
	}

	/**
	 * Adds actors to the stage.
	 * 
	 * Note: If this isn't called on your screen, then nothing will be drawn to
	 * screen.
	 * 
	 * @param actor
	 *            The component that is intended to be drawn
	 */
	protected void addActor(Actor actor) {
		stage.addActor(actor);
	}

	/* Utility functions */

	protected String getName() {
		return getClass().getSimpleName();
	}
}
