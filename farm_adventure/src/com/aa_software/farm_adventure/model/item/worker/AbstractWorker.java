package com.aa_software.farm_adventure.model.item.worker;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractWorker extends AbstractItem {
	public static final int DEFAULT_WAGE = 20;
	public static final int DEFAULT_LEVEL = 0;
	public static final int DEFAULT_EXPERIENCE = 0;
	public static final float DEFAULT_WORK_RATE = 1;
	public static final int LEVEL_UP_EXPERIENCE_THRESH = 5;
	public static final int MAX_LEVEL = 2;
	public static final float LEVEL_WAGE_MOD = .5f;
	public static final float LEVEL_WORK_RATE_MOD = .7f;
	public static final String WORKER_NAME = "Worker";
	public static final String UNSELECTEDWORKER_TEXTURE = "worker";
	public static final String SELECTEDWORKER_TEXTURE = "selectedworker";

	private boolean isBusy;
	private int experience;
	private int level;
	private int wage;
	private float workRate;

	private String texture;
	private boolean isWorking;


	public AbstractWorker() {
		this.level = DEFAULT_LEVEL;
		this.experience = DEFAULT_EXPERIENCE;
		this.wage = DEFAULT_WAGE;
		this.workRate = DEFAULT_WORK_RATE;
		this.name = WORKER_NAME;
		this.description = "A worker who will help you work the land.";
		this.texture = "worker";
		this.isWorking = false;
	}

	public AbstractWorker(int level) {
		if (level <= DEFAULT_LEVEL) {
			this.level = DEFAULT_LEVEL;
		} else if (level >= MAX_LEVEL) {
			this.level = MAX_LEVEL;
		} else {
			this.level = level;
		}

		this.experience = calculateExperience();
		this.wage = calculateWage();
		this.workRate = calculateWorkRate();
	}

	public final void addExperience() {
		if (level != MAX_LEVEL) {
			this.experience++;
			checkForLevelUp();
		}
	}

	public final void addExperience(int experience) {
		if (level != MAX_LEVEL) {
			this.experience += experience;
			checkForLevelUp();
		}
	}

	public final int calculateExperience() {
		int experience = DEFAULT_EXPERIENCE;
		if (this.level != DEFAULT_LEVEL) {
			experience = calculateLevelUpThresh();
		}
		return experience;
	}

	private final int calculateLevelUpThresh() {
		/* level 0 = exp thresh; level 1 = exp thresh + 2*exp thresh; etc... */
		int threshExperience = LEVEL_UP_EXPERIENCE_THRESH;
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				threshExperience += (i + 1) * LEVEL_UP_EXPERIENCE_THRESH;
			}
		}
		return threshExperience;
	}

	public final int calculateWage() {
		int wage = DEFAULT_WAGE;
		/* level 0 = 200; level 1 = 200 + 100; level 2 = 200 + 100 + 150 */
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				wage += wage * LEVEL_WAGE_MOD;
			}
		}
		return wage;
	}

	private final float calculateWorkRate() {
		float workRate = DEFAULT_WORK_RATE;
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				workRate += workRate * LEVEL_WORK_RATE_MOD;
			}
		}
		return workRate;
	}

	private final void checkForLevelUp() {
		int threshExperience = calculateLevelUpThresh();
		if (experience >= threshExperience) {
			level++;
		}
	}

	@Override
	public String getItemType() {
		return "WORKERS";
	}

	public int getLevel() {
		return level;
	}

	@Override
	public String getTextureName() {
		return this.texture;
	}

	public int getWage() {
		wage = calculateWage();
		return wage;
	}

	public float getWorkRate() {
		return workRate;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void resetTexture() {
		this.texture = UNSELECTEDWORKER_TEXTURE;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public void setSelectTexture() {
		this.texture = SELECTEDWORKER_TEXTURE;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public void setWorkRate(float workRate) {
		this.workRate = workRate;
	}

	@Override
	public void update(AbstractItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Plot plot, Inventory inventory) {
		// TODO Auto-generated method stub

	}

}
