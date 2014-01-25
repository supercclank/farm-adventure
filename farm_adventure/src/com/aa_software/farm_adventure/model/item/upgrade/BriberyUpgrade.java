package com.aa_software.farm_adventure.model.item.upgrade;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;

public class BriberyUpgrade extends AbstractUpgrade {
	public static final String TEXTURE_NAME = "bribery_upgrade";
	public static final float DEFAULT_WAGE_MOD = .5f;
	public static final float DEFAULT_WORK_RATE_MOD = .5f;

	void applyEffect(AbstractItem item) {
		int currentWage = ((AbstractWorker)item).getWage();
		currentWage *= DEFAULT_WAGE_MOD;
		((AbstractWorker)item).setWage(currentWage);
		int workRate = ((AbstractWorker)item).getWorkRate();
		workRate *= DEFAULT_WORK_RATE_MOD;
		((AbstractWorker)item).setWorkRate(workRate);
	}
}