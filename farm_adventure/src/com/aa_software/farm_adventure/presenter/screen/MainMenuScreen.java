package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.FarmScreen;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.TutorialFarmScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen extends AbstractScreen {

	public static final Sounds SOUNDS = Sounds.getInstance();
	private Skin skin;
	private static final int BUTTON_WIDTH = 300;
	private static final int BUTTON_HEIGHT = 60;
	private static final int TITLE_SPACE = 50;
	private static final int BUTTON_SPACE = 10;
	private Table table;
	private static String charityOfChoice = "SEMA Development";
	public MainMenuScreen() {
		super();
	}

	@Override
	public void show() {
		super.show();
		setupMainMenu();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		checkBackButton();
	}
	
	protected void checkBackButton(){
		if(Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);
		}
	}
	
	private void setupMainMenu(){
		skin = super.getSkin();
		// Create table
		table = new Table(skin);
		table.setFillParent(true);

		super.addActor(table);

		// Add label to table
		table.add("Welcome to FarmAdventure for Android!")
		.spaceBottom(TITLE_SPACE);
		table.row();

		// register the button "start game"
		TextButton startGameButton = new TextButton("Start game", skin);

		// Start Music
		SOUNDS.playMusic();

		// This line of code will take the user to the world screen on click or
		// touch
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				SOUNDS.playClick();
				//Cory - skipping over WorldScrene for Beta
				//FarmAdventure.getInstance().setScreen(new WorldScreen());
				//setting default next screen to a farm of temperate
				FarmAdventure.getInstance().setScreen(new SplashScreen());
				return true;
			}
		});
		table.add(startGameButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();

		// register tutorial button
		TextButton tutorialButton = new TextButton("Tutorial", skin);

		// Listener to send to tutorial game
		tutorialButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				SOUNDS.playClick();
				FarmAdventure.getInstance().setScreen(new TutorialFarmScreen());
				return true;
			}
		});
		table.add(tutorialButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();

//		TextButton optionsButton = new TextButton("Settings", skin);
//
//		optionsButton.addListener(new InputListener() {
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				SOUNDS.playClick();
//				//FarmAdventure.getInstance().setScreen(new OptionsScreen());
//				
//				return true;
//			}
//		});
//		table.add(optionsButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
//		.spaceBottom(BUTTON_SPACE);
//		table.row();
		
		TextButton aboutButton = new TextButton("Learn more about " + charityOfChoice, skin);

		aboutButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				SOUNDS.playClick();
				//FarmAdventure.getInstance().setScreen(new OptionsScreen());
				FarmAdventure.getInstance().setScreen(new AboutScreen());
				return true;
			}
		});
		table.add(aboutButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
	}

}
