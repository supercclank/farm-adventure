package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.Player;
import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.TutorialFarmScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen extends AbstractScreen {
	
	public static final Sounds sounds = Sounds.getInstance();
	
	public MainMenuScreen() {
		super();
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
		TextButton startGameButton = new TextButton("Start game", super.getSkin());

		// Start Music
		sounds.playMusic();
		
		// This line of code will take the user to the world screen on click or
		// touch
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new WorldScreen());
				sounds.playClick();
				return true;
			}
		});
		table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
		
		// register tutorial button
		TextButton tutorialButton = new TextButton("Tutorial", super.getSkin());
		
		// Listener to send to tutorial game
		tutorialButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new TutorialFarmScreen());
				return true;
			}
		});
		table.add(tutorialButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
		
		// register tutorial button
		TextButton optionsButton = new TextButton("Settings", super.getSkin());
		
		// Listener to send to tutorial game
		optionsButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new OptionsScreen());
				return true;
			}
		});
		table.add(optionsButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
	}

}
