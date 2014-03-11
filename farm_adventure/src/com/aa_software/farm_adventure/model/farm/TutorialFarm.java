package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.item.spell.MolesSpell;
import com.aa_software.farm_adventure.model.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.item.tool.irrigate.BackhoeTool;
import com.aa_software.farm_adventure.model.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.item.tool.plow.HandPlowTool;
import com.aa_software.farm_adventure.model.item.worker.DefaultWorker;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

/**
 * Sets up the logic for a snow farm.
 * @author AASoftware
 *
 */
public class TutorialFarm extends AbstractFarm {

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public TutorialFarm() {
		super();
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SPRING);
		}
		field.createTutorialField();
		//field.setPlot(field.COLUMNS-1, field.ROWS-1, new Plot(PlotType.WATER));
		//field.setPlot(field.COLUMNS-2, field.ROWS-1, new Plot(PlotType.WATER));
		//field.setPlot(field.COLUMNS-1, field.ROWS-2, new Plot(PlotType.WATER));
		
		/*
		 * startingCropCount.put(new BeetCrop(), 5); startingToolCount.put(new
		 * BackhoeTool(), 1); startingSpellCount.put(new MolesSpell(), 1);
		 * startingWorkerCount.put(new DefaultWorker(), 1);
		 */
		seasons[currentSeason].update(field);
		startingCropCount.put(new CarrotCrop(), 5);
		startingToolCount.put(new BackhoeTool(), 1);
		startingToolCount.put(new TrowelTool(), 1);
		startingToolCount.put(new HandPlowTool(), 1);
		startingToolCount.put(new ScytheTool(), 1);
		startingSpellCount.put(new MolesSpell(), 1);
		startingWorkerCount.put(new DefaultWorker(), DEFAULT_NUMBER_OF_WORKERS);
	}
}
