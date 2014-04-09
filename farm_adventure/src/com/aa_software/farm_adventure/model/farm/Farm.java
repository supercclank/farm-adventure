package com.aa_software.farm_adventure.model.farm;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
public class Farm {

	protected final Field field;
	protected final ToolBar toolBar;
	protected final Market market;
	protected final Inventory inventory;
	protected final Biome.Type biome;
	protected final List<Season> seasons;
	protected int currentSeason;

	protected final Timer timer;
	protected TimerTask seasonTimer;

	protected long seasonStartTime;

	public Farm(Biome.Type biome) {
		this.biome = biome;
		this.field = new Field(Biome.getWaterMod(biome));
		this.timer = new Timer();
		this.market = new Market();
		this.inventory = new Inventory();
		this.toolBar = new ToolBar(this.inventory);
		this.seasons = new ArrayList<Season>();
		for (Season.Type season : Biome.getSeasons(biome)) {
			seasons.add(new Season(season));
		}
	}

	/**
	 * Applies seasonal effects which constantly need to be updated.
	 * */
	public void applySeasonalEffects() {
		// Updates inventory's seeds to be affected by the growth rate modifier
		ArrayList<AbstractItem> seeds = inventory.getItems().get("SEEDS");
		for (AbstractItem seed : seeds) {
			((AbstractSeed) seed).setGrowthRateMod(seasons.get(currentSeason)
					.getGrowthRateMod());
		}
	}

	public void checkSeasonTimer() {
		if (seasonStartTime == 0)
			seasonStartTime = System.currentTimeMillis();
		long timeLeft = seasonStartTime + Season.CYCLE_TIME_MILLIS
				- System.currentTimeMillis();
		if (timeLeft < 0) {
			currentSeason++;
			currentSeason %= seasons.size();
			seasons.get(currentSeason).update(field);
			seasonStartTime = System.currentTimeMillis();
		}
	}

	public Season getCurrentSeason() {
		return this.seasons.get(currentSeason);
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

	public TaskType getTaskType(int x, int y, Irrigation irr) {
		return field.getTaskType(x, y, irr);
	}

	public AbstractTool getTool(int x, int y) {
		return this.toolBar.getTool(x, y);
	}

	public void updateToolBar() {
		this.toolBar.updateTools(this.inventory);
	}
}