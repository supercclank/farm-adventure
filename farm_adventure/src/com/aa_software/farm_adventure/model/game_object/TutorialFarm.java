package com.aa_software.farm_adventure.model.game_object;

public class TutorialFarm extends Farm {
	
	public TutorialFarm() {
		super();
		for(Season season : seasons) {
			season = new Season(SeasonType.SPRING);
		}
		
		plantMap.put(PlantType.CARROT, 5);
		equipmentMap.put(EquipmentType.SCYTHE, 1);
		spellMap.put(SpellType.ILLUSIONIST, 1);
	}
}
