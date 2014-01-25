package com.aa_software.farm_adventure.model.plot;

import com.aa_software.farm_adventure.model.Irrigation;

public class GrassPlot extends AbstractPlot {
	public static final String TEXTURE_NAME = "grass_plot";
	
	public GrassPlot() {
		super();
	}
	
	public GrassPlot(Irrigation irrigation) {
		super(irrigation);
	}
}
