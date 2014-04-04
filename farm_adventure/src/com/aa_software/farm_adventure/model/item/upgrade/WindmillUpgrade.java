package com.aa_software.farm_adventure.model.item.upgrade;

public class WindmillUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "windmill_upgrade";
	public static final String WINDMILL_NAME = "Windmill Upgrade";
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	
	public WindmillUpgrade() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.name = WINDMILL_NAME;
	}
}
