package com.aa_software.farm_adventure.model.farm;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.ToolBar;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

/*
 * A farm holds the state of the game in progress. The seasons and the 
 * plants, equipment, and spells available.
 */
public abstract class AbstractFarm {
	public static final int DEFAULT_NUMBER_OF_SEASONS = 4;
	/* the next two are arbitrary for now */
	public static final int DEFAULT_NUMBER_OF_WORKERS = 5;
	public static final int DEFAULT_STARTING_BANKROLL = 25;

	protected Field field;
	protected ToolBar toolBar;
	protected Season[] seasons;
	protected int currentSeason;
	protected Timer timer;
	protected TimerTask timerTask;
	/* each farm starts with a certain amount of seeds, workers, equipment */
	protected Map<AbstractWorker, Integer> startingWorkerCount;
	protected Map<AbstractCrop, Integer> startingCropCount;
	protected Map<AbstractTool, Integer> startingToolCount;
	protected Map<AbstractSpell, Integer> startingSpellCount;

	public AbstractFarm() {
		startingWorkerCount = new HashMap<AbstractWorker, Integer>();
		startingCropCount = new HashMap<AbstractCrop, Integer>();
		startingToolCount = new HashMap<AbstractTool, Integer>();
		startingSpellCount = new HashMap<AbstractSpell, Integer>();
		field = new Field();
		toolBar = new ToolBar();
		timer = new Timer();
	}

	public Season getCurrentSeason() {
		return seasons[currentSeason];
	}

	public Field getField() {
		return field;
	}

	public Plot getPlot(int x, int y) {
		return field.getPlot(x, y);
	}

	public AbstractTool getTool(int x, int y) {
		return toolBar.getTool(x, y);
	}

	/**
	 * Sets up a task that will increment the season (up to its maximum) after
	 * the season's cycle time has passed. Tasks spawned this way cancel
	 * themselves after one run and start anew (with the new season's values).
	 */
	public final void setupSeasonTimer() {
		if(timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		if(timerTask != null) {
			timerTask.cancel();
		}
		timerTask = new java.util.TimerTask() {
			@Override
			public void run() {
				currentSeason++;
				currentSeason %= seasons.length;
				seasons[currentSeason].update(field);
				setupSeasonTimer();
			}
		};
		timer.schedule(timerTask, TimeUnit.MINUTES
				.toMillis((long) seasons[currentSeason].getCycleTime()));
	}
	
	public final void cancelTimer() {
		timer.cancel();
	}

}
