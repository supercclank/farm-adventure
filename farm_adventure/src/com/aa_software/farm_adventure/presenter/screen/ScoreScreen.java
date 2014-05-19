package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.model.player.Stats;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ScoreScreen extends AbstractScreen {

	public static final Sounds SOUNDS = Sounds.getInstance();
	public Stats stats;

	public ScoreScreen(Stats stats) {
		super();
		this.stats = stats;
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		checkBackButton();
	}
	
	private void checkBackButton(){
		if(Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);
			FarmAdventure.getInstance().setScreen(new MainMenuScreen());
			dispose();
		}
	}

	@Override
	public void show() {
		super.show();

		Table table = new Table(super.getSkin());
		table.setFillParent(true);

		super.addActor(table);

		table.add("Score:").spaceBottom(50);
		table.row();
		table.add(Integer.toString(stats.getScore())).spaceBottom(50);
		table.row();
		TextButton startGameButton = new TextButton("Return to Main Menu",
				super.getSkin());

		// This line of code will take the user to the world screen on click or
		// touch
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new MainMenuScreen());
				SOUNDS.playClick();
				return true;
			}
		});
		table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();

	}

}
