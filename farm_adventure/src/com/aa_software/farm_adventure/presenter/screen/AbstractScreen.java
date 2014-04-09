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

	public static final int STAGE_WIDTH =480;
	public static final int STAGE_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 1024;

	protected final Stage mainStage;

	protected OrthographicCamera camera;
	protected OrthogonalTiledMapRenderer renderer;
	private Skin skin;

	public AbstractScreen() {
		// TODO: initiate cameras and maps

		this.mainStage = new Stage(STAGE_WIDTH,
				STAGE_HEIGHT, true);
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
		mainStage.addActor(actor);
	}

	@Override
	public void dispose() {
		mainStage.dispose();
		renderer.dispose();
		Gdx.app.exit();
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

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

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainStage.act(delta);
		mainStage.draw();

		Table.drawDebug(mainStage);
	}

	/* Getters/Setters for resources */

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	/* Utility functions */

	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		FarmAdventure.log("Showing screen: " + getName());

		Gdx.input.setInputProcessor(mainStage);
	}
}
