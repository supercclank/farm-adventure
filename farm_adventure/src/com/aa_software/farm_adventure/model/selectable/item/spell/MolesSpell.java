package com.aa_software.farm_adventure.model.selectable.item.spell;

public class MolesSpell extends AbstractSpell {
	public static final String TEXTURE_NAME = "moles_spell";
	public static final String MOLES_SPELL_NAME = "Moles";
	public static final int DEFAULT_COST = 15;
	
	public MolesSpell (){
		this.name = MOLES_SPELL_NAME;
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
	}
}
