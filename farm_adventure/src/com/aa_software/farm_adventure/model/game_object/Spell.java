package com.aa_software.farm_adventure.model.game_object;

public class Spell extends Item {
	private SpellType spellType;
	
	public Spell(SpellType spellType) {
		this.setSpellType(spellType);
	}

	public SpellType getSpellType() {
		return spellType;
	}

	public void setSpellType(SpellType spellType) {
		this.spellType = spellType;
	}
}
