package com.aa_software.farm_adventure.model.plot;

import com.aa_software.farm_adventure.model.Irrigation;

public class PlowedPlot extends AbstractPlot {
	public static final String TEXTURE_NAME = "plowed_plot";
	
	public PlowedPlot() {
		super();
	}
	
	public PlowedPlot(Irrigation irrigation) {
		super(irrigation);
	}
}
