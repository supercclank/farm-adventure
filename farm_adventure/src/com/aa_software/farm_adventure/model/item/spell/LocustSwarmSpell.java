package com.aa_software.farm_adventure.model.item.spell;

public class LocustSwarmSpell extends AbstractSpell {
	public static final String TEXTURE_NAME = "locust_swarm_spell";
	public static final String LOCUST_SWARM_SPELL_NAME = "Locust Swarm";
	public static final int DEFAULT_COST = 30;

	public LocustSwarmSpell() {
		this.name = LOCUST_SWARM_SPELL_NAME;
		this.cost = DEFAULT_COST;
		this.value = this.cost / 2;
	}
}
