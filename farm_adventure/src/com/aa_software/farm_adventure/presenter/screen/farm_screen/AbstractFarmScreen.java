package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.Player;
import com.aa_software.farm_adventure.model.farm.AbstractFarm;
import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.model.selectable.ISelectable;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.AbstractScreen;
import com.aa_software.farm_adventure.presenter.screen.MainMenuScreen;
import com.aa_software.farm_adventure.presenter.state.DefaultSelectionState;
import com.aa_software.farm_adventure.presenter.state.ISelectionState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.common.collect.ImmutableMap;

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
	Map<String,String> layers = ImmutableMap.of("GROUND_LAYER_NAME", "ground", 
								"TOOLBAR_LAYER_NAME", "toolBar",
								"SELECTED_LAYER_NAME", "selected", 
								"PLANTS_LAYER_NAME", "plants",
								"WATER_LAYER_NAME", "water");
	Map<String,String> transparents = ImmutableMap.of("GROUND_T_TILE_NAME", "gtransparent", 
								"TOOLBAR_T_TILE_NAME", "ttransparent",
								"SELECTED_T_TILE_NAME", "seltransparent", 
								"WATER_T_TILE_NAME", "wtransparent",
								"PLANTS_T_TILE_NAME", "ptransparent");
						
	/* Stage */
	public static final float FONT_SCALE = (float)(Gdx.graphics.getHeight() * .0001 );
	public static final float BANK_LABEL_X = (float)(Gdx.graphics.getWidth() * .03), 
			BANK_LABEL_Y = (float)(Gdx.graphics.getHeight() * .18),
			TIME_LABEL_X = (float)(Gdx.graphics.getWidth() * .38), 
			TIME_LABEL_Y = (float)(Gdx.graphics.getHeight() * .18),
			WORKER_LABEL_X = (float)(Gdx.graphics.getWidth() * .78), 
			WORKER_LABEL_Y = (float)(Gdx.graphics.getHeight() * .18);

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
	 * Uses the libgdx library to get the x, y location of a users touch if there
	 * was one. This value is used to check if the user clicked over the plots or 
	 * over the toolbar. If the user clicks the status bar, there is no change. If it
	 * is a plot, the x , y , and ground string are returned. If the tool bar was 
	 * clicked, the x, y, and toobar string is returned.
	 */
	public void checkTouch() {
		if (Gdx.input.justTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			float xTouch = touchPos.x;
			float yTouch = touchPos.y;
			int xStart = 0;
			int xEnd = TILE_SIZE;
			int yStart = 0;
			int yEnd = TILE_SIZE;
			int xCell = 0;
			int yCell = 0;
			TiledMapTileLayer ground = (TiledMapTileLayer) map.getLayers().get(
					layers.get("GROUND_LAYER_NAME"));
			for (int x = 0; x < ground.getWidth(); x++) {
				if (xStart <= xTouch && xTouch <= xEnd) {
					xCell = x;
				}
				xStart = xEnd;
				xEnd += TILE_SIZE;
			}
			for (int y = 0; y < ground.getHeight(); y++) {
				if (yStart <= yTouch && yTouch <= yEnd) {
					yCell = y;
				}
				yStart = yEnd;
				yEnd += TILE_SIZE;
			}
			Cell gCell = ground.getCell(xCell, yCell);
			TiledMapTileLayer toolBar = (TiledMapTileLayer) map.getLayers()
					.get(layers.get("TOOLBAR_LAYER_NAME"));
			Cell tCell = toolBar.getCell(xCell, yCell);
			if (!(gCell.getTile().getProperties()
					.get(layers.get("GROUND_LAYER_NAME"), String.class).equals(tileSet
					.getTile(tileMap.get(transparents.get("GROUND_T_TILE_NAME")))
					.getProperties().get(layers.get("GROUND_LAYER_NAME"), String.class)))) {
				updateState(xCell, yCell, layers.get("GROUND_LAYER_NAME"));
			} else if (!(tCell.getTile().getProperties()
					.get(layers.get("TOOLBAR_LAYER_NAME"), String.class).equals(tileSet
					.getTile(tileMap.get(transparents.get("TOOLBAR_T_TILE_NAME")))
					.getProperties().get(layers.get("TOOLBAR_LAYER_NAME"), String.class)))) {
				updateState(xCell, yCell, layers.get("TOOLBAR_LAYER_NAME"));
			}
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
		if (gameOver)
			dispose();
		else {
			/* Draw the base map to the screen */
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			checkTouch();

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
	 * Sets the gameTimer to run its thread after GAME_TIME_MINUTES. This thread will
	 * set gameOver to true which will lead the game to be disposed on next render.
	 * 
	 * For time remaining (milliseconds): 
	 * 				gameTimer.scheduledExecutionTime() - System.currentTimeMillis()
	 *  
	 *  For unit conversion:
	 *	@see	TimeUnit
	 *
	 */
	public void setupGameTimer() {
		
		Timer timer = new Timer();
		gameTimer = new java.util.TimerTask() {
			@Override
			public void run() {
				gameOver = true;
			}
		};
		timer.schedule(gameTimer, TimeUnit.MINUTES.toMillis(GAME_TIME_MINUTES));
		
	}
	
	@Override
	public void show() {
		setupGameTimer();
		map = new TmxMapLoader().load(TILE_MAP_NAME);
		tileSet = map.getTileSets().getTileSet(TILE_SET_NAME);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//TODO: We should try to handle screen sizes more appropriately than stretching.
		
		this.selection = null;
		this.state = new DefaultSelectionState();
		farm = new TutorialFarm();

		Iterator<String> layerIterator = layers.values().iterator();
		String[] layers = new String[this.layers.size()];
		
		for(int i = 0; layerIterator.hasNext(); i++) {
			layers[i] = layerIterator.next();
		}
		
		Iterator<TiledMapTile> tileIterator;
		tileMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < layers.length; i++) {
			tileIterator = tileSet.iterator();
			while (tileIterator.hasNext()) {
				TiledMapTile tile = tileIterator.next();
				tileMap.put(tile.getProperties().get(layers[i], String.class),
						tile.getId());
			}
		}

		TiledMapTileLayer ground = (TiledMapTileLayer) map.getLayers().get(
				this.layers.get("GROUND_LAYER_NAME"));

		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < ground.getWidth(); x++) {
				Cell gCell = ground.getCell(x, ground.getHeight() - 1 - y);
				TiledMapTile tile = tileSet.getTile(tileMap.get(farm.getPlot(x,
						y).getTextureName()));
				gCell.setTile(tile);
			}
		}
	}

	/**
	 * Creates or clears the Status Bar stage. Then, writes the player's
	 * bankroll, worker count, and time remaining in the current game.
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

	/**
	 * Takes in an x, and y, value that represents user input, as well as the type of 
	 * cell that was clicked. The options for this is ground or tool bar. If the user clicked
	 * on the tool bar, that tool is selected and the state updates. If a plot is clicked, the tool
	 * bar that was previously selected will affect the status of the plot and the state will update.
	 * @param x
	 * @param y
	 * @param property
	 */
	public void updateState(int x, int y, String property) {
		if (property.equals(layers.get("GROUND_LAYER_NAME"))) {
			TiledMapTileLayer ground = (TiledMapTileLayer) map.getLayers().get(
					property);
			selection = farm.getPlot(x, y - (ground.getHeight() - Field.ROWS));
			Cell cell = ground.getCell(x, y);
			String tileName = cell.getTile().getProperties()
					.get(property, String.class);
			state = state.update((Plot) selection);
			
			if (!tileName.equals(selection.getTextureName())) {
				cell.setTile(tileSet.getTile(tileMap.get(selection
						.getTextureName())));
				if (selection.getTextureName().equals(
						PlotType.PLOWEDWATERED.toString().toLowerCase())) {
					TiledMapTileLayer selected = (TiledMapTileLayer) map
							.getLayers().get(layers.get("WATER_LAYER_NAME"));
					TiledMapTile selectTile = tileSet
							.getTile(tileMap.get(((Plot) selection)
									.getIrrigation().toString()));
					TiledMapTile selectTranTile = tileSet.getTile(tileMap
							.get(transparents.get("SELECTED_T_TILE_NAME")));
					// This adds transparent water texture to all the water
					// layer, except for the one that was selected
					for (int i = 0; i < selected.getWidth(); i++) {
						if (selected.getCell(i, 0).getTile().getProperties()
								.get(layers.get("WATER_LAYER_NAME"), String.class)
								.equals(layers.get("WATER_LAYER_NAME"))
								&& (!(selected.getCell(i, 0).equals(selected
										.getCell(x, y))))) {
							selected.getCell(i, 0).setTile(selectTranTile);
						}
					}
					// This adds a water texture to the selected cell
					if (!(selected.getCell(x, y).getTile().getProperties()
							.get(layers.get("WATER_LAYER_NAME"), String.class)
							.equals(layers.get("WATER_LAYER_NAME")))) {
						selected.getCell(x, y).setTile(selectTile);
					}
				}
			}
			/* plant */
			if (((Plot) selection).getCrop() != null) {
				TiledMapTileLayer selected = (TiledMapTileLayer) map
						.getLayers().get(layers.get("PLANTS_LAYER_NAME"));
				TiledMapTile selectTile = tileSet.getTile(tileMap
						.get(((Plot) selection).getCrop().getTextureName()));
				TiledMapTile selectTranTile = tileSet.getTile(tileMap
						.get(transparents.get("PLANTS_T_TILE_NAME")));
				// This adds transparent plants texture to all the plant layer,
				// except for the one that was selected
				for (int i = 0; i < selected.getWidth(); i++) {
					if (selected.getCell(i, 0).getTile().getProperties()
							.get(layers.get("PLANTS_LAYER_NAME"), String.class)
							.equals(layers.get("PLANTS_LAYER_NAME"))
							&& (!(selected.getCell(i, 0).equals(selected
									.getCell(x, y))))) {
						selected.getCell(i, 0).setTile(selectTranTile);
					}
				}
				// This adds a carrot (as of right now) texture to the selected
				// cell
				if (!(selected.getCell(x, y).getTile().getProperties()
						.get(layers.get("PLANTS_LAYER_NAME"), String.class)
						.equals(layers.get("PLANTS_LAYER_NAME")))) {
					selected.getCell(x, y).setTile(selectTile);
				}
			}
			/* Harvest */
			if (((Plot) selection).getTextureName().equals(
					PlotType.GRASS.toString().toLowerCase())
					&& ((Plot) selection).getIrrigation() != null) {
				TiledMapTileLayer selected = (TiledMapTileLayer) map
						.getLayers().get(layers.get("PLANTS_LAYER_NAME"));
				TiledMapTile selectTranTile = tileSet.getTile(tileMap
						.get(transparents.get("PLANTS_T_TILE_NAME")));
				// This adds a transparent plant texture to the selected cell
				// (it erases the crop texture)
				if (!(selected.getCell(x, y).getTile().getProperties()
						.get(layers.get("PLANTS_LAYER_NAME"), String.class)
						.equals(layers.get("PLANTS_LAYER_NAME")))) {
					selected.getCell(x, y).setTile(selectTranTile);
				}
			}
		} else if (property.equals(layers.get("TOOLBAR_LAYER_NAME"))) {
			TiledMapTileLayer toolBar = (TiledMapTileLayer) map.getLayers()
					.get(property);
			selection = farm.getTool(x, y);
			Cell cell = toolBar.getCell(x, y);
			String tileName = cell.getTile().getProperties()
					.get(property, String.class);
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
			if (!tileName.equals(selection.getTextureName())) {
				cell.setTile(tileSet.getTile(tileMap.get(selection
						.getTextureName())));
			}
			/* selection */
			TiledMapTileLayer selected = (TiledMapTileLayer) map.getLayers()
					.get(layers.get("SELECTED_LAYER_NAME"));
			TiledMapTile selectTile = tileSet.getTile(tileMap
					.get(layers.get("SELECTED_LAYER_NAME")));
			TiledMapTile selectTranTile = tileSet.getTile(tileMap
					.get(transparents.get("SELECTED_T_TILE_NAME")));
			for (int i = 0; i < selected.getWidth(); i++) {
				if (selected.getCell(i, 0).getTile().getProperties()
						.get(layers.get("SELECTED_LAYER_NAME"), String.class)
						.equals(layers.get("SELECTED_LAYER_NAME"))
						&& (!(selected.getCell(i, 0).equals(selected.getCell(x,
								y))))) {
					selected.getCell(i, 0).setTile(selectTranTile);
				}
			}
			if (!(selected.getCell(x, y).getTile().getProperties()
					.get(layers.get("SELECTED_LAYER_NAME"), String.class)
					.equals(layers.get("SELECTED_LAYER_NAME")))) {
				selected.getCell(x, y).setTile(selectTile);
			}
		}
	}
	
}