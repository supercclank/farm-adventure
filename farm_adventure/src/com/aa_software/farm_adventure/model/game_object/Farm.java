package com.aa_software.farm_adventure.model.game_object;

import java.util.Map;

public class Farm {
	protected final int NUMBER_OF_SEASONS = 4;
	/* the next two are arbitrary for now */
	protected final int NUMBER_OF_WORKERS = 5;
	protected final int STARTING_BANKROLL = 25;
	
	protected Season[] seasons;
	/* each farm starts with a certain amount of seeds, workers, equipment */
	protected Map<PlantType, Integer> plantMap;
	protected Map<EquipmentType, Integer> equipmentMap;
	protected Map<SpellType, Integer> spellMap;
	
	public Farm() {
		seasons = new Season[NUMBER_OF_SEASONS];
		PlantType[] plantTypes = PlantType.values();
		for(PlantType plantType : plantTypes) {
			plantMap.put(plantType, 0);
		}
		EquipmentType[] equipmentTypes = EquipmentType.values();
		for(EquipmentType equipmentType : equipmentTypes) {
			equipmentMap.put(equipmentType, 0);
		}
		SpellType[] spellTypes = SpellType.values();
		for(SpellType spellType : spellTypes) {
			spellMap.put(spellType, 0);
		}
	}

}
