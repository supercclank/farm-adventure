package com.aa_software.farm_adventure.model.selectable.plot;

import com.aa_software.farm_adventure.model.selectable.ISelectable;
import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;

public class Plot implements ISelectable {
	private AbstractCrop crop;
	private Irrigation irrigation;
	private PlotType plotType;
	private boolean isUsable;

	public Plot(PlotType plotType) {
		this.crop = null;
		this.irrigation = null;
		this.plotType = plotType;
		this.isUsable = true;
	}

	public Plot(PlotType plotType, Irrigation irrigation) {
		this.crop = null;
		this.irrigation = irrigation;
		this.plotType = plotType;
		this.isUsable = true;
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public Irrigation getIrrigation() {
		return irrigation;
	}

	public PlotType getPlotType() {
		return plotType;
	}

	@Override
	public String getTextureName() {
		/*
		 * if(irrigation != null) { return plotType.toString().toLowerCase() +
		 * IRRIGATION_TEXTURE_NAME; }
		 */
		return plotType.toString().toLowerCase();
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setCrop(AbstractCrop crop) {
		if (plotType == PlotType.PLOWEDWATERED && isUsable) {
			this.crop = crop;
		}
	}

	public void setIrrigation(Irrigation irrigation) {
		this.irrigation = irrigation;
	}

	public void setPlotType(PlotType plotType) {
		if (isUsable) {
			this.plotType = plotType;
		}
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}

}
