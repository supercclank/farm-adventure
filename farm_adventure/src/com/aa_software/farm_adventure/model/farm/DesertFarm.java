package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.item.spell.MolesSpell;
import com.aa_software.farm_adventure.model.item.tool.BackhoeTool;
import com.aa_software.farm_adventure.model.season.SummerSeason;

public class DesertFarm extends AbstractFarm {

	public DesertFarm() {
		super();
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = new SummerSeason();
		}
		//TODO change these to make sense.
		cropMap.put(new BeetCrop(), 5);
		equipmentMap.put(new BackhoeTool(), 1);
		spellMap.put(new MolesSpell(), 1);
	}
	
}
