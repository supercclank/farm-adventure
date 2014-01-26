package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.item.spell.RainCallSpell;
import com.aa_software.farm_adventure.model.item.tool.BackhoeTool;
import com.aa_software.farm_adventure.model.season.Season;
import com.aa_software.farm_adventure.model.season.SeasonType;

public class RainforestFarm extends AbstractFarm {
	public RainforestFarm() {
		super();
		int everyOther = 1;
		for (int i = 0; i < seasons.length; i++) {
			if(everyOther%2 == 1) {
				seasons[i] = new Season(SeasonType.SUMMER);
			} else {
				seasons[i] = new Season(SeasonType.SPRING);
			}
			everyOther++;
		}
		//TODO change these to make sense.
		cropMap.put(new BananaCrop(), 5);
		toolMap.put(new BackhoeTool(), 1);
		spellMap.put(new RainCallSpell(), 1);
	}
}
