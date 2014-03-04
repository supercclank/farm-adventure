package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.tool.irrigate.AbstractIrrigationTool;
import com.aa_software.farm_adventure.model.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.screen.WorldScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class TutorialFarmScreen extends AbstractFarmScreen {

	enum State {
		DESCRIBE_OBJECTIVE, DESCRIBE_FIELD, DESCRIBE_STATUS_BAR, DESCRIBE_TOOL_BAR, DESCRIBE_PLOW, CLICK_PLOW, CLICK_PLOW_PLOT, WAIT_PLOW_PLOT, DESCRIBE_IRRIGATE, CLICK_IRRIGATE, CLICK_IRRIGATE_PLOT, CLICK_IRRIGATE_MENU, WAIT_IRRIGATE_PLOT, DESCRIBE_PLANT, CLICK_PLANT, CLICK_PLANT_PLOT, WAIT_PLANT_PLOT, DESCRIBE_HARVEST, CLICK_HARVEST, CLICK_HARVEST_PLOT, WAIT_HARVEST_PLOT, DESCRIBE_INVENTORY, CLICK_INVENTORY, DESCRIBE_BUY, DESCRIBE_SELL, DESCRIBE_END
	}
	
	/* Font setup */
	final LabelStyle style2 = new LabelStyle(fontType, Color.WHITE);
	
	private String description;
	private Stage descriptionStage;
	private Window descriptionWindow;
	private float descriptionX, descriptionY;
	private State[] states;
	private int stateIndex;
	private boolean foundClick;
	private int waitingForX;
	private int waitingForY;
	
	/**
	 * Constructs a farm screen using the specifications of TutorialFarm.
	 */
	public TutorialFarmScreen() {
		super();
		farm = new TutorialFarm();
		
		states = State.values();
		
		descriptionStage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		
		descriptionWindow = new Window("Tutorial Guide:", skin);
		descriptionWindow.setModal(false);
		descriptionWindow.setMovable(false);
		descriptionWindow.defaults().spaceBottom(10);
		descriptionWindow.row().fill().expandX();
		descriptionWindow.setVisible(true);
		
		descriptionStage.addActor(descriptionWindow);
		
		getDescription();
		updateDescription();
		disableAllGameClicks();
		disableGameTime = true;
		foundClick = true;
	}
	
	@Override
	public void show() {
		super.show();
	}
	
	/**
	 * Acts as our "game loop". Checks for touches (so that they can
	 * be handled), syncs each layer's tiles with their respective model pieces,
	 * and finally updates and draws the stage for the status bar. However, if
	 * the game is over, this method will call for a disposal of the screen.
	 * 
	 * Additionally takes care of the tutorial state.
	 * 
	 */
	@Override
	public void render(float delta) {
		if (gameOver) {
			dispose();
		} else {
			/* Draw the base map to the screen */
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			checkTouch();
			
			farm.getField().syncAllIrrigation();
			syncWaterTiles();
			syncPlantTiles();
			syncGroundTiles();
			syncToolTiles();
			syncStatusTiles();
			syncSeedTile();
			camera.update();
			renderer.setView(camera);
			renderer.render();

			fontType.setScale(FONT_SCALE);
			updateStatusBar();
			statusBarStage.draw();
			plantMenuStage.draw();
			irrigationMenuStage.draw();
			descriptionStage.draw();
			
			boolean allGameClicksAreDisabled = fieldClicksDisabled && toolBarClicksDisabled &&
					irrigationMenuClicksDisabled && plantMenuClicksDisabled;
			if(allGameClicksAreDisabled) {
				Gdx.input.setInputProcessor(descriptionStage);
			}
			
			if(states[stateIndex].toString().toLowerCase().contains("click") &&
					foundClick) {
				transitionState();
			}
			
		}
	}
	
	public void getDescription() {
		switch (states[stateIndex]) {
			case DESCRIBE_OBJECTIVE:
				description = "Describing the objective...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .7);
				break;
			case DESCRIBE_FIELD:
				description = "Describing the field...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .7);
				break;
			case DESCRIBE_STATUS_BAR:
				description = "Describing the status bar...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .25);
				break;
			case DESCRIBE_TOOL_BAR:
				description = "Describing the tool bar...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .13);
				break;
			case DESCRIBE_PLOW:
				description = "Describing the plow...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .05);
				descriptionY = (float) (Gdx.graphics.getHeight() * .13);
				break;
			case CLICK_PLOW:
				description = "Click the plow tool!";
				toolBarClicksDisabled = false;
				foundClick = false;
				waitingForX = 0;
				waitingForY = 0;
				break;
			case CLICK_PLOW_PLOT: 
				description = "Now click a plot to plow it!";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .7);
				foundClick = false;
				fieldClicksDisabled = false;
				waitingForX = 0;
				waitingForY = 2;
				break;
			case WAIT_PLOW_PLOT:
				description = "One of your workers has taken up the task!";
				break;
			case DESCRIBE_IRRIGATE:
				description = "Describing the irrigation tool...";
				descriptionX = (float) (Gdx.graphics.getWidth() * .15);
				descriptionY = (float) (Gdx.graphics.getHeight() * .13);
				break;
			case CLICK_IRRIGATE:
				description = "Click the irrigation tool!";
				descriptionX = (float) (Gdx.graphics.getWidth() * .15);
				descriptionY = (float) (Gdx.graphics.getHeight() * .13);
				toolBarClicksDisabled = false;
				foundClick = false;
				waitingForX = 1;
				waitingForY = 0;
				break;
			case CLICK_IRRIGATE_PLOT: 
				description = "Now click a plot to irrigate it!";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .7);
				foundClick = false;
				irrigationMenuClicksDisabled = false;
				fieldClicksDisabled = false;
				waitingForX = 0;
				waitingForY = 2;
				break;
			case CLICK_IRRIGATE_MENU: 
				description = "Now choose a side to irrigate!";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .7);
				foundClick = false;
				irrigationMenuClicksDisabled = false;
				/* TODO: put in logic for looking for MENU clicks.
				waitingForX = 0;
				waitingForY = 2;
				*/
				break;
			case WAIT_IRRIGATE_PLOT:
				description = "One of your workers has taken up the task!";
				break;
			default:
				description = "Ran out of states!";
				descriptionX = (float) (Gdx.graphics.getWidth() * .25);
				descriptionY = (float) (Gdx.graphics.getHeight() * .8);
				break;
		}
	}
	
	public void updateDescription() {
		Label description = new Label(this.description, style2);
		descriptionWindow.add(description);
		
		if(!states[stateIndex].toString().toLowerCase().contains("click")) {
			TextButton nextButton = new TextButton("Next!", skin);
			nextButton.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					transitionState();
					return true;
				}
			});
			descriptionWindow.add(nextButton);
		} 
		
		descriptionWindow.pack();
		
		descriptionWindow.setPosition(descriptionX, descriptionY);
	}
	
	public void endTutorial() {
		map.dispose();
		renderer.dispose();
		FarmAdventure.getInstance().setScreen(new WorldScreen());
	}
	
	public void transitionState() {
		descriptionWindow.clear();
		disableAllGameClicks();
		if(!(stateIndex + 1 > states.length)) {
			stateIndex++;
		} else {
			endTutorial();
		}
		getDescription();
		updateDescription();
	}
	
	/**
	 * Takes in an x, and y value (cell-based) that represents user input, as
	 * well as the type of cell that was clicked. The options for this is ground
	 * or tool bar. If the user clicked on the tool bar, that tool is selected
	 * and the state updates. If a plot is clicked, the tool bar that was
	 * previously selected will affect the status of the plot and the state will
	 * update.
	 * 
	 * @param x
	 * @param y
	 * @param property
	 */
	@Override
	public void updateState(int x, int y) {
		if (y >= FIELD_STARTING_Y && !fieldClicksDisabled) {
			if(!foundClick && waitingForY > 1) {
				foundClick = true;
			}
			if(selection instanceof AbstractIrrigationTool) {
				updateIrrigationWindow(x, y - FIELD_STARTING_Y);
				if(irrigationWindow.getChildren().size > 0) {
						if(!irrigationMenuClicksDisabled) {
							irrigationWindow.setVisible(true);
							Gdx.input.setInputProcessor(irrigationMenuStage);
						}
				}
			} else {
				state = state.update(farm.getPlot(x, y - FIELD_STARTING_Y));
			}
		} else if (y == 0 && !toolBarClicksDisabled) {
			/* check for double-click */
			if (selection != null && selection.equals(farm.getTool(x, y))) {
				if (selection instanceof AbstractPlantTool) {
					if(!plantMenuClicksDisabled) {
						plantWindow.setVisible(true);
						Gdx.input.setInputProcessor(plantMenuStage);
					}
				}
			} else {
				selection = farm.getTool(x, y);
				if (selection instanceof AbstractSpell) {
					state = state.update((AbstractCrop) selection);
				} else if (selection instanceof AbstractTool) {
					state = state.update((AbstractTool) selection);
				} else if (selection instanceof AbstractWorker) {
					state = state.update((AbstractWorker) selection);
				} else if (selection instanceof AbstractUpgrade) {
					state = state.update((AbstractUpgrade) selection);
				} else if (selection instanceof AbstractCrop) {
					state = state.update((AbstractCrop) selection);
				}
				syncSelectTiles(x);
				if(!foundClick && waitingForY == 0) {
					if(selection.equals(farm.getTool(waitingForX, waitingForY))) {
						foundClick = true;
					}
				}
			}
		}
	}
	
}
