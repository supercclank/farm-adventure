package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;

public class BriberyUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "bribery_upgrade";
	public static final String BRIBERY_NAME = "Bribery";
	public static final float WAGE_MOD = .5f;
	public static final float WORK_RATE_MOD = .5f;
	public static int DEFAULT_COST = 30;
	public static int DEFAULT_VALUE = 15;
	
	public BriberyUpgrade() {
		this.cost = DEFAULT_COST;
		this.value = this.cost/2;
		this.name = BRIBERY_NAME;
	}
	
	public String getTextureName() {
		return TEXTURE_NAME;
	}

	void update(AbstractWorker worker) {
		int currentWage = worker.getWage();
		currentWage *= WAGE_MOD;
		worker.setWage(currentWage);
		float workRate = worker.getWorkRate();
		workRate *= WORK_RATE_MOD;
		worker.setWorkRate(workRate);
	}
}