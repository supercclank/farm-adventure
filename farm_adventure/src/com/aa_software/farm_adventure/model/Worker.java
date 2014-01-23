package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;

public class Worker extends AbstractItem {
	public static final int STARTING_WAGE = 200;
	public static final int STARTING_EXPERIENCE = 0;
	public static final int LEVEL_UP_EXPERIENCE_THRESH = 25;
	public static final int STARTING_LEVEL = 0;
	public static final int MAX_LEVEL = 2;
	public static final float LEVEL_WAGE_MOD = .5f;
	public static final float LEVEL_WORK_MOD = .5f;

	private int experience;
	private int level;
	private int wage;
	
	public Worker() {
		this.level = STARTING_LEVEL;
		this.experience = STARTING_EXPERIENCE;	
		this.wage = STARTING_WAGE;
	}
	
	public Worker(int level) {
		if (level <= STARTING_LEVEL) {
			this.level = STARTING_LEVEL;
		} else if (level >= MAX_LEVEL){
			this.level = MAX_LEVEL;
		} else {
			this.level = level;
		}
		
		if(this.level == STARTING_LEVEL) {
			this.experience = 0;
		} else {
			this.experience = calculateLevelUpThresh();
		}
		
		this.wage = calculateWage();
	}

	public int getLevel() {
		return level;
	}

	public void addExperience() {
		if(level != MAX_LEVEL) {
			this.experience++;
			checkForLevelUp();
		}
	}
	
	public void addExperience(int experience) {
		if(level != MAX_LEVEL) {
			this.experience += experience;
			checkForLevelUp();
		}
	}
	
	private void checkForLevelUp() {
		int threshExperience = calculateLevelUpThresh();
		if(experience >= threshExperience) {
			level++;
		}
	}
	
	private int calculateLevelUpThresh() {
		/* level 0 = exp thresh; level 1 = exp thresh + 2*exp thresh; etc... */
		int threshExperience = LEVEL_UP_EXPERIENCE_THRESH;
		if(level > STARTING_LEVEL) {
			for(int i = 1; i < level+1; i++) {
				threshExperience += (i+1) * LEVEL_UP_EXPERIENCE_THRESH;
			}
		}
		return threshExperience;
	}
	
	public int getWage() {
		wage = calculateWage();
		return wage;
	}
	
	private int calculateWage() {
		wage = STARTING_WAGE;
		/* level 0 = 200; level 1 = 200 + 100; level 2 = 200 + 100 + 150 */
		if(level  > STARTING_LEVEL) {
			for(int i = 1; i < level+1; i++) {
				wage += wage * LEVEL_WAGE_MOD;
			}
		}
		return wage;
	}
}
