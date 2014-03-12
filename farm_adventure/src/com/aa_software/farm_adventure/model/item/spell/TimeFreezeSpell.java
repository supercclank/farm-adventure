package com.aa_software.farm_adventure.model.item.spell;

public class TimeFreezeSpell extends AbstractSpell {
	public static final String TEXTURE_NAME = "time_freeze_spell";
	public static final String TIME_FREEZE_SPELL_NAME = "Time Freeze";
	public static final int DEFAULT_COST = 25;
	
	public TimeFreezeSpell (){
		this.name = TIME_FREEZE_SPELL_NAME;
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
	}
}
