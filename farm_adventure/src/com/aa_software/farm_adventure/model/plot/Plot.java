package com.aa_software.farm_adventure.model.plot;

import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.seed.AbstractSeed;
import com.aa_software.farm_adventure.presenter.TextureHelper;
//TODO: change from checking if usable before setting. This will allow the plot
// to asynchronously be updated even while a task is being completed.
// This does raise other problems: consider a plot set to "leaves" right before
// a task completes... it'll undo the leaves!

public class Plot {
	private AbstractCrop crop;
	private AbstractSeed seed;
	private EnumSet<Irrigation> irrigation;
	private PlotType plotType;
	private boolean isUsable;
	private TaskType taskTexturePrefix;
	private int taskTextureIndex;
	public static final String[][] WORK_STATUS_TEXTURES = new String[][] {
			{ null, "puw1", "puw2", "puw3", "puw4" },
			{ null, "pw1", "pw2", "pw3", "pw4" } ,
			{ null, "leftT1", "leftT2", "leftT3", "leftT4" },
			{ null, "leftB1", "leftB2", "leftB3", "leftB4" },
			{ null, "rightT1", "rightT2", "rightT3", "rightT4" },
			{ null, "rightB1", "rightB2", "rightB3", "rightB4" },
			{ null, "topL1", "topL2", "topL3", "topL4" },
			{ null, "topR1", "topR2", "topR3", "topR4" },
			{ null, "bottomL1", "bottomL2", "bottomL3", "bottomL4" },
			{ null, "bottomR1", "bottomR2", "bottomR3", "bottomR4" },
			{ null, "ban1", "ban2", "ban3", "ban4" },
			{ null, "ban4", "ban3", "ban2", "ban1" },
			{ null, "beet1", "beet2", "beet3", "beet4" },
			{ null, "beet4", "beet3", "beet2", "beet1" },
			{ null, "car1", "car2", "car3", "car4" },
			{ null, "car4", "car3", "car2", "car1" },
			{ null, "ric1", "ric2", "ric3", "ric4" },
			{ null, "ric4", "ric3", "ric2", "ric1" },
			{ null, "bud1", "bud2", "bud3", "bud4" },
			{ null, "bud4", "bud3", "bud2", "bud1" }
	};
	
	public Plot(PlotType plotType) {
		this.crop = null;
		this.irrigation = EnumSet.noneOf(Irrigation.class);
		this.plotType = plotType;
		this.taskTexturePrefix = TaskType.PLOW_UW; //default
		if (plotType == PlotType.LEAVES || plotType == PlotType.WATER) {
			this.isUsable = false;
		} else {
			this.isUsable = true;
		}
	}

	public Plot(PlotType plotType, EnumSet<Irrigation> irrigation) {
		this.crop = null;
		this.irrigation = irrigation;
		this.plotType = plotType;
		this.taskTexturePrefix = TaskType.PLOW_UW; //default
		if (plotType == PlotType.LEAVES || plotType == PlotType.WATER) {
			this.isUsable = false;
		} else {
			this.isUsable = true;
		}
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public EnumSet<Irrigation> getIrrigation() {
		return irrigation;
	}

	public void addIrrigation(Irrigation irrigation) {
		if (!this.irrigation.contains(irrigation) && isUsable) {
			this.irrigation.add(irrigation);
		}
		if (!this.irrigation.isEmpty()
				&& plotType.toString().toLowerCase().endsWith("unwatered")) {
			waterPlot();
		}
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public String getTextureName() {
		return plotType.toString().toLowerCase();
	}

	public String getTaskTextureName() {
		return WORK_STATUS_TEXTURES[taskTexturePrefix.ordinal()][taskTextureIndex];
	}

	public int getWorkStatusTextureLength() {
		return WORK_STATUS_TEXTURES[taskTexturePrefix.ordinal()].length;
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

	public boolean isUsable() {
		return isUsable;
	}

	public void setCrop(final AbstractCrop crop) {
		if (crop == null && isUsable) {
			this.crop = null;
		} else if (!isGrass() && !isUnplowed() && isIrrigated() && !hasCrop()
				&& isUsable) {
			this.crop = crop;
		}
	}
	
	public void setSeed(final AbstractSeed seed) {
		if (seed == null && isUsable) {
			this.seed = null;
		} else if (!isGrass() && !isUnplowed() && isIrrigated() && !hasCrop()
				&& isUsable) {
			this.seed = seed;
			this.crop = seed.getCrop();
		}
	}
	
	//TODO: This was added to allow the animation for harvest crop
	// to work correctly. Might not be the best way to solve this problem
	// and should be looked at.
	public void harvestRemoveCrop(final AbstractCrop crop) {
		this.crop = null;
	}

	public void setIrrigation(EnumSet<Irrigation> irrigation) {
		if (isUsable) {
			this.irrigation = irrigation;
		}
		if (irrigation.isEmpty()
				&& !plotType.toString().toLowerCase().endsWith("unwatered")) {
			unwaterPlot();
		} else if (!irrigation.isEmpty()
				&& plotType.toString().toLowerCase().endsWith("unwatered")) {
			waterPlot();
		}
	}

	/**
	 * Changes a plot from containing the "WATERED" keyword to "UNWATERED". This
	 * will affect the texture used to render the plot.
	 */
	public void unwaterPlot() {
		if (plotType == PlotType.PLOWEDWATERED) {
			plotType = PlotType.PLOWEDUNWATERED;
		} else if (plotType == PlotType.UNPLOWEDWATERED) {
			plotType = PlotType.UNPLOWEDUNWATERED;
		}
	}

	/**
	 * Changes a plot from containing the "UNWATERED" keyword to "WATERED". This
	 * will affect the texture used to render the plot.
	 */
	public void waterPlot() {
		if (plotType == PlotType.PLOWEDUNWATERED) {
			plotType = PlotType.PLOWEDWATERED;
		} else if (plotType == PlotType.UNPLOWEDUNWATERED) {
			plotType = PlotType.UNPLOWEDWATERED;
		}
	}

	public void setPlotType(PlotType plotType) {
		if (isUsable) {
			if (plotType == PlotType.LEAVES || plotType == PlotType.WATER) {
				this.isUsable = false;
			}
			this.plotType = plotType;
		}
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}

	public boolean isIrrigated() {
		if (irrigation.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean isUnplowed() {
		return plotType.toString().toLowerCase().startsWith("unplowed");
	}

	public boolean isGrass() {
		return plotType.toString().toLowerCase().startsWith("grass");
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

	public void setTaskTextureIndex(int taskTextureIndex) {
		this.taskTextureIndex = taskTextureIndex;
	}

	public void setTaskTexturePrefix(TaskType task) {
		this.taskTexturePrefix = task;
	}

	public int getTaskTextureIndex() {
		return taskTextureIndex;
	}

	public TaskType getTaskTexturePrefix() {
		return taskTexturePrefix;
	}

}