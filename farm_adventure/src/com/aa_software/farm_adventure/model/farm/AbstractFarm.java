package com.aa_software.farm_adventure.model.farm;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.Market;
import com.aa_software.farm_adventure.model.ToolBar;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.seed.AbstractSeed;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.plot.TaskType;
import com.aa_software.farm_adventure.model.season.Season;

/*
 * A farm holds the state of the game in progress. The seasons and the 
 * plants, equipment, and spells available.
 */
public abstract class AbstractFarm {
	public static final int DEFAULT_NUMBER_OF_SEASONS = 4;

	protected Field field;
	protected ToolBar toolBar;
	protected Season[] seasons;
	protected int currentSeason;

	protected final Timer timer;
	protected TimerTask seasonTimer;
	protected Market market;
	protected Inventory inventory;

	protected long seasonStartTime;

	public AbstractFarm() {
		this.field = new Field();
		this.timer = new Timer();
		this.market = new Market();
		this.inventory = new Inventory();
		this.toolBar = new ToolBar(this.inventory);
	}

	public void checkSeasonTimer() {
		if (seasonStartTime == 0)
			seasonStartTime = System.currentTimeMillis();
		long timeLeft = seasonStartTime + Season.CYCLE_TIME_MILLIS
				- System.currentTimeMillis();
		if (timeLeft < 0) {
			currentSeason++;
			currentSeason %= seasons.length;
			seasons[currentSeason].update(field);
			seasonStartTime = System.currentTimeMillis();
		}
	}

	public Season getCurrentSeason() {
		return this.seasons[currentSeason];
	}

	public Field getField() {
		return this.field;
	}

	/**
	 * Returns farm inventory
	 * 
	 * @return inventory
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	public EnumSet<Irrigation> getIrrigationChoices(int x, int y) {
		return field.getIrrigationChoices(x, y);
	}

	public Market getMarket() {
		return this.market;
	}

	public Plot getPlot(int x, int y) {
		return this.field.getPlot(x, y);
	}

	public Season[] getSeasons() {
		return seasons;
	}

	public TaskType getTaskType(int x, int y, Irrigation irr) {
		return field.getTaskType(x, y, irr);
	}

	public AbstractTool getTool(int x, int y) {
		return this.toolBar.getTool(x, y);
	}

	/**
	 * Sets up a task that will increment the season (up to its maximum) after
	 * the season's cycle time has passed. Tasks spawned this way cancel
	 * themselves after one run and start anew (with the new season's values).
	 */
	public final void setupSeasonTimer() {
		this.seasonTimer = new java.util.TimerTask() {
			@Override
			public void run() {
				currentSeason++;
				currentSeason %= seasons.length;
				seasons[currentSeason].update(field);
				seasonTimer.cancel();
				setupSeasonTimer();
			}
		};
		timer.schedule(seasonTimer, TimeUnit.MINUTES
				.toMillis((long) seasons[currentSeason].getCycleTime()));
	}

	/** 
	 * Applies seasonal effects which constantly need to be updated. 
	 * */
	public void applySeasonalEffects() {
		// Updates inventory's seeds to be affected by the growth rate modifier
		ArrayList<AbstractItem> seeds = inventory.getItems().get("SEEDS");
		for(AbstractItem seed : seeds) {
			((AbstractSeed)seed).setGrowthRateMod(seasons[currentSeason].getGrowthRateMod());
		}
	}
	
	public void updateToolBar() {
		this.toolBar.updateTools(this.inventory);
	}
}
