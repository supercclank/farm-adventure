package com.aa_software.farm_adventure.model.game_object;

public class RainforestFarm extends Farm {
	public RainforestFarm() {
		super();
		int everyOther = 1;
		for(Season season : seasons) {
			if(everyOther%2 == 1) {
				season = new Season(SeasonType.SUMMER);
			} else {
				season = new Season(SeasonType.SPRING);
			}
			everyOther++;
		}
		//TODO change these to make sense.
		plantMap.put(PlantType.POTATO, 5);
		equipmentMap.put(EquipmentType.BACKHOE, 1);
		spellMap.put(SpellType.RAIN_CALL, 1);
	}
}
