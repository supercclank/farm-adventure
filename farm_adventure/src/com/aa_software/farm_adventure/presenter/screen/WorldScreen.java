package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.farm.DesertFarm;
import com.aa_software.farm_adventure.model.farm.FarmType;
import com.aa_software.farm_adventure.model.farm.RainforestFarm;
import com.aa_software.farm_adventure.model.farm.SnowFarm;
import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.model.season.SeasonType;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.AbstractFarmScreen;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.DesertFarmScreen;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.RainforestFarmScreen;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.SnowFarmScreen;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.TutorialFarmScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Creates a new WorldScreen and displays it. The screen consists of the
 * background map and clickable buttons which take the player to the appropriate
 * farm.
 * 
 * @author AA Software
 * 
 */
public class WorldScreen extends AbstractScreen {

	/* Sound */
	public static final Sounds sounds = Sounds.getInstance();

	public static final String SKIN_JSON_UI = "skin/uiskin.json";
	public static final float WINDOW_X = (float) (STAGE_WIDTH * .32);
	public static final float WINDOW_Y = (float) (STAGE_HEIGHT * .40);

	protected float screenWidth = STAGE_WIDTH;
	protected float screenHeight = STAGE_HEIGHT;

	protected Skin skin;
	protected Stage plantMenuStage;
	protected Window seasonWindow;

	protected SeasonType[] seasons;
	protected FarmType selectedFarm;

	/**
	 * Constructs a WorldScreen based on the current game.
	 * 
	 * @param game
	 *            the current FarmAdventure class that is being played
	 */
	public WorldScreen() {
		super();
	}

	/**
	 * Sets up the window to display the seasons for the currently selected farm
	 * and a button to start the selected farm.
	 */
	public void setupSeasonMenu() {

		// Gdx.input.setInputProcessor(plantMenuStage);

		Texture spring = new Texture(Gdx.files.internal("textures/spring.png"));
		Texture summer = new Texture(Gdx.files.internal("textures/summer.png"));
		Texture fall = new Texture(Gdx.files.internal("textures/fall.png"));
		Texture winter = new Texture(Gdx.files.internal("textures/winter.png"));

		TextureRegion springImage = new TextureRegion(spring);
		TextureRegion summerImage = new TextureRegion(summer);
		TextureRegion fallImage = new TextureRegion(fall);
		TextureRegion winterImage = new TextureRegion(winter);

		TextButton playFarmButton = new TextButton("Play Farm", skin);

		seasonWindow = new Window("Seasons for this farm", skin);
		seasonWindow.setModal(false);
		seasonWindow.setMovable(false);
		seasonWindow.setVisible(false);
		seasonWindow.setPosition(WINDOW_X, WINDOW_Y);
		seasonWindow.defaults().spaceBottom(10);
		seasonWindow.row().fill().expandX();

		/* Decide the season order */
		Button seasonButton;
		for (SeasonType s : seasons) {
			switch (s) {
			case SPRING:
				seasonButton = new Button(new Image(springImage), skin);
				seasonButton.padBottom(0).padLeft(0).padRight(0).padTop(0);
				seasonWindow.add(seasonButton).size(75, 115);
				break;
			case SUMMER:
				seasonButton = new Button(new Image(summerImage), skin);
				seasonButton.padBottom(0).padLeft(0).padRight(0).padTop(0);
				seasonWindow.add(seasonButton).size(75, 115);
				break;
			case FALL:
				seasonButton = new Button(new Image(fallImage), skin);
				seasonButton.padBottom(0).padLeft(0).padRight(0).padTop(0);
				seasonWindow.add(seasonButton).size(75, 115);
				break;
			case WINTER:
				seasonButton = new Button(new Image(winterImage), skin);
				seasonButton.padBottom(0).padLeft(0).padRight(0).padTop(0);
				seasonWindow.add(seasonButton).size(75, 115);
				break;
			default:
				seasonWindow.add(new Button(new Image(springImage), skin))
						.size(75, 115);
				break;
			}
		}
		seasonWindow.row();
		seasonWindow.add(playFarmButton).colspan(4).width(200);
		seasonWindow.pack();
		super.addActor(seasonWindow);

		playFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AbstractFarmScreen farmScreen;
				switch (selectedFarm) {
				case TUTORIAL:
					farmScreen = new TutorialFarmScreen();
					break;
				case RAINFOREST:
					farmScreen = new RainforestFarmScreen();
					break;
				case DESERT:
					farmScreen = new DesertFarmScreen();
					break;
				case SNOW:
					farmScreen = new SnowFarmScreen();
					break;
				default:
					farmScreen = new TutorialFarmScreen();
					break;
				}
				FarmAdventure.getInstance().setScreen(farmScreen);
				sounds.playClick();
				return true;
			}
		});
	}

	/**
	 * Sets up the world map screen with a background map and farm buttons. When
	 * a farm button is selected, the type of farm selected is saved, and
	 * setupSeasonMenu is called.
	 */
	public void setupWorldMap() {
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
		final Texture tutorialTex = new Texture(
				Gdx.files.internal("world/TutorialFarm.png"));
		final Texture rainforestTex = new Texture(
				Gdx.files.internal("world/RainforestFarm.png"));
		final Texture desertTex = new Texture(
				Gdx.files.internal("world/DesertFarm.png"));
		final Texture snowTex = new Texture(
				Gdx.files.internal("world/SnowFarm.png"));

		// Create buttons
		Button tutorialFarmButton = new Button(new Image(tutorialTex), skin);
		Button rainforestFarmButton = new Button(new Image(rainforestTex), skin);
		Button desertFarmButton = new Button(new Image(desertTex), skin);
		Button snowFarmButton = new Button(new Image(snowTex), skin);

		// Add buttons to the correct position
		super.addActor(tutorialFarmButton);
		table.add(tutorialFarmButton).size(100, 100).top().left()
				.padTop((float) (.05 * screenHeight))
				.padLeft((float) (.15 * screenWidth));
		table.row();
		super.addActor(rainforestFarmButton);
		table.add(rainforestFarmButton).size(100, 100).expand().top().right()
				.padTop((float) (.20 * screenHeight))
				.padRight((float) (.10 * screenWidth));
		table.row();
		super.addActor(desertFarmButton);
		table.add(desertFarmButton).size(100, 100).expand().bottom().left()
				.padBottom((float) (.05 * screenHeight))
				.padLeft((float) (.07 * screenWidth));
		table.row();
		super.addActor(snowFarmButton);
		table.add(snowFarmButton).size(100, 100).expand().bottom().right()
				.padBottom((float) (.10 * screenHeight))
				.padRight((float) (.15 * screenWidth));
		table.row();

		// This line of code will take the user to the farm on click or touch of
		// the tutorial farm
		tutorialFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				seasons = TutorialFarm.DEFAULT_SEASONS;
				selectedFarm = FarmType.TUTORIAL;
				setupSeasonMenu();
				seasonWindow.setVisible(true);
				sounds.playClick();
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the rainforest farm
		rainforestFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				seasons = RainforestFarm.DEFAULT_SEASONS;
				selectedFarm = FarmType.RAINFOREST;
				setupSeasonMenu();
				seasonWindow.setVisible(true);
				sounds.playClick();
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the desert farm
		desertFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				seasons = DesertFarm.DEFAULT_SEASONS;
				selectedFarm = FarmType.DESERT;
				setupSeasonMenu();
				seasonWindow.setVisible(true);
				sounds.playClick();
				return true;
			}
		});

		// This line of code will take the user to the farm on click or touch of
		// the snow farm
		snowFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				seasons = SnowFarm.DEFAULT_SEASONS;
				selectedFarm = FarmType.SNOW;
				setupSeasonMenu();
				seasonWindow.setVisible(true);
				sounds.playClick();
				return true;
			}
		});
		// Table.drawDebug(statusBarStage);
	}

	/**
	 * Creates and displays the world map and buttons. Handles the on click for
	 * the buttons - starting up a new farm.
	 */
	@Override
	public void show() {
		super.show();
		skin = new Skin(Gdx.files.internal(SKIN_JSON_UI));
		setupWorldMap();
	}

}
