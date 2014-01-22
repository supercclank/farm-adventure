package com.aa_software.farm_adventure.model.game_object;

public class DesertFarm extends Farm {

	public DesertFarm() {
		super();
		for(Season season : seasons) {
			season = new Season(SeasonType.SUMMER);
		}
		//TODO change these to make sense.
		plantMap.put(PlantType.CORN, 5);
		equipmentMap.put(EquipmentType.ELECTRIC_TROWEL, 1);
		spellMap.put(SpellType.MOLES, 1);
	}
	
}
