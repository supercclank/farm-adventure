package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.model.audio.Sounds;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionsScreen extends AbstractScreen {

	public final Sounds sounds = Sounds.getInstance();

	private Slider masterVolume;
	private Slider gameVolume;
	private Slider musicVolume;
	private Label masterValue;
	private Label gameValue;
	private Label musicValue;

	public OptionsScreen() {
		super();
	}

	@Override
	public void show() {
		super.show();

		// Create table
		Table table = new Table(super.getSkin());
		table.setFillParent(true);

		// Register table
		super.addActor(table);

		// Add label to table
		table.add("Option Screen").spaceBottom(50).colspan(3);
		table.row();

		table.row().spaceBottom(50);

		// Create masterVolume label
		table.add("Master Volume: ");
		masterVolume = new Slider(0, 100, 5, false, super.getSkin());
		masterVolume.setValue(100 * sounds.getMasterVolume());

		// Add listener
		masterVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				masterValue.setText("" + (int) masterVolume.getValue() + "%");
				sounds.setMasterVolume(masterVolume.getValue() / 100);
				sounds.setMusicVolume(musicVolume.getValue() / 100);
				sounds.setSoundVolume(gameVolume.getValue() / 100);
			}
		});
		table.add(masterVolume);
		masterValue = new Label("" + (int) masterVolume.getValue() + "%",
				super.getSkin());
		table.add(masterValue);
		table.row();

		table.row().spaceBottom(50);

		// Create gameVolume label
		table.add("Game Volume: ");
		gameVolume = new Slider(0, 100, 5, false, super.getSkin());
		gameVolume.setValue(100 * sounds.getSoundVolume());

		// Add listener
		gameVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameValue.setText("" + (int) gameVolume.getValue() + "%");
				sounds.setSoundVolume(gameVolume.getValue() / 100);
			}
		});
		table.add(gameVolume);
		gameValue = new Label("" + (int) gameVolume.getValue() + "%",
				super.getSkin());
		table.add(gameValue);
		table.row();

		// Create musicVolume label
		table.add("Music Volume: ");
		musicVolume = new Slider(0, 100, 5, false, super.getSkin());
		musicVolume.setValue(100 * sounds.getMusicVolume());

		// Add listener
		musicVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				musicValue.setText("" + (int) musicVolume.getValue() + "%");
				sounds.setMusicVolume(musicVolume.getValue() / 100);
			}
		});
		table.add(musicVolume);
		musicValue = new Label("" + (int) musicVolume.getValue() + "%",
				super.getSkin());
		table.add(musicValue);
		table.row();

		// back button
		TextButton backButton = new TextButton("Back", super.getSkin());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				sounds.playClick();
				FarmAdventure.getInstance().setScreen(new MainMenuScreen());
				return true;
			}
		});
		table.add(backButton).spaceTop(200).colspan(3).width(300);
	}

}
