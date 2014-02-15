package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;
import com.aa_software.farm_adventure.model.selectable.plot.PlotType;

public class TutorialFarm extends AbstractFarm {

	public TutorialFarm() {
		super();
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SPRING);
		}
		//field.setPlot(field.COLUMNS-1, field.ROWS-1, new Plot(PlotType.WATER));
		//field.setPlot(field.COLUMNS-2, field.ROWS-1, new Plot(PlotType.WATER));
		//field.setPlot(field.COLUMNS-1, field.ROWS-2, new Plot(PlotType.WATER));
		
		/*
		 * startingCropCount.put(new BeetCrop(), 5); startingToolCount.put(new
		 * BackhoeTool(), 1); startingSpellCount.put(new MolesSpell(), 1);
		 * startingWorkerCount.put(new DefaultWorker(), 1);
		 */
	}
}
