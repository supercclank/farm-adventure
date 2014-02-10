package com.aa_software.farm_adventure.model.selectable.item.worker;

import com.aa_software.farm_adventure.model.selectable.item.AbstractItem;

public abstract class AbstractWorker extends AbstractItem {
	public static final int DEFAULT_WAGE = 200;
	public static final int DEFAULT_LEVEL = 0;
	public static final int DEFAULT_EXPERIENCE = 0;
	public static final int DEFAULT_WORK_RATE = 1;
	public static final int LEVEL_UP_EXPERIENCE_THRESH = 25;
	public static final int MAX_LEVEL = 2;
	public static final float LEVEL_WAGE_MOD = .5f;
	public static final float LEVEL_WORK_RATE_MOD = .7f;

	private int experience;
	private int level;
	private int wage;
	private int workRate;

	public AbstractWorker() {
		this.level = DEFAULT_LEVEL;
		this.experience = DEFAULT_EXPERIENCE;
		this.wage = DEFAULT_WAGE;
		this.workRate = DEFAULT_WORK_RATE;
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

	public int getLevel() {
		return level;
	}

	public int calculateExperience() {
		int experience = DEFAULT_EXPERIENCE;
		if (this.level != DEFAULT_LEVEL) {
			experience = calculateLevelUpThresh();
		}
		return experience;
	}

	public void addExperience() {
		if (level != MAX_LEVEL) {
			this.experience++;
			checkForLevelUp();
		}
	}

	public void addExperience(int experience) {
		if (level != MAX_LEVEL) {
			this.experience += experience;
			checkForLevelUp();
		}
	}

	private void checkForLevelUp() {
		int threshExperience = calculateLevelUpThresh();
		if (experience >= threshExperience) {
			level++;
		}
	}

	private int calculateLevelUpThresh() {
		/* level 0 = exp thresh; level 1 = exp thresh + 2*exp thresh; etc... */
		int threshExperience = LEVEL_UP_EXPERIENCE_THRESH;
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				threshExperience += (i + 1) * LEVEL_UP_EXPERIENCE_THRESH;
			}
		}
		return threshExperience;
	}

	public int getWage() {
		wage = calculateWage();
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	private int calculateWage() {
		int wage = DEFAULT_WAGE;
		/* level 0 = 200; level 1 = 200 + 100; level 2 = 200 + 100 + 150 */
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				wage += wage * LEVEL_WAGE_MOD;
			}
		}
		return wage;
	}

	private int calculateWorkRate() {
		int workRate = DEFAULT_WORK_RATE;
		if (level > DEFAULT_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				workRate += workRate * LEVEL_WORK_RATE_MOD;
			}
		}
		return workRate;
	}

	public int getWorkRate() {
		return workRate;
	}

	public void setWorkRate(int workRate) {
		this.workRate = workRate;
	}

	public String getTextureName() {
		return "arbitrary";
		// TODO: change
	}
}
