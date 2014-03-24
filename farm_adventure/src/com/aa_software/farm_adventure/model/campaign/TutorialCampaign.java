package com.aa_software.farm_adventure.model.campaign;

import com.aa_software.farm_adventure.model.farm.DesertFarm;
import com.aa_software.farm_adventure.model.farm.RainforestFarm;
import com.aa_software.farm_adventure.model.farm.SnowFarm;
import com.aa_software.farm_adventure.model.farm.TutorialFarm;

public class TutorialCampaign extends AbstractCampaign {

	public TutorialCampaign() {
		super();
		farms[0] = new TutorialFarm();
		farms[1] = new RainforestFarm();
		farms[2] = new DesertFarm();
		farms[3] = new SnowFarm();
	}

}
