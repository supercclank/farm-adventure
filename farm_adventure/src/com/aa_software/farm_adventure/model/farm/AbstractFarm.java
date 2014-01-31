package com.aa_software.farm_adventure.model.farm;

import java.util.Map;

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
	protected final int DEFAULT_NUMBER_OF_SEASONS = 4;
	/* the next two are arbitrary for now */
	protected final int DEFAULT_NUMBER_OF_WORKERS = 5;
	protected final int DEFAULT_STARTING_BANKROLL = 25;
	
	protected Field field;
	protected ToolBar toolBar;
	protected Season[] seasons;
	/* each farm starts with a certain amount of seeds, workers, equipment */
	protected Map<AbstractWorker, Integer> startingWorkerCount;
	protected Map<AbstractCrop, Integer> startingCropCount;
	protected Map<AbstractTool, Integer> startingToolCount;
	protected Map<AbstractSpell, Integer> startingSpellCount;
	
	public AbstractFarm() {
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		field = new Field();
		toolBar = new ToolBar();
	}
	
	public Plot getPlot(int x, int y) {
		return field.getPlot(x, y);
	}
	
	public AbstractTool getTool(int x, int y) {
		return toolBar.getTool(x, y);
	}
	
	public Field getField() {
		return field;
	}

}
