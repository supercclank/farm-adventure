package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen extends AbstractScreen {

	public MainMenuScreen(FarmAdventure game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		// Create table
		Table table = new Table(super.getSkin());
		table.setFillParent(true);

		super.addActor(table);

		// Add label to table
		table.add("Welcome to FarmAdventure for Android!").spaceBottom(50);
		table.row();

		// register the button "start game"
		TextButton startGameButton = new TextButton("Start game",
				super.getSkin());

		// This line of code will take the user to the world screen on click or
		// touch
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new WorldScreen(game));
				return true;
			}
		});
		table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
	}

}
