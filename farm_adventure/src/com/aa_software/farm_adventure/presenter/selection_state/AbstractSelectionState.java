package com.aa_software.farm_adventure.presenter.selection_state;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.AbstractItem;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.plot.Plot;

public abstract class AbstractSelectionState {
	protected AbstractItem selection;
	
	// public abstract AbstractSelectionState update(AbstractCrop crop);

	// AbstractSelectionState update(AbstractSpell spell);

	public abstract AbstractSelectionState update(AbstractTool tool);

	// AbstractSelectionState update(AbstractUpgrade upgrade);

	// public abstract AbstractSelectionState update(DefaultWorker worker);

	public abstract AbstractSelectionState update(Plot plot, Inventory inventory);
}
