package com.aa_software.farm_adventure.model.item.spell;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractSpell extends AbstractItem {
	protected float spellLength;

	public float getSpellLength() {
		return spellLength;
	}

	public String getTextureName() {
		return "arbitrary";
		// TODO: change
	}

	public void setSpellLength(float spellLength) {
		this.spellLength = spellLength;
	}

	@Override
	public void update(AbstractItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Plot plot, Inventory inventory) {
		// TODO Auto-generated method stub
		
	}	
	
}
