package com.aa_software.farm_adventure.model.plot;

import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.presenter.TextureHelper;
//TODO: change from checking if usable before setting. This will allow the plot
// to asynchronously be updated even while a task is being completed.
// This does raise other problems: consider a plot set to "leaves" right before
// a task completes... it'll undo the leaves!

public class Plot {
	private AbstractCrop crop;
	private EnumSet<Irrigation> irrigation;
	private PlotType plotType;
	private boolean isUsable;
	private int taskTextureIndex;
	public static final String[] WORK_STATUS_TEXTURES = { null, "task1",
			"task2", "task3", "task4" };

	public Plot(PlotType plotType) {
		this.crop = null;
		this.irrigation = EnumSet.noneOf(Irrigation.class);
		this.plotType = plotType;
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
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public String getTextureName() {
		return plotType.toString().toLowerCase();
	}

	public String getTaskTextureName() {
		return WORK_STATUS_TEXTURES[taskTextureIndex];
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

	public void setIrrigation(EnumSet<Irrigation> irrigation) {
		if (isUsable) {
			this.irrigation = irrigation;
		}
		if (irrigation.isEmpty()
				&& !plotType.toString().toLowerCase().endsWith("unwatered")) {
			unwaterPlot();
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

	public int getTaskTextureIndex() {
		return taskTextureIndex;
	}

}