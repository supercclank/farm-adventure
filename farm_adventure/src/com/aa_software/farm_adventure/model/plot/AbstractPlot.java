package com.aa_software.farm_adventure.model.plot;

import com.aa_software.farm_adventure.model.Irrigation;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;

public abstract class AbstractPlot {
	
	private AbstractCrop plant;
	private Irrigation irrigation;
	
	public AbstractPlot() {
		plant = null;
		irrigation = null;
	}
	
	public AbstractPlot(Irrigation irrigation) {
		plant = null;
		this.irrigation = irrigation;
	}
	
	public AbstractCrop getPlant() {
		return plant;
	}
	
	public void setPlant(AbstractCrop plant) {
		this.plant = plant;
	}
	
	public Irrigation getIrrigation() {
		return irrigation;
	}
	
	public void setIrrigation(Irrigation irrigation) {
		this.irrigation = irrigation;
	}
	
}
