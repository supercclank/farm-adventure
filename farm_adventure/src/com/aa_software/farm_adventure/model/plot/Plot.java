package com.aa_software.farm_adventure.model.plot;

import java.util.Arrays;
import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.presenter.TextureHelper;

public class Plot {
	private AbstractCrop crop;
	private EnumSet<Irrigation> irrigation;
	private PlotType plotType;
	private boolean isUsable;

	public Plot(PlotType plotType) {
		this.crop = null;
		this.irrigation = EnumSet.noneOf(Irrigation.class);
		this.plotType = plotType;
		this.isUsable = true;
	}

	public Plot(PlotType plotType, EnumSet<Irrigation> irrigation) {
		this.crop = null;
		this.irrigation = irrigation;
		this.plotType = plotType;
		this.isUsable = true;
	}

	public AbstractCrop getCrop() {
		return crop;
	}

	public EnumSet<Irrigation> getIrrigation() {
		return irrigation;
	}

	public void addIrrigation(Irrigation irrigation) {
		if(!this.irrigation.contains(irrigation) && isUsable) {
			this.irrigation.add(irrigation);
		}
	}
	
	public PlotType getPlotType() {
		return plotType;
	}

	public String getTextureName() {
		return plotType.toString().toLowerCase();
	}
	
	/**
	 * Translates the plots irrigation enumeration set into its corresponding
	 * texture name.
	 * 
	 * @return		the texture name corresponding to this plots irrigation.
	 */
	public String getIrrigationTextureName() {
		String textureName = TextureHelper.getIrrigationTextureName(irrigation);
		return textureName;
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setCrop(final AbstractCrop crop) {
		if (plotType == PlotType.PLOWEDWATERED && isUsable) {
			this.crop = crop;
		}
	}

	public void setIrrigation(EnumSet<Irrigation> irrigation) {
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
		if(irrigation.isEmpty()) {
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
