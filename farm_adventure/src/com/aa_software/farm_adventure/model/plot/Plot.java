package com.aa_software.farm_adventure.model.plot;

import com.aa_software.farm_adventure.model.ISelectable;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;

public class Plot implements ISelectable {
	public static final String IRRIGATION_TEXTURE_NAME = "_IRRIGATED";
	private AbstractCrop crop;
	private Irrigation irrigation;
	private PlotType plotType;
	
	public Plot(PlotType plotType) {
		this.crop = null;
		this.irrigation = null;
		this.plotType = plotType;
	}
	
	public Plot(PlotType plotType, Irrigation irrigation) {
		this.crop = null;
		this.irrigation = irrigation;
		this.plotType = plotType;
	}
	
	public Irrigation getIrrigation() {
		return irrigation;
	}
	
	public void setIrrigation(Irrigation irrigation) {
		this.irrigation = irrigation;
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public void setPlotType(PlotType plotType) {
		this.plotType = plotType;
	}

	public String getTextureName() {
		if(irrigation != null) {
			return plotType.toString() + IRRIGATION_TEXTURE_NAME;
		}
		return plotType.toString();
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public void setCrop(AbstractCrop crop) {
		this.crop = crop;
	}
	
}
