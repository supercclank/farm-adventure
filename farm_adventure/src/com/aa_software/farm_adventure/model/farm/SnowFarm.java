package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.item.spell.TimeFreezeSpell;
import com.aa_software.farm_adventure.model.item.tool.MuleTool;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class SnowFarm extends AbstractFarm {

	public SnowFarm() {
		super();
		int everyOther = 1;
		for (int i = 0; i < seasons.length; i++) {
			if(everyOther%2 == 1) {
				seasons[i] = new Season(SeasonType.FALL);
			} else {
				seasons[i] = new Season(SeasonType.WINTER);
			}
			everyOther++;
		}
		//TODO change these to make sense.
		cropMap.put(new BeetCrop(), 5);
		toolMap.put(new MuleTool(), 1);
		spellMap.put(new TimeFreezeSpell(), 1);
	}
	
}
