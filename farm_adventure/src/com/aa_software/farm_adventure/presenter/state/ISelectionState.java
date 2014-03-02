package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public interface ISelectionState {
	ISelectionState update(AbstractCrop crop);

	ISelectionState update(AbstractSpell spell);

	ISelectionState update(AbstractTool tool);

	ISelectionState update(AbstractUpgrade upgrade);

	ISelectionState update(AbstractWorker worker);

	ISelectionState update(Plot plot);
}
