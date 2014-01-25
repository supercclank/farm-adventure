package com.aa_software.farm_adventure.model.state;

import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.AbstractPlot;

public interface ISelectionState {
	ISelectionState update(AbstractPlot plot);
	ISelectionState update(AbstractSpell spell);
	ISelectionState update(AbstractUpgrade upgrade);
	ISelectionState update(AbstractTool tool);
	ISelectionState update(AbstractWorker worker);
}
