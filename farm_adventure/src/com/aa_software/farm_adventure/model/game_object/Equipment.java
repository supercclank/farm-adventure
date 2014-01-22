package com.aa_software.farm_adventure.model.game_object;

public class Equipment extends Item {
	private EquipmentType equipmentType;

	public Equipment(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
}
