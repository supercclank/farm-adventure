package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.BananaCrop;
import com.aa_software.farm_adventure.model.item.equipment.BackhoeEquipment;
import com.aa_software.farm_adventure.model.item.spell.RainCallSpell;
import com.aa_software.farm_adventure.model.season.AbstractSeason;
import com.aa_software.farm_adventure.model.season.SpringSeason;
import com.aa_software.farm_adventure.model.season.SummerSeason;

public class RainforestFarm extends AbstractFarm {
	public RainforestFarm() {
		super();
		int everyOther = 1;
		for(AbstractSeason season : seasons) {
			if(everyOther%2 == 1) {
				season = new SummerSeason();
			} else {
				season = new SpringSeason();
			}
			everyOther++;
		}
		//TODO change these to make sense.
		cropMap.put(new BananaCrop(), 5);
		equipmentMap.put(new BackhoeEquipment(), 1);
		spellMap.put(new RainCallSpell(), 1);
	}
}
