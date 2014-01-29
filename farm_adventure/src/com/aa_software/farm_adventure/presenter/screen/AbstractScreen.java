package com.aa_software.farm_adventure.presenter.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public abstract class AbstractScreen implements Screen {
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	private String imageName;

	public void hide() {
		
	}
	
	public void show() { 
		
	}
	
	public void dispose() {
		
	}
	
	public void render(float delta) {
	}
	
	public void resize(int width, int height) {
		
	}
	
	public void pause() { 
		
	}
	
	public void resume() {
		
	}
}
