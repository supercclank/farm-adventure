package com.aa_software.farm_adventure.model;

public class Preferences {
	private int volumeAdjustment;
	private int brightnessAdjustment;
	private int difficultyAdjustment;
	
	public int getVolumeAdjustment() {
		return volumeAdjustment;
	}
	public void setVolumeAdjustment(int volumeAdjustment) {
		this.volumeAdjustment = volumeAdjustment;
	}
	public int getDifficultyAdjustment() {
		return difficultyAdjustment;
	}
	public void setDifficultyAdjustment(int difficultyAdjustment) {
		this.difficultyAdjustment = difficultyAdjustment;
	}
	public int getBrightnessAdjustment() {
		return brightnessAdjustment;
	}
	public void setBrightnessAdjustment(int brightnessAdjustment) {
		this.brightnessAdjustment = brightnessAdjustment;
	}
}
