package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
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
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AbstractFarmScreen extends AbstractScreen {
	
	/* 8 minutes in milliseconds is 480000. To test dispose, set to 3-5000. */
	public static final long MAXIMUM_GAME_TIME = 480000;
	public static final String TILE_MAP_NAME = "tile_maps/tileMap128.tmx";
	public static final String TILE_SET_NAME = "tileSet128";
	public static final int TILE_LAYER_COUNT = 5;
	public static final String GROUND_LAYER_NAME = "ground";
	public static final String TOOLBAR_LAYER_NAME = "toolBar";
	public static final String SELECTED_LAYER_NAME = "selected";
	public static final String PLANTS_LAYER_NAME = "plants";
	public static final String WATER_LAYER_NAME = "water";
	public static final String G_TRANSPARENT_TILE_NAME = "gtransparent";
	public static final String T_TRANSPARENT_TILE_NAME = "ttransparent";
	public static final String SEL_TRANSPARENT_TILE_NAME = "seltransparent";
	public static final String W_TRANSPARENT_TILE_NAME = "wtransparent";
	public static final String P_TRANSPARENT_TILE_NAME = "ptransparent";
	public static final Player player = Player.getInstance();
	
	protected final FarmAdventure game;
	protected Timer timer;
	protected ISelectable selection;
	protected ISelectionState state;
	protected AbstractFarm farm;

	protected OrthographicCamera camera;
	protected OrthogonalTiledMapRenderer renderer;
	protected TiledMap map;
	protected TiledMapTileSet tileSet;
	protected HashMap<String, Integer> tileMap;
	protected TimerTask task;

	protected Stage stage;
	protected boolean gameOver = false;

	private static final int TILE_SIZE = 128;

	public AbstractFarmScreen(FarmAdventure game) {
		super(game);
		this.game = game;
	}

	@Override
	public void render(float delta) {
		if(gameOver)
			dispose();
		else {
			/* Draw the base map to the screen */
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
			checkTouch();
	
			camera.update();
	
			renderer.setView(camera);
			renderer.render();
			
			updateStage();
			stage.draw();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		timer = new Timer();
		task = 
				new java.util.TimerTask() {
					@Override
					public void run() {
						gameOver = true;
					}
		};
		
		timer.schedule(task, MAXIMUM_GAME_TIME);
		
		map = new TmxMapLoader().load(TILE_MAP_NAME);
		tileSet = map.getTileSets().getTileSet(TILE_SET_NAME);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 1024);

		this.selection = null;
		this.state = new DefaultSelectionState();
		farm = new TutorialFarm();
		
		Iterator<TiledMapTile> tiles;
		tileMap = new HashMap<String, Integer>();
		String[] layers = { GROUND_LAYER_NAME, TOOLBAR_LAYER_NAME, SELECTED_LAYER_NAME,
				WATER_LAYER_NAME, PLANTS_LAYER_NAME };
		
		for(int i = 0; i < layers.length; i++) {
			tiles = tileSet.iterator();
			while (tiles.hasNext()) {
				TiledMapTile tile = tiles.next();
				tileMap.put(
						tile.getProperties().get(layers[i], String.class),
						tile.getId());
			}
		}
		
		TiledMapTileLayer ground = (TiledMapTileLayer) map.getLayers().get(
				GROUND_LAYER_NAME);

		for (int y = 0; y < Field.ROWS; y++) {
			for (int x = 0; x < ground.getWidth(); x++) {
				Cell gCell = ground.getCell(x, ground.getHeight() - 1 - y);
				TiledMapTile tile = tileSet.getTile(tileMap.get(farm.getPlot(x,
						y).getTextureName()));
				gCell.setTile(tile);
			}
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		//TODO: score evaluation for player bank roll ++++++
		player.setBankroll(player.getBankroll() + 100);
		map.dispose();
		renderer.dispose();
		//renderer = null;
		//map = null;
		game.setScreen(new MainMenuScreen(game));
	}

	public void updateState(int x, int y, String property) {
		if (property.equals(GROUND_LAYER_NAME)) {
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
							.getLayers().get(WATER_LAYER_NAME);
					TiledMapTile selectTile = tileSet
							.getTile(tileMap.get(((Plot) selection)
									.getIrrigation().toString()));
					TiledMapTile selectTranTile = tileSet.getTile(tileMap
							.get(W_TRANSPARENT_TILE_NAME));
					// This adds transparent water texture to all the water
					// layer, except for the one that was selected
					for (int i = 0; i < selected.getWidth(); i++) {
						if (selected.getCell(i, 0).getTile().getProperties()
								.get(WATER_LAYER_NAME, String.class)
								.equals(WATER_LAYER_NAME)
								&& (!(selected.getCell(i, 0).equals(selected
										.getCell(x, y))))) {
							selected.getCell(i, 0).setTile(selectTranTile);
						}
					}
					// This adds a water texture to the selected cell
					if (!(selected.getCell(x, y).getTile().getProperties()
							.get(WATER_LAYER_NAME, String.class)
							.equals(WATER_LAYER_NAME))) {
						selected.getCell(x, y).setTile(selectTile);
					}
				}
			}
			/* plant */
			if (((Plot) selection).getCrop() != null) {
				TiledMapTileLayer selected = (TiledMapTileLayer) map
						.getLayers().get(PLANTS_LAYER_NAME);
				TiledMapTile selectTile = tileSet.getTile(tileMap
						.get(((Plot) selection).getCrop().getTextureName()));
				TiledMapTile selectTranTile = tileSet.getTile(tileMap
						.get(P_TRANSPARENT_TILE_NAME));
				// This adds transparent plants texture to all the plant layer,
				// except for the one that was selected
				for (int i = 0; i < selected.getWidth(); i++) {
					if (selected.getCell(i, 0).getTile().getProperties()
							.get(PLANTS_LAYER_NAME, String.class)
							.equals(PLANTS_LAYER_NAME)
							&& (!(selected.getCell(i, 0).equals(selected
									.getCell(x, y))))) {
						selected.getCell(i, 0).setTile(selectTranTile);
					}
				}
				// This adds a carrot (as of right now) texture to the selected
				// cell
				if (!(selected.getCell(x, y).getTile().getProperties()
						.get(PLANTS_LAYER_NAME, String.class)
						.equals(PLANTS_LAYER_NAME))) {
					selected.getCell(x, y).setTile(selectTile);
				}
			}
			/* Harvest */
			if (((Plot) selection).getTextureName().equals(
					PlotType.GRASS.toString().toLowerCase())
					&& ((Plot) selection).getIrrigation() != null) {
				TiledMapTileLayer selected = (TiledMapTileLayer) map
						.getLayers().get(PLANTS_LAYER_NAME);
				TiledMapTile selectTranTile = tileSet.getTile(tileMap
						.get(P_TRANSPARENT_TILE_NAME));
				// This adds a transparent plant texture to the selected cell
				// (it erases the crop texture)
				if (!(selected.getCell(x, y).getTile().getProperties()
						.get(PLANTS_LAYER_NAME, String.class)
						.equals(PLANTS_LAYER_NAME))) {
					selected.getCell(x, y).setTile(selectTranTile);
				}
			}
		} else if (property.equals(TOOLBAR_LAYER_NAME)) {
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
					.get(SELECTED_LAYER_NAME);
			TiledMapTile selectTile = tileSet.getTile(tileMap
					.get(SELECTED_LAYER_NAME));
			TiledMapTile selectTranTile = tileSet.getTile(tileMap
					.get(SEL_TRANSPARENT_TILE_NAME));
			for (int i = 0; i < selected.getWidth(); i++) {
				if (selected.getCell(i, 0).getTile().getProperties()
						.get(SELECTED_LAYER_NAME, String.class)
						.equals(SELECTED_LAYER_NAME)
						&& (!(selected.getCell(i, 0).equals(selected.getCell(x,
								y))))) {
					selected.getCell(i, 0).setTile(selectTranTile);
				}
			}
			if (!(selected.getCell(x, y).getTile().getProperties()
					.get(SELECTED_LAYER_NAME, String.class)
					.equals(SELECTED_LAYER_NAME))) {
				selected.getCell(x, y).setTile(selectTile);
			}
		}
	}

	public void checkTouch() {
		// TODO: remove magic numbers
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
					GROUND_LAYER_NAME);
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
			System.out.println("Ground Cell: "
					+ gCell.getTile().getProperties()
							.get(GROUND_LAYER_NAME, String.class));
			System.out
					.println("Is it a ground cell: "
							+ !(gCell.getTile().getProperties()
									.get(GROUND_LAYER_NAME, String.class).equals(tileSet
									.getTile(
											tileMap.get(G_TRANSPARENT_TILE_NAME))
									.getProperties()
									.get(GROUND_LAYER_NAME, String.class))));
			TiledMapTileLayer toolBar = (TiledMapTileLayer) map.getLayers()
					.get(TOOLBAR_LAYER_NAME);
			Cell tCell = toolBar.getCell(xCell, yCell);
			System.out.println("ToolBar Cell: "
					+ tCell.getTile().getProperties()
							.get(TOOLBAR_LAYER_NAME, String.class));
			System.out
					.println("Is it a toolbar cell: "
							+ !(tCell.getTile().getProperties()
									.get(TOOLBAR_LAYER_NAME, String.class).equals(tileSet
									.getTile(
											tileMap.get(T_TRANSPARENT_TILE_NAME))
									.getProperties()
									.get(TOOLBAR_LAYER_NAME, String.class))));

			if (!(gCell.getTile().getProperties()
					.get(GROUND_LAYER_NAME, String.class).equals(tileSet
					.getTile(tileMap.get(G_TRANSPARENT_TILE_NAME))
					.getProperties().get(GROUND_LAYER_NAME, String.class)))) {
				updateState(xCell, yCell, GROUND_LAYER_NAME);
			} else if (!(tCell.getTile().getProperties()
					.get(TOOLBAR_LAYER_NAME, String.class).equals(tileSet
					.getTile(tileMap.get(T_TRANSPARENT_TILE_NAME))
					.getProperties().get(TOOLBAR_LAYER_NAME, String.class)))) {
				updateState(xCell, yCell, TOOLBAR_LAYER_NAME);
			}
		}
	}
	
	public void updateStage() {
		
		if(stage==null)
			stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				true);
		else
			stage.clear();
		BitmapFont fontType = new BitmapFont();
		fontType.scale(.4f);
		LabelStyle style1 = new LabelStyle(fontType, Color.BLACK);
		Label bankBalance = new Label("Bank Balance: $" + player.getBankroll(), style1);
		long curTime = task.scheduledExecutionTime() - System.currentTimeMillis();
		String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(curTime),
				TimeUnit.MILLISECONDS.toSeconds(curTime) - TimeUnit.MILLISECONDS.toMinutes(curTime)*60);
		Label timeRemaining = new Label("Time Remaining: " + time, style1);
		Label workers = new Label("Workers: " + player.getInventory().getWorkerCount(), style1);
		bankBalance.setPosition(10, 180);
		timeRemaining.setPosition(220, 180);
		workers.setPosition(500, 180);
		stage.addActor(bankBalance);
		stage.addActor(timeRemaining);
		stage.addActor(workers);
	}
}
