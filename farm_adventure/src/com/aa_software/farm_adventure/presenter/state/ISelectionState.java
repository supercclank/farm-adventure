package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public interface ISelectionState {
	ISelectionState update(Plot plot);

	ISelectionState update(AbstractSpell spell);

	ISelectionState update(AbstractUpgrade upgrade);

	ISelectionState update(AbstractTool tool);

	ISelectionState update(AbstractWorker worker);

	ISelectionState update(AbstractCrop crop);
}