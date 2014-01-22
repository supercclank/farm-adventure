package com.aa_software.farm_adventure.model.game_object;

public class Plant extends Item {
	private int growthTime;
	private PlantType plantType;
	private int output;

	public Plant(PlantType plantType) {
		this.plantType = plantType;
		//TODO arbitrary times, etc.
		switch(plantType) {
			case CARROT: 
				growthTime = 20;
				output = 250;
				cost = 50;
				break;
			case RICE:
				growthTime = 35;
				output = 1000;
				cost = 100;
				break;
			case BEET:
				growthTime = 10;
				output = 550;
				cost = 250;
				break;
			case BANANA:
				growthTime = 60;
				output = 500;
				cost = 20;
				break;
			case POTATO:
				growthTime = 40;
				output = 1000;
				cost = 50;
				break;
			case CORN:
				growthTime = 10;
				output = 300;
				cost = 30;
				break;
			default:
				/* obviously we have forgotten a case, so we want obvious
				 * results in game so we can catch it.
				 */
				growthTime = 0;
				output = 0;
				cost = 0;
				break;
		}
	}

	public int getGrowthTime() {
		return growthTime;
	}

	public void setGrowthTime(int growthTime) {
		this.growthTime = growthTime;
	}

	public PlantType getPlantType() {
		return plantType;
	}

	public void setPlantType(PlantType plantType) {
		this.plantType = plantType;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}
}
