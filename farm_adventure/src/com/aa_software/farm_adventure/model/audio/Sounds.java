package com.aa_software.farm_adventure.model.audio;

import com.aa_software.farm_adventure.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static Sound click;
	public static Music mainMusic;
	public static float soundVolume;
	
	private static Sounds Instance;

	public static Sounds getInstance() {
		if (Instance == null) {
			Instance = new Sounds();
		}
		return Instance;
	}	
	
	private Sounds()
	{
		click = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.wav"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/rain.mp3"));
		soundVolume = 1;
	}
	
	public void playSound()
	{
		click.setVolume(click.play(), soundVolume);
	}
	
	public void playMusic()
	{
		mainMusic.setLooping(true);
		mainMusic.play();
	}
	
	public void pauseMusic()
	{
		mainMusic.pause();
	}
	
	public void setMusicVolume(float vol)
	{
		mainMusic.setVolume(vol);
	}
	
	public void setSoundVolume(float vol)
	{
		soundVolume = vol;
	}
}