package com.aa_software.farm_adventure.model.selectable.item.spell;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public abstract class AbstractSpell extends AbstractItem {
	protected float spellLength;

	public float getSpellLength() {
		return spellLength;
	}

	public String getTextureName() {
		return "arbitrary";
		// TODO: change
	}

	public void setSpellLength(float spellLength) {
		this.spellLength = spellLength;
	}
}
