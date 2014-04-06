package com.aa_software.farm_adventure.model.item.spell;

public class IllusionistSpell extends AbstractSpell {
	public static final String TEXTURE_NAME = "illusionist_spell";
	public static final String ILLUSIONIST_SPELL_NAME = "Illusionist";
	public static final int DEFAULT_COST = 25;

	public IllusionistSpell() {
		this.name = ILLUSIONIST_SPELL_NAME;
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
	}
}
