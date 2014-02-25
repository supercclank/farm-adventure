package com.aa_software.farm_adventure.model.plot;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;

public class Plot {
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

	public String getTextureName() {
		return plotType.toString().toLowerCase();
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setCrop(final AbstractCrop crop) {
		if (plotType == PlotType.PLOWEDWATERED && isUsable) {
			this.crop = crop;
		}
	}

	public void setIrrigation(Irrigation irrigation) {
		if(isUsable) {
			this.irrigation = irrigation;
		}
	}

	public void setPlotType(PlotType plotType) {
		if(isUsable) {
			this.plotType = plotType;
		}
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}
	
	public boolean isIrrigated() {
		if(irrigation == null) {
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
		if(crop == null) {
			return false;
		}
		return true;
	}

}
