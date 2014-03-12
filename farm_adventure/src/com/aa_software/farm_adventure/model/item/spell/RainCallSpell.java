package com.aa_software.farm_adventure.model.item.spell;

public class RainCallSpell extends AbstractSpell {
	public static final String TEXTURE_NAME = "rain_call_spell";
	public static final String RAIN_CALL_SPELL_NAME = "Rain Call";
	public static final int DEFAULT_COST = 20;
	
	public RainCallSpell (){
		this.name = RAIN_CALL_SPELL_NAME;
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
	}
}
