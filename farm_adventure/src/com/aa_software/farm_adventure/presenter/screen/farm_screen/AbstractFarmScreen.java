package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.Iterator;

import com.aa_software.farm_adventure.model.ISelectable;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.presenter.state.DefaultSelectionState;
import com.aa_software.farm_adventure.presenter.state.ISelectionState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class AbstractFarmScreen implements Screen {
	private ISelectable selection;
	private ISelectionState state;
	
	protected OrthographicCamera camera;
	protected OrthogonalTiledMapRenderer renderer;
	protected TiledMap map;

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		/* on-click change selection */
		if(selection instanceof Plot) {
			state = state.update((Plot)selection);
		} else if(selection instanceof AbstractItem) {
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
		}
		
		/* Draw the base map to the screen */
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		checkTouch();
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
	}
	
	@Override
	public void resize(int width, int height){
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		map = new TmxMapLoader().load("tile_maps/tileMap128.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 1024);
		
		this.selection = null;
		this.state = new DefaultSelectionState();
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
	
	public void updateState(int x, int y, String property) {
		
	}
	
	public void checkTouch(){
		//TODO: remove magic numbers
		if(Gdx.input.isTouched()) {
			float xTouch = Gdx.input.getX();
			float yTouch = Gdx.input.getY();
			int xStart = 0;
			int xEnd = 128;
			int yStart = 0;
			int yEnd = 128;
			int xCell = 0;
			int yCell = 0;
			TiledMapTileLayer ground = (TiledMapTileLayer)map.getLayers().get("ground");
			for(int x = 0; x < ground.getWidth(); x++){
				if(xStart <= xTouch && xTouch <= xEnd){
					xCell = x;
				}
				xStart = xEnd;
				xEnd += 128;
			}
			for(int y = 0; y < ground.getHeight(); y++){
				if(yStart <= yTouch && yTouch <= yEnd){
					yCell = (ground.getHeight() -1) - y;
				}
				yStart = yEnd;
				yEnd += 128;
			}
			Cell gCell = ground.getCell(xCell, yCell);
			if(gCell.getTile().getProperties().containsKey("ground")){
				TiledMapTileLayer selected = (TiledMapTileLayer)map.getLayers().get("selected");
				Cell sCell = selected.getCell(xCell, yCell);
				Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("tileSet128").iterator();
				while(tiles.hasNext()) {
					TiledMapTile tile = tiles.next();
					if(tile.getProperties().containsKey("selected") && tile.getProperties().get("selected", String.class).equals("selected")){
						sCell.setTile(tile);
					}
				}
			}
			
		}
	}
}
