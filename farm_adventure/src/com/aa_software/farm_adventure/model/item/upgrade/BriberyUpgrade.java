package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;

public class BriberyUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "bribery_upgrade";
	public static final float WAGE_MOD = .5f;
	public static final float WORK_RATE_MOD = .5f;

	void update(AbstractWorker worker) {
		int currentWage = worker.getWage();
		currentWage *= WAGE_MOD;
		worker.setWage(currentWage);
		int workRate = worker.getWorkRate();
		workRate *= WORK_RATE_MOD;
		worker.setWorkRate(workRate);
	}
}