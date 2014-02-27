package com.aa_software.farm_adventure.model.plot;

import java.util.Arrays;
import java.util.EnumSet;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;

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
	
	public String getIrrigationTextureName() {
		String textureName = null;
		if(irrigation.containsAll(Arrays.asList(Irrigation.values()))) {
			textureName = "TOP_LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.LEFT))) {
			textureName = "TOP_LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM, Irrigation.RIGHT))) {
			textureName = "TOP_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT, Irrigation.LEFT))) {
			textureName = "TOP_LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT, Irrigation.BOTTOM))) {
			textureName = "LEFT_RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.BOTTOM))) {
			textureName = "RIGHT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.BOTTOM, 
				Irrigation.LEFT))) {
			textureName = "LEFT_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.RIGHT, 
				Irrigation.LEFT))) {
			textureName = "LEFT_RIGHT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.LEFT))) {
			textureName = "TOP_LEFT";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.BOTTOM))) {
			textureName = "TOP_BOTTOM";
		} else if(irrigation.containsAll(Arrays.asList(Irrigation.TOP, 
				Irrigation.RIGHT))) {
			textureName = "TOP_RIGHT";
		} else if(irrigation.contains(Irrigation.TOP)) {
			textureName = "TOP";
		} else if(irrigation.contains(Irrigation.BOTTOM)) {
			textureName = "BOTTOM";
		} else if(irrigation.contains(Irrigation.LEFT)) {
			textureName = "LEFT";
		} else if(irrigation.contains(Irrigation.RIGHT)) {
			textureName = "RIGHT";
		}
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
