package com.aa_software.farm_adventure.model;

import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.AbstractPlot;
import com.aa_software.farm_adventure.model.state.DefaultSelectionState;
import com.aa_software.farm_adventure.model.state.ISelectionState;
import com.badlogic.gdx.ApplicationListener;

public class FarmAdventure implements ApplicationListener {
	private ISelectable selection;
	private ISelectionState state;
	@Override
	public void create() {		
		this.selection = null;
		this.state = new DefaultSelectionState();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {		
		/* on-click change selection */
		if(selection instanceof AbstractPlot) {
			state = state.update((AbstractPlot)selection);
		} else if(selection instanceof AbstractItem) {
			if(selection instanceof AbstractSpell) {
				state = state.update((AbstractSpell)selection);
			} else if (selection instanceof AbstractTool) {
				state = state.update((AbstractTool)selection);
			} else if (selection instanceof AbstractWorker) {
				state = state.update((AbstractWorker)selection);
			} else if (selection instanceof AbstractUpgrade) {
				state = state.update((AbstractUpgrade)selection);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
}
