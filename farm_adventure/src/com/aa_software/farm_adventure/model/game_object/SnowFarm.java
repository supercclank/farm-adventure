package com.aa_software.farm_adventure.model.game_object;

public class SnowFarm extends Farm {

	public SnowFarm() {
		super();
		int everyOther = 1;
		for(Season season : seasons) {
			if(everyOther%2 == 1) {
				season = new Season(SeasonType.FALL);
			} else {
				season = new Season(SeasonType.WINTER);
			}
			everyOther++;
		}
		//TODO change these to make sense.
		plantMap.put(PlantType.CORN, 5);
		equipmentMap.put(EquipmentType.MULE, 1);
		spellMap.put(SpellType.TIME_FREEZE, 1);
	}
	
}
