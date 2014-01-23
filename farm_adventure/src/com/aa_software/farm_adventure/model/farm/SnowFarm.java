package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.BeetCrop;
import com.aa_software.farm_adventure.model.item.equipment.MuleEquipment;
import com.aa_software.farm_adventure.model.item.spell.TimeFreezeSpell;
import com.aa_software.farm_adventure.model.season.FallSeason;
import com.aa_software.farm_adventure.model.season.WinterSeason;

public class SnowFarm extends AbstractFarm {

	public SnowFarm() {
		super();
		int everyOther = 1;
		for (int i = 0; i < seasons.length; i++) {
			if(everyOther%2 == 1) {
				seasons[i] = new FallSeason();
			} else {
				seasons[i] = new WinterSeason();
			}
			everyOther++;
		}
		//TODO change these to make sense.
		cropMap.put(new BeetCrop(), 5);
		equipmentMap.put(new MuleEquipment(), 1);
		spellMap.put(new TimeFreezeSpell(), 1);
	}
	
}
