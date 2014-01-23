package com.aa_software.farm_adventure.model.farm;

import com.aa_software.farm_adventure.model.item.crop.CarrotCrop;
import com.aa_software.farm_adventure.model.item.equipment.ScytheEquipment;
import com.aa_software.farm_adventure.model.item.spell.IllusionistSpell;
import com.aa_software.farm_adventure.model.season.AbstractSeason;
import com.aa_software.farm_adventure.model.season.SpringSeason;

public class TutorialFarm extends AbstractFarm {
	
	public TutorialFarm() {
		super();
		for(AbstractSeason season : seasons) {
			season = new SpringSeason();
		}
		
		cropMap.put(new CarrotCrop(), 5);
		equipmentMap.put(new ScytheEquipment(), 1);
		spellMap.put(new IllusionistSpell(), 1);
	}
}
