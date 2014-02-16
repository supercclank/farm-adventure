package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.Player;
import com.aa_software.farm_adventure.model.ToolBar;
import com.aa_software.farm_adventure.model.farm.AbstractFarm;
import com.aa_software.farm_adventure.model.farm.DesertFarm;
import com.aa_software.farm_adventure.model.farm.SnowFarm;
import com.aa_software.farm_adventure.model.selectable.ISelectable;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.AbstractScreen;
import com.aa_software.farm_adventure.presenter.screen.MainMenuScreen;
import com.aa_software.farm_adventure.presenter.state.DefaultSelectionState;
import com.aa_software.farm_adventure.presenter.state.ISelectionState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class AbstractFarmScreen extends AbstractScreen {

	/* Game */
	public static final long GAME_TIME_MINUTES = 8;

	/* Player */
	public static final Player PLAYER = Player.getInstance();

	/* Tile */
	public static final String TILE_MAP_NAME = "tile_maps/tileMap128.tmx";
	public static final String TILE_SET_NAME = "tileSet128";
	private static final int TILE_SIZE = 128;

	/* Layer */
	/*
	 * For each layer, please provide a method of syncing the model with the
	 * presenter
	 */
	private final String[] allLayers = { "ground", "water", "plant", "select",
			"tool" };
	private final int FIELD_STARTING_Y = 2;

	/* Stage */
	public static final float FONT_SCALE = (float) (Gdx.graphics.getHeight() * .0001);
	public static final float BANK_LABEL_X = (float) (Gdx.graphics.getWidth() * .03),
			BANK_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18),
			TIME_LABEL_X = (float) (Gdx.graphics.getWidth() * .38),
			TIME_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18),
			WORKER_LABEL_X = (float) (Gdx.graphics.getWidth() * .78),
			WORKER_LABEL_Y = (float) (Gdx.graphics.getHeight() * .18);

	protected ISelectable selection;
	protected ISelectionState state;
	protected AbstractFarm farm;

	protected TiledMap map;
	protected TiledMapTileSet tileSet;
	protected HashMap<String, Integer> tileMap;

	protected TimerTask gameTimer;
	protected boolean gameOver;

	public AbstractFarmScreen(FarmAdventure game) {
		super(game);
		gameOver = false;
	}

	/**
	 * Uses the libgdx library to get the x, y location of a users touch if
	 * there was one. This value is used to check if the user clicked over the
	 * plots or over the toolbar. If the user clicks the status bar, there is no
	 * change. If it is a plot, the x , y , and ground string are used to update
	 * the state. If the tool bar was clicked, the x, y, and toobar string are
	 * used to update the state.
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

	@Override
	public void dispose() {
		// TODO: score evaluation for player bankroll
		PLAYER.setBankroll(PLAYER.getBankroll() + 100);
		map.dispose();
		renderer.dispose();
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float delta) {
		if (gameOver) {
			dispose();
		} else {
			/* Draw the base map to the screen */
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			checkTouch();
			syncWaterTiles();
			syncPlantTiles();
			syncGroundTiles();
			syncToolTiles();
			camera.update();
			renderer.setView(camera);
			renderer.render();

			updateStatusBar();
			stage.draw();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	/**
	 * Sets the gameTimer to run its thread after GAME_TIME_MINUTES. This thread
	 * will set gameOver to true which will lead the game to be disposed on next
	 * render.
	 * 
	 * For time remaining (milliseconds): gameTimer.scheduledExecutionTime() -
	 * System.currentTimeMillis()
	 * 
	 * For unit conversion:
	 * 
	 * @see TimeUnit
	 * 
	 */
	public void setupGameTimer() {
		Timer timer = new Timer();
		gameTimer = new java.util.TimerTask() {
			@Override
			public void run() {
				gameOver = true;
				gameTimer.cancel();
			}
		};
		timer.schedule(gameTimer, TimeUnit.MINUTES.toMillis(GAME_TIME_MINUTES));
	}

	@Override
	public void show() {
		setupGameTimer();

		// TODO: We should try to handle screen sizes more appropriately than
		// stretching.
		map = new TmxMapLoader().load(TILE_MAP_NAME);
		tileSet = map.getTileSets().getTileSet(TILE_SET_NAME);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		this.selection = null;
		this.state = new DefaultSelectionState();
		farm = new DesertFarm();

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
	}

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

	public void syncPlantTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"plant");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				/* Get the cell we wish to update */
				Cell cell = layer.getCell(x, y
						+ (layer.getHeight() - Field.ROWS));
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

	public void syncWaterTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"water");
		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < Field.COLUMNS; x++) {
				Cell cell = layer.getCell(x, y
						+ (layer.getHeight() - Field.ROWS));
				/* By default, make the tile transparent */
				TiledMapTile tile = tileSet.getTile(tileMap.get("transparent"));
				if (farm.getPlot(x, y).getIrrigation() != null) {
					tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y)
							.getIrrigation().toString()));
				}
				cell.setTile(tile);
			}
		}
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
		if (y >= FIELD_STARTING_Y) {
			selection = farm.getPlot(x, y - FIELD_STARTING_Y);
			state = state.update((Plot) selection);
		} else if (y == 0) {
			selection = farm.getTool(x, y);
			if (selection instanceof AbstractSpell) {
				state = state.update((AbstractSpell) selection);
			} else if (selection instanceof AbstractTool) {
				state = state.update((AbstractTool) selection);
			} else if (selection instanceof AbstractWorker) {
				state = state.update((AbstractWorker) selection);
			} else if (selection instanceof AbstractUpgrade) {
				state = state.update((AbstractUpgrade) selection);
			} else if (selection instanceof AbstractCrop) {
				state = state.update((AbstractCrop) selection);
			}
			syncSelectTiles(x);
		}
	}

	/**
	 * Clears the Status Bar stage. Then, writes the player's bankroll, worker
	 * count, and time remaining in the current game.
	 */
	public void updateStatusBar() {

		/* Stage setup */
		stage.clear();

		/* Font setup */
		BitmapFont fontType = new BitmapFont();
		fontType.scale(FONT_SCALE);
		LabelStyle style1 = new LabelStyle(fontType, Color.BLACK);

		/* Bankroll label setup */
		Label bankBalance = new Label("Bank Balance: $" + PLAYER.getBankroll(),
				style1);
		bankBalance.setPosition(BANK_LABEL_X, BANK_LABEL_Y);

		/* Time label setup */
		long curTime = gameTimer.scheduledExecutionTime()
				- System.currentTimeMillis();
		String time = String.format("%02d:%02d",
				TimeUnit.MILLISECONDS.toMinutes(curTime),
				TimeUnit.MILLISECONDS.toSeconds(curTime)
						- TimeUnit.MILLISECONDS.toMinutes(curTime) * 60);
		Label timeRemaining = new Label("Time Remaining: " + time, style1);
		timeRemaining.setPosition(TIME_LABEL_X, TIME_LABEL_Y);

		/* Worker label setup */
		Label workers = new Label("Workers: "
				+ PLAYER.getInventory().getWorkerCount(), style1);
		workers.setPosition(WORKER_LABEL_X, WORKER_LABEL_Y);

		/* Stage setup */
		stage.addActor(bankBalance);
		stage.addActor(timeRemaining);
		stage.addActor(workers);
	}

}