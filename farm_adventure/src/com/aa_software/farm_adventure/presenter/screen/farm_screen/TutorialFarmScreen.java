package com.aa_software.farm_adventure.presenter.screen.farm_screen;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.farm.TutorialFarm;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.seed.AbstractSeed;
import com.aa_software.farm_adventure.model.item.tool.harvest.AbstractHarvestTool;
import com.aa_software.farm_adventure.model.item.tool.irrigate.AbstractIrrigationTool;
import com.aa_software.farm_adventure.model.item.tool.plant.AbstractPlantTool;
import com.aa_software.farm_adventure.model.item.tool.plow.AbstractPlowTool;
import com.aa_software.farm_adventure.model.item.worker.DefaultWorker;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.aa_software.farm_adventure.presenter.IrrigationListener;
import com.aa_software.farm_adventure.presenter.TextureHelper;
import com.aa_software.farm_adventure.presenter.screen.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TutorialFarmScreen extends AbstractFarmScreen {

	private class SeedClickListener extends ClickListener {
		AbstractItem item;

		public SeedClickListener(AbstractItem item) {
			this.item = item;
		}

		/**
		 * On button touch the item is bought and the item quantity is updated
		 * in the inventory
		 */
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {

			plantWindow.setVisible(false);
			((AbstractPlantTool) farm.getTool(PLANT_TOOL_X, PLANT_TOOL_Y))
					.setSeed(((AbstractSeed) this.item));
			if (states[stateIndex] == State.CLICK_PLANT_MENU) {
				foundClick = true;
			}
			sounds.playClick();
			Gdx.input.setInputProcessor(workerStage);
			return true;
		}
	}

	enum State {
		DESCRIBE_OBJECTIVE, DESCRIBE_FIELD, DESCRIBE_STATUS_BAR, DESCRIBE_PLOW_WORKER, CLICK_PLOW_WORKER, DESCRIBE_TOOL_BAR, DESCRIBE_PLOW, CLICK_PLOW, CLICK_PLOW_PLOT, WAIT_PLOW_PLOT, CLICK_IRRIGATE_WORKER, DESCRIBE_IRRIGATE, CLICK_IRRIGATE, CLICK_IRRIGATE_PLOT, CLICK_PLANT_WORKER, DESCRIBE_PLANT, CLICK_PLANT, CLICK_CLICK_PLANT, CLICK_PLANT_MENU, CLICK_PLANT_PLOT, WAIT_PLANT_PLOT, CLICK_HARVEST_WORKER, DESCRIBE_HARVEST, CLICK_HARVEST, CLICK_HARVEST_PLOT, WAIT_HARVEST_PLOT, DESCRIBE_INVENTORY, CLICK_INVENTORY, DESCRIBE_INVENTORY_SCREEN, DESCRIBE_QUANTITY, DESCRIBE_BUY_AND_SELL, CLICK_BUY_AND_SELL, DESCRIBE_INFO, CLICK_INFO, DESCRIBE_EXIT_INFO, CLICK_EXIT_INFO, BEFORE_LEAVING, DESCRIBE_SEASONS, DESCRIBE_SPRING, DESCRIBE_SUMMER, DESCRIBE_FALL, DESCRIBE_WINTER, DESCRIBE_END, END
	}

	final int MARKET_X = 4;

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
		setAllGameClicksDisabled(true);
		disableGameTime = true;
		foundClick = true;

		// TODO: Maybe there is a better way to stop the deficient worker
		// deadlock.
		for (int i = 0; i < 2; i++) {
			farm.getInventory().addItem(new DefaultWorker());
		}
	}

	@Override
	public void addSeedButton(AbstractSeed seed) {
		Table seedTable = new Table();
		Texture seedTexture = new Texture(Gdx.files.internal("textures/"
				+ seed.getTextureName() + ".png"));
		TextureRegion seedImage = new TextureRegion(seedTexture);
		seedTable.row();
		seedTable.add(new Image(seedImage));
		Label seedQuantity = new Label("" + farm.getInventory().getCount(seed),
				style1);
		seedTable.row();
		seedTable.add(seedQuantity);
		Button seedButton = new Button(seedTable, skin);
		plantWindow.add(seedButton);
		seedButton.addListener(new SeedClickListener(seed));
	}

	/**
	 * This method disposes of our left over libGDX elements, updates the
	 * player's score, and returns the player to the main menu.
	 */
	@Override
	public void dispose() {
		setAllGameClicksDisabled(false);
		map.dispose();
		renderer.dispose();
		descriptionStage.dispose();
		FarmAdventure.getInstance().setScreen(new MainMenuScreen());
	}

	public void getDescription() {
		switch (states[stateIndex]) {
		case DESCRIBE_OBJECTIVE:
			description = "Welcome to your first farm!\nYour objective is to make the most\nmoney before time runs out.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .27);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_FIELD:
			description = "This is your field with plots and water.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_STATUS_BAR:
			description = "This is the status bar which displays\nyour money, time left, and workers.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			descriptionY = (float) (Gdx.graphics.getHeight() * .25);
			break;
		case DESCRIBE_PLOW_WORKER:
			description = "These are \navailable farmers \nthat can labor \na plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .65);
			descriptionY = (float) (Gdx.graphics.getHeight() * .15);
			break;
		case CLICK_PLOW_WORKER:
			description = "Select a worker to \nplow a plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .65);
			descriptionY = (float) (Gdx.graphics.getHeight() * .15);
			foundClick = false;
			workerClicksDisabled = false;
			break;

		case DESCRIBE_TOOL_BAR:
			description = "This is the tool bar which allows\nyou to perform actions on the farm.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case DESCRIBE_PLOW:
			description = "This is the plow\ntool which plows \n a plot of land.";
			descriptionX = Gdx.graphics.getWidth() * 0;
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case CLICK_PLOW:
			description = "Click the plow tool.";
			toolBarClicksDisabled = false;
			foundClick = false;
			waitingForX = 0;
			break;
		case CLICK_PLOW_PLOT:
			description = "Now click a plot to plow it.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .9);
			foundClick = false;
			fieldClicksDisabled = false;
			break;
		case WAIT_PLOW_PLOT:
			description = "One of your workers has taken up the task.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			break;
		case CLICK_IRRIGATE_WORKER:
			description = "Select a worker to \nirrigate a plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .65);
			descriptionY = (float) (Gdx.graphics.getHeight() * .15);
			foundClick = false;
			workerClicksDisabled = false;
			break;
		case DESCRIBE_IRRIGATE:
			description = "This is the irrigation\ntool which allows you\nto irrigate your plots.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .15);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case CLICK_IRRIGATE:
			description = "Click the irrigation tool.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .18);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			toolBarClicksDisabled = false;
			foundClick = false;
			waitingForX = 1;
			break;
		case CLICK_IRRIGATE_PLOT:
			// TODO: Make sure that it only goes forward if you click a plot
			// open to irrigation!
			description = "Now click a plot to irrigate it.\nTry to get the irrigation\n"
					+ "to the plowed plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .9);
			foundClick = false;
			irrigationMenuClicksDisabled = false;
			fieldClicksDisabled = false;
			break;
		case CLICK_PLANT_WORKER:
			System.out.println(selectedWorker);
			description = "Select a worker to \nplant on a plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .65);
			descriptionY = (float) (Gdx.graphics.getHeight() * .15);
			foundClick = false;
			workerClicksDisabled = false;
			break;
		case DESCRIBE_PLANT:
			description = "This is the planting\ntool which plants seeds.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case CLICK_PLANT:
			description = "Click the plant tool.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .40);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			toolBarClicksDisabled = false;
			foundClick = false;
			waitingForX = 2;
			break;
		case CLICK_CLICK_PLANT:
			description = "Click the plant tool\nagain to choose a plant.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .37);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			plantMenuClicksDisabled = false;
			toolBarClicksDisabled = false;
			foundClick = false;
			waitingForX = 2;
			break;
		case CLICK_PLANT_MENU:
			description = "Now choose a type of seed.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			foundClick = false;
			plantMenuClicksDisabled = false;
			break;
		case CLICK_PLANT_PLOT:
			description = "Now choose a plot to plant on.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			foundClick = false;
			fieldClicksDisabled = false;
			break;
		case WAIT_PLANT_PLOT:
			description = "One of your workers has taken up the task.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			break;
		case CLICK_HARVEST_WORKER:
			description = "Select a worker to \nharvest a plot.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .65);
			descriptionY = (float) (Gdx.graphics.getHeight() * .15);
			foundClick = false;
			workerClicksDisabled = false;
			break;
		case DESCRIBE_HARVEST:
			description = "This is the harvesting\ntool which will harvest\ngrown plants.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .55);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case CLICK_HARVEST:
			description = "Click the harvest tool.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .60);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			toolBarClicksDisabled = false;
			foundClick = false;
			waitingForX = 3;
			break;
		case CLICK_HARVEST_PLOT:
			// TODO: should make sure the harvested plot had a plant!
			description = "Now click a plot to harvest it.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .35);
			descriptionY = (float) (Gdx.graphics.getHeight() * .9);
			foundClick = false;
			irrigationMenuClicksDisabled = false;
			fieldClicksDisabled = false;
			break;
		case WAIT_HARVEST_PLOT:
			description = "One of your workers has taken up the task.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			break;
		case DESCRIBE_INVENTORY:
			description = "This is inventory\nand market button.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .85);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			break;
		case CLICK_INVENTORY:
			description = "Click the inventory\nand market button.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .85);
			descriptionY = (float) (Gdx.graphics.getHeight() * .13);
			toolBarClicksDisabled = false;
			inventoryClicksDisabled = false;
			foundClick = false;
			waitingForX = 4;
			break;
		case DESCRIBE_INVENTORY_SCREEN:
			description = "This is your inventory and market screen.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_QUANTITY:
			description = "This is the quantity \nthat you own of a \ncertain item.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .13);
			descriptionY = (float) (Gdx.graphics.getHeight() * .8);
			break;
		case DESCRIBE_BUY_AND_SELL:
			description = "You can use these buttons to\npurchase or sell items\nand hire workers.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .4);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;

		case CLICK_BUY_AND_SELL:
			description = "Buy, sell, hire,\nor upgrade and item.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .7);
			descriptionY = (float) (Gdx.graphics.getHeight() * .8);
			break;
		case DESCRIBE_INFO:
			description = "You can use these buttons to\nget info on an item.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .5);
			descriptionY = (float) (Gdx.graphics.getHeight() * .8);
			break;
		case CLICK_INFO:
			description = "Click the info button.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .5);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_EXIT_INFO:
			description = "This is the exit button to\nleave the market and\nreturn to farm view.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .6);
			descriptionY = (float) (Gdx.graphics.getHeight() * .9);
			break;
		case CLICK_EXIT_INFO:
			description = "Click EXIT button";
			descriptionX = (float) (Gdx.graphics.getWidth() * .5);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case BEFORE_LEAVING:
			description = "Before leaving the tutorial\n"
					+ "you should know about seasons.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_SEASONS:
			description = "Each farm you play will have\n"
					+ "a specific cycle of seasons.\n"
					+ "and each season has a\n"
					+ "different effect on the farm.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_SPRING:
			description = "Spring is the perfect farming\n"
					+ "season. In this season, crops\n"
					+ "grow twice as quickly.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_SUMMER:
			description = "Summer brings a lack of water.\n"
					+ "In this season, plots can\n"
					+ "suddenly lose their irrigation.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_FALL:
			description = "Fall drops leaves from trees.\n"
					+ "In this season, leaves can\n"
					+ "cover plots, making them unusable.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_WINTER:
			description = "Winter lacks the warm sun's\n"
					+ "rays. In this season, crops\n" + "grow twice as slowly.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case DESCRIBE_END:
			description = "Good job! Now try out a real farm.";
			descriptionX = (float) (Gdx.graphics.getWidth() * .30);
			descriptionY = (float) (Gdx.graphics.getHeight() * .7);
			break;
		case END:
			gameOver = true;
			break;
		default:
			description = "Ran out of states!";
			descriptionX = (float) (Gdx.graphics.getWidth() * .25);
			descriptionY = (float) (Gdx.graphics.getHeight() * .8);
			break;
		}
	}

	/**
	 * Acts as our "game loop". Checks for touches (so that they can be
	 * handled), syncs each layer's tiles with their respective model pieces,
	 * and finally updates and draws the stage for the status bar. However, if
	 * the game is over, this method will call for a disposal of the screen.
	 * 
	 * Additionally takes care of the tutorial state.
	 * 
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		descriptionStage.draw();

		boolean allGameClicksAreDisabled = fieldClicksDisabled
				&& toolBarClicksDisabled && irrigationMenuClicksDisabled
				&& plantMenuClicksDisabled && workerClicksDisabled;
		if (allGameClicksAreDisabled) {
			Gdx.input.setInputProcessor(descriptionStage);
		} else if (!workerClicksDisabled
				&& (states[stateIndex] == State.CLICK_PLOW_WORKER || states[stateIndex] == State.CLICK_IRRIGATE_WORKER)
				|| states[stateIndex] == State.CLICK_PLANT_WORKER
				|| states[stateIndex] == State.CLICK_HARVEST_WORKER) {
			Gdx.input.setInputProcessor(workerStage);
			if (selectedWorker >= 0) {
				workerClicksDisabled = true;
				foundClick = true;
			}
		}

		// TODO change this hacky fix. Perhaps add a set of "wait" states,
		// similar to the
		// "click" set that transition on a given condition.
		if (states[stateIndex] == State.CLICK_IRRIGATE_PLOT) {
			if (unwateredPlowedPlotExists() && foundClick) {
				transitionState();
			}
		} else if (states[stateIndex].toString().toLowerCase()
				.contains("click")
				&& foundClick) {
			transitionState();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		descriptionStage.setViewport(width, height);
	}

	public void transitionState() {
		descriptionWindow.clear();
		setAllGameClicksDisabled(true);
		if (!(stateIndex + 1 > states.length)) {
			stateIndex++;
		}
		getDescription();
		updateDescription();
	}

	public boolean unwateredPlowedPlotExists() {
		// TODO probably want to move this logic to Farm
		Field field = farm.getField();
		for (int i = 0; i < Field.COLUMNS; i++) {
			for (int j = 0; j < Field.ROWS; j++) {
				Plot plot = field.getPlot(i, j);
				if (!(plot.isUnplowed() || plot.isGrass())
						&& plot.isIrrigated()) {
					return true;
				}
			}
		}
		return false;
	}

	public void updateDescription() {
		Label description = new Label(this.description, style2);
		descriptionWindow.add(description);

		if (!states[stateIndex].toString().toLowerCase().contains("click")) {
			TextButton nextButton = new TextButton("Next!", skin);
			nextButton.addListener(new InputListener() {
				@Override
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

	/**
	 * Updates the window for selecting irrigation.
	 * 
	 * @param x
	 *            the x coordinate of the selected plot
	 * @param y
	 *            the y coordinate of the selected plot
	 */
	@Override
	public void updateIrrigationWindow(final int x, final int y) {
		irrigationWindow.clear();

		Iterator<Irrigation> iterator = farm.getIrrigationChoices(x, y)
				.iterator();
		for (; iterator.hasNext();) {
			Irrigation irrigation = iterator.next();
			TaskType task = farm.getTaskType(x, y, irrigation);
			Texture irrigationTexture = new Texture(
					Gdx.files.internal(TextureHelper
							.getIrrigationTextureFileName(EnumSet
									.of(irrigation))));
			TextureRegion irrigationImage = new TextureRegion(irrigationTexture);
			Button irrigationButton = new Button(new Image(irrigationImage),
					skin);

			// creates an input listener that additionally has the fields for
			// the selected X and Y. This way, when the listener is called, it
			// will know which X and Y it pertains to.

			irrigationButton.addListener(new IrrigationListener(x, y,
					irrigation, task) {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					if (selection instanceof AbstractIrrigationTool) {
						((AbstractIrrigationTool) selection)
								.setIrrigationChoice(this.getIrrigation());
						((AbstractIrrigationTool) selection).setTaskType(this
								.getTaskType());
						state = state.update(
								farm.getPlot(this.getX(), this.getY()),
								farm.getInventory());
					}
					if (states[stateIndex] == State.CLICK_IRRIGATE_PLOT) {
						foundClick = true;
					}
					irrigationWindow.setVisible(false);
					return true;
				}
			});
			irrigationWindow.add(irrigationButton);
		}

		irrigationWindow.pack();
	}

	/**
	 * Sets up the window to choose a seed to plant
	 */
	@Override
	public void updatePlantWindow() {
		plantWindow.clear();
		if (farm.getInventory().getItems().get("SEEDS") != null) {
			ArrayList<AbstractSeed> seedKey = new ArrayList<AbstractSeed>();
			AbstractSeed tempSeed;
			int seedNum = farm.getInventory().getItems().get("SEEDS").size();
			for (int i = 0; i < seedNum; i++) {
				System.out.println("Seed Key: " + seedKey.size());
				tempSeed = (AbstractSeed) farm.getInventory().getItems()
						.get("SEEDS").get(i);
				if (seedKey.size() == 0) {
					seedKey.add(tempSeed);
					addSeedButton(tempSeed);
				} else {
					Boolean inSeedKey = false;
					for (int j = 0; j < seedKey.size(); j++) {
						if (seedKey.get(j).compareTo(tempSeed) == 0) {
							inSeedKey = true;
							j = seedKey.size();
						}
					}
					if (!inSeedKey) {
						seedKey.add(tempSeed);
						addSeedButton(tempSeed);
					}
				}
			}
		}
		plantWindow.pack();
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
		if (!foundClick) {
			if (y >= FIELD_STARTING_Y && !fieldClicksDisabled) {
				Plot plot = farm.getPlot(x, y - FIELD_STARTING_Y);
				if (plot.isUsable()) {
					boolean harvested = selection instanceof AbstractHarvestTool
							&& plot.hasCrop();
					boolean planted = selection instanceof AbstractPlantTool
							&& !plot.isGrass() && !plot.isUnplowed()
							&& plot.isIrrigated() && !plot.hasCrop();
					boolean plowed = selection instanceof AbstractPlowTool
							&& (plot.isGrass() || plot.isUnplowed());
					if ((harvested && states[stateIndex] == State.CLICK_HARVEST_PLOT)
							|| (planted && states[stateIndex] == State.CLICK_PLANT_PLOT)
							|| (plowed && states[stateIndex] == State.CLICK_PLOW_PLOT)) {
						foundClick = true;
					}
					super.updateState(x, y);
				}
			} else if (y == 0 && !toolBarClicksDisabled) {
				super.updateState(x, y);
				if (x == waitingForX) {
					foundClick = true;
				}
			}
		} else {
			super.updateState(x, y);
		}
	}
}