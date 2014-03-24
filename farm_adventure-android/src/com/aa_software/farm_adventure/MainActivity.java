package com.aa_software.farm_adventure;

import android.os.Bundle;

import com.aa_software.farm_adventure.presenter.FarmAdventure;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		initialize(FarmAdventure.getInstance(), cfg);
	}
}