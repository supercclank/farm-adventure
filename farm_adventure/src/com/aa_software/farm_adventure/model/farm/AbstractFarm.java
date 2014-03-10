package com.aa_software.farm_adventure.model.farm;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.ToolBar;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Irrigation;
import com.aa_software.farm_adventure.model.plot.Plot;
import com.aa_software.farm_adventure.model.season.Season;

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
	protected long seasonStartTime;
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
		toolBar = new ToolBar();
		
	}

	public Season getCurrentSeason() {
		return seasons[currentSeason];
	}
	
	public Season[] getSeasons() {
		return seasons;
	}

	public Field getField() {
		return field;
	}

	public Plot getPlot(int x, int y) {
		return field.getPlot(x, y);
	}
	
	public EnumSet<Irrigation> getIrrigationChoices(int x, int y) {
		return field.getIrrigationChoices(x, y);
	}

	public AbstractTool getTool(int x, int y) {
		return toolBar.getTool(x, y);
	}

	public void checkSeasonTimer() {
		if (seasonStartTime == 0)
			seasonStartTime = System.currentTimeMillis();
		long timeLeft = seasonStartTime + Season.CYCLE_TIME_MILLIS - System.currentTimeMillis();
		if(timeLeft < 0) {
			currentSeason++;
			currentSeason %= seasons.length;
			seasons[currentSeason].update(field);
			seasonStartTime = System.currentTimeMillis();
		}
	}
}
