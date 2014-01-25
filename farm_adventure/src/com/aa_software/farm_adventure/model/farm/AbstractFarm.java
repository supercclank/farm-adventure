package com.aa_software.farm_adventure.model.farm;

import java.util.Map;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.season.AbstractSeason;
/*
 * A farm holds the state of the game in progress. The seasons and the 
 * plants, equipment, and spells available.
 */
public abstract class AbstractFarm {
	protected final int DEFAULT_NUMBER_OF_SEASONS = 4;
	/* the next two are arbitrary for now */
	protected final int DEFAULT_NUMBER_OF_WORKERS = 5;
	protected final int DEFAULT_STARTING_BANKROLL = 25;
	
	protected AbstractSeason[] seasons;
	/* each farm starts with a certain amount of seeds, workers, equipment */
	protected Map<AbstractCrop, Integer> cropMap;
	protected Map<AbstractTool, Integer> equipmentMap;
	protected Map<AbstractSpell, Integer> spellMap;
	
	public AbstractFarm() {
		seasons = new AbstractSeason[DEFAULT_NUMBER_OF_SEASONS];
	}

}
