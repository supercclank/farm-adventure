package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.AbstractFarmScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Creates a new WorldScreen and displays it. The screen consists of the background map 
 * and clickable buttons which take the player to the appropriate farm.
 * @author AA Software
 *
 */
public class WorldScreen extends AbstractScreen {

	/**
	 * Constructs a WorldScreen based on the current game.
	 * @param game the current FarmAdventure class that is being played
	 */
	public WorldScreen(FarmAdventure game) {
		super(game);
	}

	/**
	 * Creates and displays the world map and buttons. Handles the on click
	 * for the buttons - starting up a new farm.
	 */
	@Override
	public void show() {
		super.show();

		// Create background and table
		Image background = new Image(new Texture(
				Gdx.files.internal("world/WorldMap.png")));
		Table table = new Table();
		background.setFillParent(true);
		// if(FarmAdventure.DEV_MODE)
		// table.debug();
		super.addActor(background);
		super.addActor(table);
		table.setFillParent(true);

		// Set up the texture for the button
		final Texture t = new Texture(
				Gdx.files.internal("world/TutorialFarm.png"));
		final int width = t.getWidth() - 2;
		final int height = t.getHeight() - 2;
		TextureRegion patch = new TextureRegion(t, 1, 1, width, height);

		// Create the style for the button
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.up = new TextureRegionDrawable(patch);
		buttonStyle.down = new TextureRegionDrawable(patch);
		buttonStyle.font = new BitmapFont();

		// Create buttons
		TextButton tutorialFarmButton = new TextButton("", buttonStyle);
		TextButton rainforestFarmButton = new TextButton("", buttonStyle);
		TextButton desertFarmButton = new TextButton("", buttonStyle);
		TextButton snowFarmButton = new TextButton("", buttonStyle);

		// Add buttons to the correct position
		super.addActor(tutorialFarmButton);
		table.add(tutorialFarmButton).size(100, 100).top().left().padTop(50)
				.padLeft(80);
		table.row();
		super.addActor(rainforestFarmButton);
		table.add(rainforestFarmButton).size(100, 100).expand().top().right()
				.padTop(350).padRight(80);
		table.row();
		super.addActor(desertFarmButton);
		table.add(desertFarmButton).size(100, 100).expand().bottom().left()
				.padBottom(250).padLeft(80);
		table.row();
		super.addActor(snowFarmButton);
		table.add(snowFarmButton).size(100, 100).expand().bottom().right()
				.padBottom(100).padRight(120);
		table.row();

		// This line of code will take the user to the farm on click or touch of
		// the tutorial farm
		tutorialFarmButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AbstractFarmScreen afs = new AbstractFarmScreen(game);
				game.setScreen(afs);
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the rainforest farm
		rainforestFarmButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AbstractFarmScreen afs = new AbstractFarmScreen(game);
				game.setScreen(afs);
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the desert farm
		desertFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AbstractFarmScreen afs = new AbstractFarmScreen(game);
				game.setScreen(afs);
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the snow farm
		snowFarmButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AbstractFarmScreen afs = new AbstractFarmScreen(game);
				game.setScreen(afs);
				return true;
			}
		});
		// Table.drawDebug(stage);
	}

}
