package com.aa_software.farm_adventure.presenter.screen;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.farm_screen.TutorialFarmScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class WorldScreen extends AbstractScreen {

	public WorldScreen(FarmAdventure game) {
		super(game);
	}

	public void show() {
		super.show();

        // Create table
        Table table = new Table(super.getSkin());
        table.setFillParent(true);
        if(FarmAdventure.DEV_MODE)
        	table.debug();
        super.addActor(table);
        
        //Add label to table
        table.add("Welcome to the WorldScreen!").spaceBottom(50);
        table.row();

        // register the button "Goto farm"
        TextButton farmScreenButton = new TextButton("Goto farm", super.getSkin());
        
        //This line of code will take the user to the farm on click or touch
        farmScreenButton.addListener( new InputListener() {
        	 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		 game.setScreen(new TutorialFarmScreen(game));
        		 return true;
         }
        });
        table.add(farmScreenButton).size(300, 60).uniform().spaceBottom(10);
        table.row();
	}

}
