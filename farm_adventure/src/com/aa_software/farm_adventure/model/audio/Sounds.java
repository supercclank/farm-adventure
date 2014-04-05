package com.aa_software.farm_adventure.model.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * A Singleton class. Contains all the functions neccessary for a method to access sound effects and music. Plays the sounds and music through libGDX.
 * @author AA Software
 *
 */
public class Sounds {
	protected Sound click;
	protected Sound money;
	protected Music mainMusic;
	protected float soundVolume;
	protected float masterVolume;
	
	private static Sounds Instance;

	/**
	 * Creates a Sounds instance if it doesn't exist, or uses an already existing one.
	 * @return a Singleton instance of the Sounds class
	 */
	public static Sounds getInstance() {
		if (Instance == null) {
			Instance = new Sounds();
		}
		return Instance;
	}	
	
	/**
	 * Constructs a Sounds object with the appropriate sounds and music.
	 */
	private Sounds() {
		click = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.wav"));
		money = Gdx.audio.newSound(Gdx.files.internal("sounds/money.mp3"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
		soundVolume = 1;
		masterVolume = 1;
	}
	
	/**
	 * Plays the click sound using libGDX.
	 */
	public void playClick() {
		click.setVolume(click.play(), soundVolume);
	}
	
	/**
	 * Plays the money sound using libGDX.
	 */
	public void playMoney() {
		money.setVolume(money.play(), soundVolume);
	}
	
	/**
	 * Plays the main music using libGDX.
	 */
	public void playMusic() {
		mainMusic.setLooping(true);
		mainMusic.play();
	}
	
	/**
	 * Pauses the music.
	 */
	public void pauseMusic() {
		mainMusic.pause();
	}
	
	/**
	 * Sets the volume of the music.
	 * @param vol a float in the range [0,1]. 0 is the lowest volume and 1 is the highest.
	 */
	public void setMusicVolume(float vol) {
		mainMusic.setVolume(vol);
	}
	
	/**
	 * Sets the volume of the sounds.
	 * @param vol a float in the range [0,1]. 0 is the lowest volume and 1 is the highest.
	 */
	public void setSoundVolume(float vol) {
		soundVolume = vol;
	}
	
	/**
	 * Sets the volume of the master volume
	 * @param vol a float in the range [0,1]. 0 is the lowest volume and 1 is the highest.
	 */
	public void setMasterVolume(float vol) {
		masterVolume = vol;
	}
	
	public float getMasterVolume() {
		return masterVolume;
	}
	
	public float getSoundVolume() {
		return soundVolume;
	}
	
	public float getMusicVolume() {
		return mainMusic.getVolume();
	}
}