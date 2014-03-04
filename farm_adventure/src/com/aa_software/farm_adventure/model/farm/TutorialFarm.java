package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.Field;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;
import com.aa_software.farm_adventure.model.selectable.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.MolesSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.harvest.ScytheTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.BackhoeTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plant.TrowelTool;
import com.aa_software.farm_adventure.model.selectable.item.tool.plow.HandPlowTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.DefaultWorker;

/**
 * Sets up the logic for a snow farm.
 * 
 * @author AASoftware
 * 
 */
public class TutorialFarm extends AbstractFarm {

	public static final FarmType type = FarmType.TUTORIAL;
	public static final SeasonType[] DEFAULT_SEASONS = { SeasonType.SPRING,
			SeasonType.SPRING, SeasonType.SPRING, SeasonType.SPRING };
	private final float WATER_PLOT_MOD = .20f;

	/**
	 * Constructs a farm with the correct seasons and field.
	 */
	public TutorialFarm() {
		super();
		seasons = new Season[DEFAULT_NUMBER_OF_SEASONS];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SPRING);
		}
		field = new Field(WATER_PLOT_MOD);

		/*
		 * startingCropCount.put(new BeetCrop(), 5); startingToolCount.put(new
		 * BackhoeTool(), 1); startingSpellCount.put(new MolesSpell(), 1);
		 * startingWorkerCount.put(new DefaultWorker(), 1);
		 */
		seasons[currentSeason].update(field);
		setupSeasonTimer();
		startingCropCount.put(new CarrotCrop(), 5);
		startingToolCount.put(new BackhoeTool(), 1);
		startingToolCount.put(new TrowelTool(), 1);
		startingToolCount.put(new HandPlowTool(), 1);
		startingToolCount.put(new ScytheTool(), 1);
		startingSpellCount.put(new MolesSpell(), 1);
		startingWorkerCount.put(new DefaultWorker(), DEFAULT_NUMBER_OF_WORKERS);
	}
}
