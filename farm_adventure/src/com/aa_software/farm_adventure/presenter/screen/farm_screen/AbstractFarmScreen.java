package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.EnumSet;

import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.Player;
import com.aa_software.farm_adventure.model.ToolBar;
import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.farm.AbstractFarm;
import com.aa_software.farm_adventure.model.farm.SnowFarm;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.item.crop.RiceCrop;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.tool.irrigate.AbstractIrrigationTool;
import com.aa_software.farm_adventure.model.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.plot.Irrigation;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.IrrigationListener;
import com.aa_software.farm_adventure.presenter.TextureHelper;
import com.aa_software.farm_adventure.presenter.screen.AbstractScreen;
import com.aa_software.farm_adventure.presenter.screen.MainMenuScreen;
import com.aa_software.farm_adventure.presenter.state.DefaultSelectionState;
import com.aa_software.farm_adventure.presenter.state.ISelectionState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class AbstractFarmScreen extends AbstractScreen {

	/* Game */
	public static final long GAME_TIME_MILLIS = 120000;
	long gameStartTime;

	/* Player */
	public static final Player PLAYER = Player.getInstance();
	
	/* Sound */
	public static final Sounds sounds = Sounds.getInstance();

	/* Tile */
	public static final String TILE_MAP_NAME = "tilemap/tileMap128.tmx";
	public static final String TILE_SET_NAME = "tileSet128";
	private static final int TILE_SIZE = 128;

	/* Skin */
	public static final String SKIN_JSON_UI = "skin/uiskin.json";

	/* Layer */
	/*
	 * For each layer, please provide a method of syncing the model with the
	 * presenter
	 */
	private final String[] allLayers = { "ground", "water", "plant", "tool",
			"seed", "status", "select", "transparent", "task" };
	public static final int PLANT_TOOL_X = 2, PLANT_TOOL_Y = 0,
			IRRIGATION_TOOL_X = 1, IRRIGATION_TOOL_Y = 0, STATUS_BAR_Y = 1,
			FIELD_STARTING_Y = 2;

	/* Stage */
	public static final float FONT_SCALE = 1;
	public static final float BANK_LABEL_X = (float) (Gdx.graphics.getWidth() * .03),
			BANK_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18),
			TIME_LABEL_X = (float) (Gdx.graphics.getWidth() * .38),
			TIME_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18),
			WORKER_LABEL_X = (float) (Gdx.graphics.getWidth() * .78),
			WORKER_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18),
			WINDOW_X = (float) (Gdx.graphics.getWidth() * .25),
			WINDOW_Y = (float) (Gdx.graphics.getHeight() * .13),
			INVENTORY_HEIGHT = Gdx.graphics.getHeight() - 15 * (TILE_SIZE / 10);

	/* Font setup */
	BitmapFont fontType = new BitmapFont();
	LabelStyle style1 = new LabelStyle(fontType, Color.BLACK);

	protected AbstractItem selection;
	protected ISelectionState state;
	protected AbstractFarm farm;

	protected TiledMap map;
	protected TiledMapTileSet tileSet;
	protected HashMap<String, Integer> tileMap;

	protected InputMultiplexer inputMultiplexer;

	protected Skin skin;
	protected Stage plantMenuStage;
	protected Window plantWindow;

	protected Skin inventorySkin;
	protected Stage inventoryStage;
	protected Window inventoryWindow;
	protected Table inventoryScrollTable;

	protected final Stage irrigationMenuStage;
	protected final Stage statusBarStage;
	protected final Window irrigationWindow;

	protected boolean irrigationMenuClicksDisabled;
	protected boolean plantMenuClicksDisabled;
	protected boolean toolBarClicksDisabled;
	protected boolean fieldClicksDisabled;
	protected boolean inventoryClicksDisabled;
	protected boolean disableGameTime;

	protected boolean gameOver;

	protected enum Actions {
		BUY, SELL
	}

	public AbstractFarmScreen() {
		gameOver = false;
		disableGameTime = false;
		irrigationMenuClicksDisabled = false;
		plantMenuClicksDisabled = false;
		toolBarClicksDisabled = false;
		fieldClicksDisabled = false;
		inventoryClicksDisabled = false;

		this.selection = null;
		this.state = new DefaultSelectionState();

		map = new TmxMapLoader().load(TILE_MAP_NAME);
		tileSet = map.getTileSets().getTileSet(TILE_SET_NAME);
		/* Push all of the tiles for each layer into the tile map */
		Iterator<TiledMapTile> tileIterator;
		tileMap = new HashMap<String, Integer>();
		for (int i = 0; i < allLayers.length; i++) {
			for (tileIterator = tileSet.iterator(); tileIterator.hasNext();) {
				TiledMapTile tile = tileIterator.next();
				tileMap.put(tile.getProperties()
						.get(allLayers[i], String.class), tile.getId());
			}
		}

		skin = new Skin(Gdx.files.internal(SKIN_JSON_UI));
		plantMenuStage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		irrigationMenuStage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		statusBarStage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		inventoryStage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);

		irrigationWindow = new Window("Pick a Side to Irrigate", skin);
		irrigationWindow.setModal(false);
		irrigationWindow.setMovable(false);
		irrigationWindow.setVisible(false);
		irrigationWindow.setPosition(WINDOW_X, WINDOW_Y);
		irrigationWindow.defaults().spaceBottom(10);
		irrigationWindow.row().fill().expandX();

		irrigationMenuStage.addActor(irrigationWindow);

		plantWindow = new Window("Pick a Type of Seed", skin);
		plantWindow.setModal(false);
		plantWindow.setMovable(false);
		plantWindow.setVisible(false);
		plantWindow.setPosition(WINDOW_X, WINDOW_Y);
		plantWindow.defaults().spaceBottom(10);
		plantWindow.row().fill().expandX();

		plantMenuStage.addActor(plantWindow);
		
		inputMultiplexer = new InputMultiplexer();
		inventoryScrollTable = new Table();
		setupInventoryWindow();
	}

	/**
	 * Uses the libgdx library to get the x, y location of a users touch if
	 * there was one. This value is used to check if the user clicked over the
	 * plots or over the tool bar. If the user clicks the status bar, there is
	 * no change. If it is a plot, the x , y , and ground string are used to
	 * update the state. If the tool bar was clicked, the x, y, and tool bar
	 * string are used to update the state.
	 */
	public void checkTouch() {
		if (Gdx.input.justTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			int xCell = (int) (touchPos.x / TILE_SIZE);
			int yCell = (int) (touchPos.y / TILE_SIZE);
			updateState(xCell, yCell);
		}
	}

	/**
	 * This method disposes of our left over libGDX elements, updates the
	 * player's score, and returns the player to the main menu.
	 */
	@Override
	public void dispose() {
		// TODO: score evaluation for player bankroll
		PLAYER.setBankroll(PLAYER.getBankroll() + 100);
		map.dispose();
		renderer.dispose();
		FarmAdventure.getInstance().setScreen(new MainMenuScreen());
	}

	/**
	 * This method acts as our "game loop". Checks for touches (so that they can
	 * be handled), syncs each layer's tiles with their respective model pieces,
	 * and finally updates and draws the stage for the status bar. However, if
	 * the game is over, this method will call for a disposal of the screen.
	 * 
	 */
	@Override
	public void render(float delta) {
		if (gameOver) {
			dispose();
		} else {
			/* Draw the base map to the screen */
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			fontType.setScale(FONT_SCALE);

			checkTouch();
			farm.getField().syncAllIrrigation();
			syncWaterTiles();
			syncPlantTiles();
			syncGroundTiles();
			syncToolTiles();
			syncStatusTiles();
			syncSeedTile();
			syncTaskTiles();
			camera.update();
			renderer.setView(camera);
			renderer.render();

			updateStatusBar();
			statusBarStage.draw();
			plantMenuStage.draw();
			inventoryStage.draw();
			inventoryStage.act();

			irrigationMenuStage.draw();

		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		plantMenuStage.setViewport(width, height);
		irrigationMenuStage.setViewport(width, height);
	}
	
	public void disableAllGameClicks() {
		fieldClicksDisabled = true;
		toolBarClicksDisabled = true;
		irrigationMenuClicksDisabled = true;
		plantMenuClicksDisabled = true;
		inventoryClicksDisabled = true;
	}

	/**
	 * Sets up the window to choose a seed to plant
	 */
	public void updatePlantWindow() {
		plantWindow.clear();
		boolean inventoryHasCarrot = true;
		boolean inventoryHasBeet = true;
		boolean inventoryHasRice = true;
		boolean inventoryHasBanana = true;

		if (inventoryHasCarrot) {
			Texture carrot = new Texture(
					Gdx.files.internal("textures/carrotCrop.png"));
			TextureRegion carrotImage = new TextureRegion(carrot);
			Button carrotButton = new Button(new Image(carrotImage), skin);
			plantWindow.add(carrotButton);
			carrotButton.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					plantWindow.setVisible(false);
					((AbstractPlantTool) farm.getTool(PLANT_TOOL_X,
							PLANT_TOOL_Y)).setSeed(new CarrotCrop());
					sounds.playClick();
					return true;
				}
			});
		}

		if (inventoryHasBeet) {
			Texture beet = new Texture(Gdx.files.internal("textures/beetCrop.png"));
			TextureRegion beetImage = new TextureRegion(beet);
			Button beetButton = new Button(new Image(beetImage), skin);
			plantWindow.add(beetButton);
			beetButton.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					plantWindow.setVisible(false);
					((AbstractPlantTool) farm.getTool(PLANT_TOOL_X,
							PLANT_TOOL_Y)).setSeed(new BeetCrop());
					sounds.playClick();
					return true;
				}
			});
		}

		if (inventoryHasRice) {
			Texture rice = new Texture(Gdx.files.internal("textures/riceCrop.png"));
			TextureRegion riceImage = new TextureRegion(rice);
			Button riceButton = new Button(new Image(riceImage), skin);
			plantWindow.add(riceButton);
			riceButton.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					plantWindow.setVisible(false);
					((AbstractPlantTool) farm.getTool(PLANT_TOOL_X,
							PLANT_TOOL_Y)).setSeed(new RiceCrop());
					sounds.playClick();
					return true;
				}
			});
		}

		if (inventoryHasBanana) {
			Texture banana = new Texture(
					Gdx.files.internal("textures/bananaCrop.png"));
			TextureRegion bananaImage = new TextureRegion(banana);
			Button bananaButton = new Button(new Image(bananaImage), skin);
			plantWindow.add(bananaButton);
			bananaButton.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					plantWindow.setVisible(false);
					((AbstractPlantTool) farm.getTool(PLANT_TOOL_X,
							PLANT_TOOL_Y)).setSeed(new BananaCrop());
					sounds.playClick();
					return true;
				}
			});
		}

		plantWindow.pack();
	}

	@Override
	public void show() {
		super.show();
		renderer = new OrthogonalTiledMapRenderer(map);

		gameStartTime = System.currentTimeMillis();

		this.selection = null;
		this.state = new DefaultSelectionState();

		/* Push all of the tiles for each layer into the tile map */
		Iterator<TiledMapTile> tileIterator;
		tileMap = new HashMap<String, Integer>();
		for (int i = 0; i < allLayers.length; i++) {
			for (tileIterator = tileSet.iterator(); tileIterator.hasNext();) {
				TiledMapTile tile = tileIterator.next();
				tileMap.put(tile.getProperties()
						.get(allLayers[i], String.class), tile.getId());
			}
		}

		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	/**
	 * Syncs each cell's ground tile with the field.
	 * 
	 */
	public void syncGroundTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"ground");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				/* Get the cell we wish to update, converting field Y to cell Y */
				Cell cell = layer.getCell(x, y + FIELD_STARTING_Y);
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				/* Get the ground texture for our cell to use */
				tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y)
						.getTextureName()));
				cell.setTile(tile);
			}
		}
	}

	/**
	 * Syncs each cell's ground tile with the field.
	 * 
	 */
	public void syncTaskTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"task");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				/* Get the cell we wish to update, converting field Y to cell Y */
				Cell cell = layer.getCell(x, y + FIELD_STARTING_Y);
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				/* Get the ground texture for our cell to use */
				if (farm.getPlot(x, y).getTaskTextureName() != null) {
					tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y)
							.getTaskTextureName()));
				}
				cell.setTile(tile);
			}
		}
	}

	/**
	 * Syncs each cell's plant tile with the field.
	 * 
	 */
	public void syncPlantTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"plant");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				/* Get the cell we wish to update */
				Cell cell = layer.getCell(x, y + FIELD_STARTING_Y);
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				/*
				 * If there is a plant on the plot, get its texture for our cell
				 * to use
				 */
				if (farm.getPlot(x, y).getCrop() != null) {
					tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y)
							.getCrop().getTextureName()));
				}
				cell.setTile(tile);
			}
		}
	}

	/**
	 * Syncs the tool bar cell in the tool bar to match the current seed
	 */
	public void syncSeedTile() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"seed");
		Cell cell = layer.getCell(PLANT_TOOL_X, PLANT_TOOL_Y);
		TiledMapTile tile = tileSet.getTile(tileMap
				.get(((AbstractPlantTool) farm.getTool(PLANT_TOOL_X,
						PLANT_TOOL_Y)).getSeed().getSeedName()));
		cell.setTile(tile);
	}

	/**
	 * Syncs the player's tool bar selection. Gives the selected position the
	 * "select" tile and gives everything else a transparent tile.
	 * 
	 * @param x
	 *            the x-coordinate of the cell that the player clicked.
	 */
	public void syncSelectTiles(int x) {
		TiledMapTileLayer selected = (TiledMapTileLayer) map.getLayers().get(
				"select");
		TiledMapTile tile;

		for (int i = 0; i < selected.getWidth(); i++) {
			if (x == i) {
				tile = tileSet.getTile(tileMap.get("select"));
			} else {
				tile = tileSet.getTile(tileMap.get("transparent"));
			}
			selected.getCell(i, 0).setTile(tile);
		}
	}

	/**
	 * Syncs each cell in the status bar to match the current season
	 */
	public void syncStatusTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"status");
		for (int x = 0; x < Field.COLUMNS; x++) {
			Cell cell = layer.getCell(x, STATUS_BAR_Y);
			TiledMapTile tile = tileSet.getTile(tileMap.get(farm
					.getCurrentSeason().getSeasonType().toString()
					.toLowerCase()
					+ "" + x));
			cell.setTile(tile);
		}
	}

	/**
	 * Syncs each cell's tool tile with the tool bar.
	 * 
	 */
	public void syncToolTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"tool");
		for (int y = 0; y < ToolBar.ROWS; y++) {
			for (int x = 0; x < ToolBar.COLUMNS; x++) {
				/* Get the cell we wish to update */
				Cell cell = layer.getCell(x, y);
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				/* Get the ground texture for our cell to use */
				if (x != ToolBar.COLUMNS - 1) {
					tile = tileSet.getTile(tileMap.get(farm.getTool(x, y)
							.getTextureName()));
				} else { // TODO: we're hard coding the market texture here
							// because there is no tool to .getTextureName()
							// from.
					tile = tileSet.getTile(tileMap.get("market"));
				}
				cell.setTile(tile);
			}
		}
	}

	/**
	 * Syncs each cell's water tile with the field.
	 * 
	 */
	public void syncWaterTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"water");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				Cell cell = layer.getCell(x, y + FIELD_STARTING_Y);
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				if (!farm.getPlot(x, y).getIrrigation().isEmpty()) {
					tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y)
							.getIrrigationTextureName()));
				}
				cell.setTile(tile);
			}
		}
	}

	/**
	 * Updates the window for selecting irrigation.
	 * 
	 * @param x
	 *            the x coordinate of the selected plot
	 * @param y
	 *            the y coordinate of the selected plot
	 */
	public void updateIrrigationWindow(final int x, final int y) {

		irrigationWindow.clear();

		Iterator<Irrigation> iterator = farm.getIrrigationChoices(x, y)
				.iterator();
		for (; iterator.hasNext();) {
			Irrigation irrigation = iterator.next();
			Texture irrigationTexture = new Texture(
					Gdx.files.internal(TextureHelper
							.getIrrigationTextureFileName(EnumSet
									.of(irrigation))));
			TextureRegion irrigationImage = new TextureRegion(irrigationTexture);
			Button irrigationButton = new Button(new Image(irrigationImage),
					skin);
			/*
			 * creates an input listener that additionally has the fields for
			 * the selected X and Y. This way, when the listener is called, it
			 * will know which X and Y it pertains to.
			 */
			irrigationButton.addListener(new IrrigationListener(x, y,
					irrigation) {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					if (selection instanceof AbstractIrrigationTool) {
						((AbstractIrrigationTool) selection)
								.setIrrigationChoice(this.getIrrigation());
						state = state.update(
								farm.getPlot(this.getX(), this.getY()),
								farm.getInventory());
						sounds.playClick();
					}
					irrigationWindow.setVisible(false);
					return true;
				}
			});
			irrigationWindow.add(irrigationButton);
		}

		irrigationWindow.pack();
	}

	/**
	 * Takes in an x, and y value (cell-based) that represents user input, as
	 * well as the type of cell that was clicked. The options for this is ground
	 * or tool bar. If the user clicked on the tool bar, that tool is selected
	 * and the state updates. If a plot is clicked, the tool bar that was
	 * previously selected will affect the status of the plot and the state will
	 * update.
	 * 
	 * @param x
	 * @param y
	 * @param property
	 */
	public void updateState(int x, int y) {
		if (y >= FIELD_STARTING_Y && !fieldClicksDisabled) {
			plantWindow.setVisible(false);
			irrigationWindow.setVisible(false);
			if (farm.getInventory().getFreeWorker() == null) {
				return;
			}
			if (selection instanceof AbstractIrrigationTool) {
				if (!irrigationMenuClicksDisabled) {
					updateIrrigationWindow(x, y - FIELD_STARTING_Y);
					if (irrigationWindow.getChildren().size > 0) {
						irrigationWindow.setVisible(true);
						Gdx.input.setInputProcessor(irrigationMenuStage);
						sounds.playClick();
					}
				}
			} else {
				state = state.update(farm.getPlot(x, y - FIELD_STARTING_Y),
						farm.getInventory());
				updateInventoryTable();
			}
		} else if (y == 0 && !toolBarClicksDisabled) {
			sounds.playClick();
			plantWindow.setVisible(false);
			irrigationWindow.setVisible(false);
			if (selection != null && selection.equals(farm.getTool(x, y))) {
				if (selection instanceof AbstractPlantTool) {
					if (!plantMenuClicksDisabled) {
						updatePlantWindow();
						if (plantWindow.getChildren().size > 0) {
							plantWindow.setVisible(true);
							Gdx.input.setInputProcessor(plantMenuStage);
						}
					}
				}

				if (inventoryWindow.isVisible()) {
					inventoryWindow.setVisible(false);
				}

			} else if (farm.getTool(x, y) instanceof AbstractTool) {
				if (inventoryWindow.isVisible()) {
					inventoryWindow.setVisible(false);
				}
				selection = farm.getTool(x, y);
				state = state.update((AbstractTool) selection);
				syncSelectTiles(x);
			} else {
				// TODO spell and Upgrade Selection
				/**
				 * selection = farm.getTool(x, y); if (selection instanceof
				 * AbstractSpell) { state = state.update((AbstractCrop)
				 * selection); } else if (selection instanceof AbstractTool) {
				 * state = state.update((AbstractTool) selection); } else if
				 * (selection instanceof AbstractWorker) { state =
				 * state.update((AbstractWorker) selection); } else if
				 * (selection instanceof AbstractUpgrade) { state =
				 * state.update((AbstractUpgrade) selection); } else if
				 * (selection instanceof AbstractCrop) { state =
				 * state.update((AbstractCrop) selection); }
				 */
				selection = null;
				state = new DefaultSelectionState();
				syncSelectTiles(x);
				if(!inventoryClicksDisabled) {
					inventoryWindow.setVisible(true);
					Gdx.input.setInputProcessor(inventoryStage);
				}
			}
		}
	}

	/**
	 * Clears the Status Bar stage. Then, writes the player's bankroll, worker
	 * count, and time remaining in the current game.
	 */
	public void updateStatusBar() {

		/* Stage setup */
		statusBarStage.clear();

		/* Bankroll label setup */
		Label bankBalance = new Label("Bank Balance: $" + PLAYER.getBankroll(),
				style1);
		bankBalance.setPosition(BANK_LABEL_X, BANK_LABEL_Y);

		/* Time label setup */
		String time = null;
		if (!disableGameTime) {
			long curTime = gameStartTime + GAME_TIME_MILLIS
					- System.currentTimeMillis();
			if (curTime < 0) {
				gameOver = true;
			}
			/* 1000 milliseconds = 1 second */
			if (!(curTime < 1000)) {
				farm.checkSeasonTimer();
			}
			time = String.format("%02d:%02d",
					TimeUnit.MILLISECONDS.toMinutes(curTime),
					TimeUnit.MILLISECONDS.toSeconds(curTime)
							- TimeUnit.MILLISECONDS.toMinutes(curTime) * 60);
		} else {
			time = "INFINITE!";
		}
		Label timeRemaining = new Label("Time Remaining: " + time, style1);
		timeRemaining.setPosition(TIME_LABEL_X, TIME_LABEL_Y);

		/* Worker label setup */
		Label workers = new Label("Workers: "
				+ farm.getInventory().getWorkerCount(), style1);
		workers.setPosition(WORKER_LABEL_X, WORKER_LABEL_Y);

		/* Stage setup */
		statusBarStage.addActor(bankBalance);
		statusBarStage.addActor(timeRemaining);
		statusBarStage.addActor(workers);
	}

	/**
	 * Sets up the window to view inventory and market
	 */
	public void setupInventoryWindow() {

		inventorySkin = new Skin(Gdx.files.internal(SKIN_JSON_UI));

		Table marketTable = new Table();
		marketTable.layout();
		inventoryStage.addActor(marketTable);

		inventoryScrollTable.pack();
		ScrollPane inventorySP = new ScrollPane(inventoryScrollTable,
				inventorySkin);
		marketTable.row();
		marketTable.add(new Label("MARKETPLACE", inventorySkin));
		marketTable.row();
		marketTable.add(inventorySP).expand().fill().align(Align.left).left();
		marketTable.pack();
		marketTable.layout();

		inventoryWindow = new Window("", inventorySkin);
		inventoryWindow.setModal(true);
		inventoryWindow.setMovable(false);
		inventoryWindow.setVisible(false);
		inventoryWindow.setSize(Gdx.graphics.getWidth(),
				INVENTORY_HEIGHT);
		inventoryWindow.setPosition(0, Gdx.graphics.getHeight());
		inventoryWindow.defaults().spaceBottom(10);
		inventoryWindow.row().fill().expandX();
		inventoryWindow.add(marketTable).fill().expand().colspan(4)
				.maxHeight(INVENTORY_HEIGHT);
		inventoryStage.addActor(inventoryWindow);
	}

	/**
	 * Populates the inventory/market table with item image, inventory quantity,
	 * buy cost, and sell value
	 */
	public void updateInventoryTable() {
		inventoryScrollTable.clear();
		inventoryScrollTable.layout();
		// inventory Stuff
		Map<String, ArrayList<AbstractItem>> marketItems = this.farm
				.getMarket().getItems();

		Object[] keyset = marketItems.keySet().toArray();
		int typeCount = keyset.length;
		Label type;
		for (int j = 0; j < typeCount; j++) {
			type = new Label(keyset[j].toString(), inventorySkin,
					"default-font", Color.ORANGE);
			type.setWrap(true);
			inventoryScrollTable.row();
			inventoryScrollTable.add(type).left();
			int itemCount = marketItems.get(keyset[j]).size();
			int cost = 0;
			int value = 0;
			int inventoryTypeCount = 0;
			for (int i = 0; i < itemCount; i++) {

				Texture itemTexture = new Texture(
						Gdx.files.internal("textures/"
								+ marketItems.get(keyset[j]).get(i)
										.getTextureName() + ".png"));
				TextureRegion itemImage = new TextureRegion(itemTexture);
				Button carrotButton = new Button(new Image(itemImage),
						inventorySkin);
				carrotButton.setDisabled(true);

				inventoryTypeCount = farm.getInventory().getCount(
						marketItems.get(keyset[j]).get(i));
				System.out.println("Farm inventory count: "
						+ farm.getInventory().getItemsCount());
				cost = marketItems.get(keyset[j]).get(i).getCost();
				value = marketItems.get(keyset[j]).get(i).getValue();
				inventoryScrollTable.row();
				inventoryScrollTable.add(carrotButton).left();
				Label itemName = new Label(marketItems.get(keyset[j]).get(i)
						.toString(), inventorySkin);
				inventoryScrollTable.add(itemName)
						.width((float) (Gdx.graphics.getWidth() * .2)).left();
				Label itemInvCount = new Label(
						Integer.toString(inventoryTypeCount),
						inventorySkin);
				inventoryScrollTable.add(itemInvCount)
						.width((float) (Gdx.graphics.getWidth() * .2)).left();
				Button buyButton, sellButton;
				if (type.getText().toString().equals("WORKERS")) {
					buyButton = new TextButton("HIRE " + " $" + cost,
							inventorySkin, "default");
					sellButton = new TextButton("FIRE " + " $" + value,
							inventorySkin, "default");

				} else {
					buyButton = new TextButton("BUY " + " $" + cost,
							inventorySkin, "default");
					sellButton = new TextButton("SELL " + " $" + value,
							inventorySkin, "default");
				}

				buyButton.addListener(new BuyClickListener(marketItems.get(
						keyset[j]).get(i), itemInvCount));
				inventoryScrollTable.add(buyButton)
						.width((float) (Gdx.graphics.getWidth() * .2)).left();

				sellButton.addListener(new SellClickListener(marketItems.get(
						keyset[j]).get(i), itemInvCount));
				inventoryScrollTable.add(sellButton)
						.width((float) (Gdx.graphics.getWidth() * .2)).left();

			}
		}
	}

	/**
	 * 
	 * @author FarmAdventure Devs
	 * 
	 */
	private class BuyClickListener extends ClickListener {
		AbstractItem item;
		Label itemInvCount;

		/**
		 * This button enables buying of the item and updating of the item
		 * quantity
		 * 
		 * @param item
		 * @param itemInvCount
		 */
		public BuyClickListener(AbstractItem item, Label itemInvCount) {
			this.item = item;
			this.itemInvCount = itemInvCount;
		}

		/**
		 * On button touch the item is bought and the item quantity is updated
		 * in the inventory
		 */
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			if (PLAYER.buyItem(this.item)) {
				farm.getInventory().addItem(item);
				itemInvCount.setText(Integer.toString(farm.getInventory()
						.getCount(this.item)));
				sounds.playMoney();
			}
			return true;
		}
	}

	/**
	 * 
	 * @author FarmAdventure Devs
	 * 
	 */
	private class SellClickListener extends ClickListener {
		AbstractItem item;
		Label itemInvCount;

		/**
		 * This button enables selling of the item and updating of the item
		 * quantity
		 * 
		 * @param item
		 * @param itemInvCount
		 */
		public SellClickListener(AbstractItem item, Label itemInvCount) {
			this.item = item;
			this.itemInvCount = itemInvCount;
		}

		/**
		 * On button touch the item is bought and the item quantity is updated
		 * in the inventory
		 */
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			if (farm.getInventory().removeItem(item)) {
				PLAYER.sellItem(this.item);
				itemInvCount.setText(Integer.toString(farm.getInventory()
						.getCount(this.item)));
				sounds.playMoney();
			}
			return true;
		}
	}
}