package com.aa_software.farm_adventure.model.plot;

import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.presenter.utility.TextureHelper;

/**
 * Represents an individual plot, holding irrigation and crops.
 * 
 * @author Bebop
 * 
 */
public class Plot {

	public enum Type {
		GRASS, UNPLOWEDUNWATERED, UNPLOWEDWATERED, PLOWEDUNWATERED, PLOWEDWATERED, WATER, LEAVES
	}

	private AbstractCrop crop;
	private EnumSet<Irrigation> irrigation;
	private Type type;
	private boolean isUsable;
	private TaskType taskTexturePrefix;
	private int taskTextureIndex;
	public static final String[][] WORK_STATUS_TEXTURES = new String[][] {
			{ null, "puw1", "puw2", "puw3", "puw4" },
			{ null, "pw1", "pw2", "pw3", "pw4" },
			{ null, "leftT1", "leftT2", "leftT3", "leftT4" },
			{ null, "leftB1", "leftB2", "leftB3", "leftB4" },
			{ null, "rightT1", "rightT2", "rightT3", "rightT4" },
			{ null, "rightB1", "rightB2", "rightB3", "rightB4" },
			{ null, "topL1", "topL2", "topL3", "topL4" },
			{ null, "topR1", "topR2", "topR3", "topR4" },
			{ null, "bottomL1", "bottomL2", "bottomL3", "bottomL4" },
			{ null, "bottomR1", "bottomR2", "bottomR3", "bottomR4" },
			{ null, "pot1", "pot2", "pot3", "pot4" },
			{ null, "pot4", "pot3", "pot2", "pot1" },
			{ null, "beet1", "beet2", "beet3", "beet4" },
			{ null, "beet4", "beet3", "beet2", "beet1" },
			{ null, "car1", "car2", "car3", "car4" },
			{ null, "car4", "car3", "car2", "car1" },
			{ null, "cab1", "cab2", "cab3", "cab4" },
			{ null, "cab4", "cab3", "cab2", "cab1" },
			{ null, "bud1", "bud2", "bud3", "bud4" },
			{ null, "bud4", "bud3", "bud2", "bud1" } };

	public Plot(Type plotType) {
		this.irrigation = EnumSet.noneOf(Irrigation.class);
		this.type = plotType;
		this.taskTexturePrefix = TaskType.PLOW_UW; // default
		if (plotType == Type.LEAVES || plotType == Type.WATER) {
			this.isUsable = false;
		} else {
			this.isUsable = true;
		}
	}

	public Plot(Type plotType, EnumSet<Irrigation> irrigation) {
		this.irrigation = irrigation;
		this.type = plotType;
		this.taskTexturePrefix = TaskType.PLOW_UW; // default
		if (plotType == Type.LEAVES || plotType == Type.WATER) {
			this.isUsable = false;
		} else {
			this.isUsable = true;
		}
	}

	/**
	 * Adds irrigation to the plot if it is usable (and doesn't already contain
	 * said irrigation). Then it waters the plot if it is unwatered.
	 */
	public void addIrrigation(Irrigation irrigation) {
		if (!this.irrigation.contains(irrigation) && isUsable) {
			this.irrigation.add(irrigation);
		}
		if (!this.irrigation.isEmpty()
				&& type.toString().toLowerCase().endsWith("unwatered")) {
			waterPlot();
		}
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public EnumSet<Irrigation> getIrrigation() {
		return irrigation;
	}

	/**
	 * Translates the plots irrigation enumeration set into its corresponding
	 * texture name.
	 * 
	 * @return the texture name corresponding to this plots irrigation.
	 */
	public String getIrrigationTextureName() {
		return TextureHelper.getIrrigationTextureName(irrigation);
	}

	public Type getPlotType() {
		return type;
	}

	public int getTaskTextureIndex() {
		return taskTextureIndex;
	}

	public String getTaskTextureName() {
		return WORK_STATUS_TEXTURES[taskTexturePrefix.ordinal()][taskTextureIndex];
	}

	public TaskType getTaskTexturePrefix() {
		return taskTexturePrefix;
	}

	public String getTextureName() {
		return type.toString().toLowerCase();
	}

	public int getWorkStatusTextureLength() {
		return WORK_STATUS_TEXTURES[taskTexturePrefix.ordinal()].length;
	}

	public boolean hasCrop() {
		if (crop == null) {
			return false;
		}
		return true;
	}

	public void incrementTaskTextureIndex() {
		taskTextureIndex++;
	}

	/**
	 * Checks if the plot's type is one of the grass types.
	 */
	public boolean isGrass() {
		return (type == Type.GRASS);
	}

	public boolean isIrrigated() {
		if (irrigation.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the plot's type is one of the unplowed types.
	 */
	public boolean isUnplowed() {
		return (type == Type.UNPLOWEDUNWATERED || type == Type.UNPLOWEDWATERED);
	}

	public boolean isUsable() {
		return isUsable;
	}

	// TODO: This was added to allow the animation for harvest crop
	// to work correctly. Might not be the best way to solve this problem
	// and should be looked at.
	public void removeCrop() {
		crop = null;
	}

	public void setCrop(final AbstractCrop crop) {
		if (crop == null && isUsable) {
			this.crop = null;
		} else if (!isGrass() && !isUnplowed() && isIrrigated() && !hasCrop()
				&& isUsable) {
			this.crop = crop;
		}
	}

	public void setIrrigation(EnumSet<Irrigation> irrigation) {
		if (irrigation.isEmpty() && !this.irrigation.isEmpty()) {
			unwaterPlot();
		} else if (!irrigation.isEmpty() && this.irrigation.isEmpty()) {
			waterPlot();
		}
		if (isUsable) {
			this.irrigation = irrigation;
		}
	}

	public void setPlotType(Type plotType) {
		if (isUsable) {
			if (plotType == Type.LEAVES || plotType == Type.WATER) {
				this.isUsable = false;
			}
			this.type = plotType;
		}
	}

	public void setTaskTextureIndex(int taskTextureIndex) {
		this.taskTextureIndex = taskTextureIndex;
	}

	public void setTaskTexturePrefix(TaskType task) {
		this.taskTexturePrefix = task;
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}

	/**
	 * Changes a plot from containing the "WATERED" keyword to "UNWATERED". This
	 * will affect the texture used to render the plot.
	 */
	public void unwaterPlot() {
		if (type == Type.PLOWEDWATERED) {
			type = Type.PLOWEDUNWATERED;
		} else if (type == Type.UNPLOWEDWATERED) {
			type = Type.UNPLOWEDUNWATERED;
		}
	}

	/**
	 * Changes a plot from containing the "UNWATERED" keyword to "WATERED". This
	 * will affect the texture used to render the plot.
	 */
	public void waterPlot() {
		if (type == Type.PLOWEDUNWATERED) {
			type = Type.PLOWEDWATERED;
		} else if (type == Type.UNPLOWEDUNWATERED) {
			type = Type.UNPLOWEDWATERED;
		}
	}

}