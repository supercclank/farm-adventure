package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.HashMap;
import java.util.Iterator;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.farm.AbstractFarm;
import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.model.selectable.ISelectable;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
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

public class AbstractFarmScreen implements Screen {
	protected final FarmAdventure game;
	
	public static final String GROUND_LAYER_NAME = "ground";
	public static final String TOOLBAR_LAYER_NAME = "toolBar";
	public static final String SELECTED_LAYER_NAME = "selected";
	public static final String G_TRANSPARENT_TILE_NAME = "gtransparent";
	public static final String T_TRANSPARENT_TILE_NAME = "ttransparent";
	public static final String SEL_TRANSPARENT_TILE_NAME = "seltransparent";

	
	protected ISelectable selection;
	protected ISelectionState state;
	protected AbstractFarm farm;
	
	protected OrthographicCamera camera;
	protected OrthogonalTiledMapRenderer renderer;
	protected TiledMap map;
	protected TiledMapTileSet tileSet;
	protected HashMap<String, Integer> tileMap; 
	
	protected Stage stage;
	
	private static final int TILE_SIZE = 128; 

	public AbstractFarmScreen(FarmAdventure game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		/* Draw the base map to the screen */
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		checkTouch();
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height){
		
	}

	@Override
	public void show() {
		map = new TmxMapLoader().load("tile_maps/tileMap128.tmx");
		tileSet = map.getTileSets().getTileSet("tileSet128");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 1024);
		
		this.selection = null;
		this.state = new DefaultSelectionState();
		
		farm = new TutorialFarm();
		farm.getField();
		tileMap = new HashMap<String, Integer>();
		Iterator<TiledMapTile> tiles = tileSet.iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			tileMap.put(tile.getProperties().get(GROUND_LAYER_NAME, String.class), tile.getId());
		}
		tiles = tileSet.iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			tileMap.put(tile.getProperties().get(TOOLBAR_LAYER_NAME, String.class), tile.getId());
		}
		tiles = tileSet.iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			tileMap.put(tile.getProperties().get(SELECTED_LAYER_NAME, String.class), tile.getId());
		}
		
		TiledMapTileLayer ground = (TiledMapTileLayer)map.getLayers().get(GROUND_LAYER_NAME);

		for(int y = 0; y < Field.ROWS; y++) {
			for(int x = 0; x < ground.getWidth(); x++) {
				Cell gCell = ground.getCell(x, ground.getHeight() - 1 - y);
				TiledMapTile tile = tileSet.getTile(tileMap.get(farm.getPlot(x, y).getTextureName()));
				gCell.setTile(tile);
			}
		}
		
		stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
		BitmapFont fontType = new BitmapFont();
		fontType.scale(.4f);
		LabelStyle style1 = new LabelStyle(fontType, Color.BLACK);
		Label bankBalance = new Label("Bank Balance: $200", style1);
		Label timeRemaining = new Label("Time Remaining: 2:52", style1);
		Label workers = new Label("Workers: 4", style1);
		bankBalance.setPosition(10, 180);
		timeRemaining.setPosition(220, 180);
		workers.setPosition(500, 180);
		stage.addActor(bankBalance);
		stage.addActor(timeRemaining);
		stage.addActor(workers);
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
		map.dispose();
		renderer.dispose();
	}
	
	public void updateState (int x, int y, String property) {
		if(property.equals(GROUND_LAYER_NAME)) {
			TiledMapTileLayer ground = (TiledMapTileLayer)map.getLayers().get(property);
			selection = farm.getPlot (x, y - (ground.getHeight() - Field.ROWS));
			Cell cell = ground.getCell(x, y);
			String tileName = cell.getTile().getProperties().get(property, String.class);
			state = state.update((Plot)selection);
			if(!tileName.equals(selection.getTextureName())) {
				cell.setTile(tileSet.getTile(tileMap.get(selection.getTextureName())));		
			}
		} else if(property.equals(TOOLBAR_LAYER_NAME)) {
			TiledMapTileLayer toolBar = (TiledMapTileLayer)map.getLayers().get(property);
			selection = farm.getTool(x, y);
			Cell cell = toolBar.getCell(x, y);
			String tileName = cell.getTile().getProperties().get(property, String.class);
			if(selection instanceof AbstractSpell) {
				state = state.update((AbstractSpell)selection);
			} else if (selection instanceof AbstractTool) {
				state = state.update((AbstractTool)selection);
			} else if (selection instanceof AbstractWorker) {
				state = state.update((AbstractWorker)selection);
			} else if (selection instanceof AbstractUpgrade) {
				state = state.update((AbstractUpgrade)selection);
			} else if(selection instanceof AbstractCrop) {
				state = state.update((AbstractCrop)selection);
			}
			if(!tileName.equals(selection.getTextureName())) {
				cell.setTile(tileSet.getTile(tileMap.get(selection.getTextureName())));		
			}
			/* selection */
			TiledMapTileLayer selected = (TiledMapTileLayer)map.getLayers().get(SELECTED_LAYER_NAME);
			TiledMapTile selectTile = tileSet.getTile(tileMap.get(SELECTED_LAYER_NAME));
			TiledMapTile selectTranTile = tileSet.getTile(tileMap.get(SEL_TRANSPARENT_TILE_NAME));
			for( int i = 0; i < selected.getWidth(); i++) {
				if(selected.getCell(i, 0).getTile().getProperties().get(SELECTED_LAYER_NAME, String.class).equals(SELECTED_LAYER_NAME)
						&& (!(selected.getCell(i,0).equals(selected.getCell(x,y))))){
					selected.getCell(i,0).setTile(selectTranTile);
				}
			}
			if (!(selected.getCell(x, y).getTile().getProperties().get(SELECTED_LAYER_NAME, String.class).equals(SELECTED_LAYER_NAME))){
				selected.getCell(x, y).setTile(selectTile);
			}
		}
	}
	
	public void checkTouch(){
		//TODO: remove magic numbers
		if(Gdx.input.justTouched()) {
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
			TiledMapTileLayer ground = (TiledMapTileLayer)map.getLayers().get(GROUND_LAYER_NAME);
			for(int x = 0; x < ground.getWidth(); x++){
				if(xStart <= xTouch && xTouch <= xEnd){
					xCell = x;
				}
				xStart = xEnd;
				xEnd += TILE_SIZE;
			}
			for(int y = 0; y < ground.getHeight(); y++){
				if(yStart <= yTouch && yTouch <= yEnd){
					yCell = y;
				}
				yStart = yEnd;
				yEnd += TILE_SIZE;
			}
			Cell gCell = ground.getCell(xCell, yCell); 
			System.out.println("Ground Cell: " + gCell.getTile().getProperties().get(GROUND_LAYER_NAME, String.class));
			System.out.println("Is it a ground cell: " + !(gCell.getTile().getProperties().get(GROUND_LAYER_NAME, String.class).equals(tileSet.getTile(tileMap.get(G_TRANSPARENT_TILE_NAME)).getProperties().get(GROUND_LAYER_NAME, String.class))));
			TiledMapTileLayer toolBar = (TiledMapTileLayer)map.getLayers().get(TOOLBAR_LAYER_NAME);
			Cell tCell = toolBar.getCell(xCell, yCell);
			System.out.println("ToolBar Cell: " + tCell.getTile().getProperties().get(TOOLBAR_LAYER_NAME, String.class));
			System.out.println("Is it a toolbar cell: " + !(tCell.getTile().getProperties().get(TOOLBAR_LAYER_NAME, String.class).equals(tileSet.getTile(tileMap.get(T_TRANSPARENT_TILE_NAME)).getProperties().get(TOOLBAR_LAYER_NAME, String.class))));

			if(!(gCell.getTile().getProperties().get(GROUND_LAYER_NAME, String.class).equals(tileSet.getTile(tileMap.get(G_TRANSPARENT_TILE_NAME)).getProperties().get(GROUND_LAYER_NAME, String.class)))) {
				updateState(xCell,yCell,GROUND_LAYER_NAME);
			}
			else if(!(tCell.getTile().getProperties().get(TOOLBAR_LAYER_NAME, String.class).equals(tileSet.getTile(tileMap.get(T_TRANSPARENT_TILE_NAME)).getProperties().get(TOOLBAR_LAYER_NAME, String.class)))){
				updateState(xCell,yCell,TOOLBAR_LAYER_NAME);
			}
		}
	}
}
