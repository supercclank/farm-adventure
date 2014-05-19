package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.FarmScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SplashScreen extends AbstractScreen {

	public static final Sounds SOUNDS = Sounds.getInstance();
	private Skin skin;
	private static final int BUTTON_WIDTH = 300;
	private static final int BUTTON_HEIGHT = 60;
	private static final int TITLE_SPACE = 50;
	private static final int BUTTON_SPACE = 10;
	private Table table;
	private static final Texture splashTexture = new Texture(Gdx.files.internal("textures/" + "splash" + ".png"));
	
	private int time;
	public SplashScreen() {
		super();
		time = 0;
	}

	@Override
	public void show() {
		super.show();
		setupSplashScreen();
		
	}
	
	@Override
	public void render(float delta) {
		if (time > 3) {
			dispose();
		} else {
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			mainStage.act(delta);
			mainStage.draw();
			Table.drawDebug(mainStage);
			try {
				updateTime();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateTime() throws InterruptedException {
		Thread.sleep(1000);
		time++;
	}
	
	protected void checkBackButton(){
		if(Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);
		}
	}
	
	@Override
	public void dispose() {
		FarmAdventure.getInstance().setScreen(new FarmScreen());
	}
	
	private void setupSplashScreen() {
		TextureRegion splashImage = new TextureRegion(splashTexture);
		skin = super.getSkin();
		// Create table
		table = new Table(skin);
		table.setFillParent(true);

		super.addActor(table);
		//super.addActor(new Image(splashImage));
		table.add(new Image(splashImage)).spaceBottom(TITLE_SPACE);
		table.row();
		SOUNDS.playMusic();
	}
}
