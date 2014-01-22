package com.aa_software.farm_adventure.model.game_object;

public class Plot {
	
	private PlotType plotType;
	private Plant plant;
	private Irrigation irrigation;
	
	public Plot() {
		plotType = PlotType.UNPLOWED;
		plant = null;
		irrigation = null;
	}
	
	public Plot(PlotType plotType) {
		this.plotType = plotType;
		plant = null;
		irrigation = null;
	}
	
	public Plot(Irrigation irrigation) {
		plotType = PlotType.WATERED;
		plant = null;
		this.irrigation = irrigation;
	}
	
	public PlotType getPlotType() {
		return plotType;
	}
	
	public void setPlotType(PlotType plotType) {
		this.plotType = plotType;
	}
	
	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public Irrigation getIrrigation() {
		return irrigation;
	}
	
	public void setIrrigation(Irrigation irrigation) {
		this.irrigation = irrigation;
	}
	
}
