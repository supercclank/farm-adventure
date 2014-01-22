package com.aa_software.farm_adventure.model.game_object;

public class Campaign {
	public static final int NUMBER_OF_FARMS = 4;
	private Farm[] farms;
	private int progress;
	
	public Campaign() {
		farms = new Farm[NUMBER_OF_FARMS];
		farms[1] = new TutorialFarm();
		farms[2] = new RainforestFarm();
		farms[3] = new DesertFarm();
		farms[4] = new SnowFarm();
		progress = 0;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Farm[] getFarms() {
		return farms;
	}

	public void setFarms(Farm[] farms) {
		this.farms = farms;
	}
}
