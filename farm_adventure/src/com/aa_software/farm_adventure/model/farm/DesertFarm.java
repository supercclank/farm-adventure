package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;
import com.aa_software.farm_adventure.model.selectable.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.MolesSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.irrigate.BackhoeTool;
import com.aa_software.farm_adventure.model.selectable.item.worker.DefaultWorker;

public class DesertFarm extends AbstractFarm {

	public DesertFarm() {
		super();
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new Season(SeasonType.SUMMER);
		}
	}
	
}
