package com.aa_software.farm_adventure.presenter.screen;

import java.util.ArrayList;
import java.util.List;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.farm.Biome;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.FarmScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	public static final Sounds SOUNDS = Sounds.getInstance();

	public static final float WINDOW_X = (float) (STAGE_WIDTH * .32);
	public static final float WINDOW_Y = (float) (STAGE_HEIGHT * .40);

	protected float screenWidth = STAGE_WIDTH;
	protected float screenHeight = STAGE_HEIGHT;

	private Skin skin;
	protected Stage farmMenuStage;
	protected Window seasonWindow;
	protected Window confirmFarmSelectionWindow;
	protected static final Biome.Type[] BIOMES = { Biome.Type.GRASSLAND,
			Biome.Type.TROPICAL, Biome.Type.TEMPERATE, Biome.Type.BOREAL };

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
	 * Creates and displays the world map and buttons. Handles the on click for
	 * the buttons - starting up a new farm.
	 */
	@Override
	public void show() {
		super.show();
		skin = super.getSkin();
		setupWorldMap();
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		checkBackButton();
	}
	
	private void checkBackButton(){
		if(Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);
			FarmAdventure.getInstance().setScreen(new MainMenuScreen());
		}
	}

	/**
	 * Sets up the window to display the seasons for the currently selected farm
	 * and a button to start the selected farm.
	 */
	public void setupSeasonMenu(final Biome.Type biome) {

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

		seasonWindow = new Window("Season cycle for this farm", skin);
		seasonWindow.setModal(false);
		seasonWindow.setMovable(false);
		seasonWindow.setVisible(false);
		seasonWindow.setPosition(WINDOW_X, WINDOW_Y);
		seasonWindow.defaults().spaceBottom(10);
		seasonWindow.row().fill().expandX();
		
		/* Decide the season order */
		Button seasonButton;
		for (Season.Type s : Biome.getSeasons(biome)) {
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
				FarmAdventure.getInstance().setScreen(new FarmScreen(biome));
				SOUNDS.playClick();
				return true;
			}
		});
	}

	/**
	 * Sets up the window to display the confirmation screen for the currently selected farm
	 * and a button to start the selected farm.
	 */
	public void setupConfirmFarmSelectionWindow() {
		TextButton playFarmButton = new TextButton("Play Farm", skin);

		confirmFarmSelectionWindow = new Window("Continue to this farm?", skin);
		confirmFarmSelectionWindow.setModal(false);
		confirmFarmSelectionWindow.setMovable(false);
		confirmFarmSelectionWindow.setVisible(false);
		confirmFarmSelectionWindow.setPosition(WINDOW_X, WINDOW_Y);
		confirmFarmSelectionWindow.defaults().spaceBottom(10);
		confirmFarmSelectionWindow.row().fill().expandX();
		
		confirmFarmSelectionWindow.row();
		confirmFarmSelectionWindow.add(playFarmButton).colspan(4).width(200);
		confirmFarmSelectionWindow.pack();
		super.addActor(confirmFarmSelectionWindow);

		playFarmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new FarmScreen());
				SOUNDS.playClick();
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
		List<Button> farmButtons = new ArrayList<Button>();
		Button grasslandFarmButton = new Button(new Image(tutorialTex), skin);
		farmButtons.add(grasslandFarmButton);
		Button tropicalFarmButton = new Button(new Image(rainforestTex), skin);
		farmButtons.add(tropicalFarmButton);
		Button temperateFarmButton = new Button(new Image(desertTex), skin);
		farmButtons.add(temperateFarmButton);
		Button borealFarmButton = new Button(new Image(snowTex), skin);
		farmButtons.add(borealFarmButton);

		int index = 0;
		for (final Biome.Type biome : BIOMES) {
			farmButtons.get(index).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					//setupSeasonMenu(biome);
					//seasonWindow.setVisible(true);
					setupConfirmFarmSelectionWindow();
					confirmFarmSelectionWindow.setVisible(true);
					SOUNDS.playClick();
					return true;
				}
			});
			index++;
		}
		// Add buttons to the correct position
		table.add(grasslandFarmButton).size(100, 100).top().left()
				.padTop((float) (.05 * screenHeight))
				.padLeft((float) (.15 * screenWidth));
		table.row();
		table.add(tropicalFarmButton).size(100, 100).expand().top().right()
				.padTop((float) (.20 * screenHeight))
				.padRight((float) (.10 * screenWidth));
		table.row();
		table.add(temperateFarmButton).size(100, 100).expand().bottom().left()
				.padBottom((float) (.05 * screenHeight))
				.padLeft((float) (.07 * screenWidth));
		table.row();
		table.add(borealFarmButton).size(100, 100).expand().bottom().right()
				.padBottom((float) (.10 * screenHeight))
				.padRight((float) (.15 * screenWidth));
		table.row();
	}
	
	
}
