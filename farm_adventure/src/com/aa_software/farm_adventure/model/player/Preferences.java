package com.aa_software.farm_adventure.model.player;

/**
 * 
 * Holds the player's preferences from the option menu.
 * 
 * @author Bebop
 * 
 */
public class Preferences {
	private static boolean isMuted = false;
	private float soundVolume;
	private float masterVolume;
	private float gameVolume;
	
	public static boolean isMuted() {
		return isMuted;
	}

	public static void setMuted(boolean muted) {
		isMuted = muted;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	public float getMasterVolume() {
		return masterVolume;
	}

	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	public float getGameVolume() {
		return gameVolume;
	}

	public void setGameVolume(float gameVolume) {
		this.gameVolume = gameVolume;
	}
}
