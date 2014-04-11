package com.aa_software.farm_adventure.model.item.worker;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.plot.Plot;

/**
 * Represents the default worker.
 * 
 * @author Bebop
 *
 */
public class DefaultWorker extends AbstractItem {
	/**
	 * Starting attributes
	 */
	public static final int STARTING_COST = 20;
	public static final int STARTING_LEVEL = 0;
	public static final int STARTING_EXPERIENCE = 0;
	public static final float WORK_RATE = 1;
	public static final int LEVEL_UP_EXPERIENCE_THRESH = 5;
	public static final int MAX_LEVEL = 2;
	
	/**
	 * Level up modifiers
	 */
	public static final float LEVEL_COST_MOD = .5f;
	public static final float LEVEL_WORK_RATE_MOD = .7f;
	
	public static final String NAME = "Worker";
	public static final String DESCRIPTION = "A worker who will help you work the land.";
	public static final String UNSELECTEDWORKER_TEXTURE = "worker";
	public static final String SELECTEDWORKER_TEXTURE = "selectedworker";
	public static final String TYPE = "WORKERS";

	protected boolean isBusy;
	protected int experience;
	protected int level;
	protected int cost;
	protected float workRate;
	protected String texture;

	public DefaultWorker() {
		super(STARTING_COST, 0, NAME, DESCRIPTION);
		this.level = STARTING_LEVEL;
		this.experience = STARTING_EXPERIENCE;
		this.workRate = WORK_RATE;
		resetTexture();
	}

	public DefaultWorker(int level) {
		super(0, 0, NAME, DESCRIPTION);

		if (level <= STARTING_LEVEL) {
			this.level = STARTING_LEVEL;
		} else if (level >= MAX_LEVEL) {
			this.level = MAX_LEVEL;
		} else {
			this.level = level;
		}

		this.experience = calculateExperience();
		this.cost = calculateCost();
		this.workRate = calculateWorkRate();
		resetTexture();
	}

	/**
	 * Adds one experience point to the worker and then checks if the worker is
	 * ready to level up.
	 */
	public final void addExperience() {
		if (level != MAX_LEVEL) {
			this.experience++;
			checkForLevelUp();
		}
	}

	/**
	 * Adds experience to the workers experience pool.
	 * 
	 * @param experience
	 *            The amount of experience to be added to the worker's pool.
	 */
	public final void addExperience(int experience) {
		if (level != MAX_LEVEL) {
			this.experience += experience;
			checkForLevelUp();
		}
	}

	/**
	 * Calculates the cost of the worker based on his level.
	 * 
	 * @return cost The calculated cost of the worker.
	 */
	private final int calculateCost() {
		int cost = STARTING_COST;
		/* level 0 = 200; level 1 = 200 + 100; level 2 = 200 + 100 + 150 */
		if (level > STARTING_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				cost += cost * LEVEL_COST_MOD;
			}
		}
		return cost;
	}

	/**
	 * Calculates the number of experience points a worker has based on his
	 * level.
	 * 
	 * @return experience The total number of experience points the worker has.
	 */
	public final int calculateExperience() {
		int experience = STARTING_EXPERIENCE;
		if (this.level != STARTING_LEVEL) {
			experience = calculateLevelUpThresh();
		}
		return experience;
	}

	/**
	 * Calculates the number of experience points needed to level up.
	 * 
	 * @return threshExperience The number of experience points needed to level
	 *         up.
	 */
	private final int calculateLevelUpThresh() {
		/* level 0 = exp thresh; level 1 = exp thresh + 2*exp thresh; etc... */
		int threshExperience = LEVEL_UP_EXPERIENCE_THRESH;
		if (level > STARTING_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				threshExperience += (i + 1) * LEVEL_UP_EXPERIENCE_THRESH;
			}
		}
		return threshExperience;
	}

	/**
	 * Calculates the total work rate of the worker, based on his base work rate
	 * and his current level.
	 * 
	 * @return workRate The total work rate of the worker.
	 */
	private final float calculateWorkRate() {
		float workRate = WORK_RATE;
		if (level > STARTING_LEVEL) {
			for (int i = 1; i < level + 1; i++) {
				workRate += workRate * LEVEL_WORK_RATE_MOD;
			}
		}
		return workRate;
	}

	/**
	 * Checks whether the worker's experience points has reached the threshold
	 * to level.
	 */
	private final void checkForLevelUp() {
		int threshExperience = calculateLevelUpThresh();
		if (experience >= threshExperience) {
			level++;
		}
	}

	@Override
	public int getCost() {
		cost = calculateCost();
		return cost;
	}

	@Override
	public String getItemType() {
		return TYPE;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public String getTextureName() {
		return this.texture;
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

	@Override
	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setSelectTexture() {
		this.texture = SELECTEDWORKER_TEXTURE;
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
