package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class AboutScreen extends AbstractScreen {

	public static final Sounds SOUNDS = Sounds.getInstance();
	private Skin skin;
	private static final int BUTTON_WIDTH = 300;
	private static final int BUTTON_HEIGHT = 60;
	private static final int TITLE_SPACE = 50;
	private static final int BUTTON_SPACE = 10;
	private Table table;
	private static final Texture aboutTexture = new Texture(Gdx.files.internal("textures/" + "about" + ".png"));
	
	public AboutScreen() {
		super();
	}

	@Override
	public void show() {
		super.show();
		setupaboutScreen();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		checkBackButton();
	}
	
	protected void checkBackButton(){
		if(Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);
		}
	}
	
	private void setupaboutScreen() {
		TextureRegion aboutImage = new TextureRegion(aboutTexture);
		skin = super.getSkin();
		// Create table
		table = new Table(skin);
		table.setFillParent(true);

		super.addActor(table);
		//super.addActor(new Image(aboutImage));
		table.add(new Image(aboutImage)).spaceBottom(TITLE_SPACE);
		table.row();
		SOUNDS.playMusic();
		
		TextButton giveButton = new TextButton("Give", skin);
		table.add(giveButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
		
		TextButton likeButton = new TextButton("Like", skin);
		table.add(likeButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
		
		TextButton tweetButton = new TextButton("Tweet", skin);
		table.add(tweetButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
		
		TextButton subscribeButton = new TextButton("Subscribe", skin);
		table.add(subscribeButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
		
		TextButton returnToMain = new TextButton("Return to Main Menu", skin);
		table.add(returnToMain).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform()
		.spaceBottom(BUTTON_SPACE);
		table.row();
		
		returnToMain.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				SOUNDS.playClick();
				FarmAdventure.getInstance().setScreen(new MainMenuScreen());
				return true;
			}
		});
	}
}
