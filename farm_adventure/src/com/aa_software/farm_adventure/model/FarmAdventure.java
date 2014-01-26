package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.state.DefaultSelectionState;
import com.aa_software.farm_adventure.model.state.ISelectionState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class FarmAdventure implements ApplicationListener {
	private ISelectable selection;
	private ISelectionState state;
	
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	
	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		map = new TmxMapLoader().load("tile_maps/tileMap128.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 1024);
		
		this.selection = null;
		this.state = new DefaultSelectionState();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
	}

	@Override
	public void render() {		
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
			}
		}
		
		/* Draw the base map to the screen */
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
}
