package com.aa_software.farm_adventure.model.state;

import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class UpgradeSelectionState extends AbstractSelectionState  {
	public UpgradeSelectionState(AbstractUpgrade selection) {
		this.selection = selection;
	}
	public UpgradeSelectionState update(Plot plot) {
		return new UpgradeSelectionState((AbstractUpgrade)selection);
	}
	public UpgradeSelectionState update(AbstractSpell spell) {
		selection.update(spell);
		return new UpgradeSelectionState((AbstractUpgrade)selection);
	}
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState((AbstractUpgrade)upgrade);
	}
	public UpgradeSelectionState update(AbstractTool tool) {
		selection.update(tool);
		return new UpgradeSelectionState((AbstractUpgrade)selection);
	}
	public UpgradeSelectionState update(AbstractWorker worker) {
		selection.update(worker);
		return new UpgradeSelectionState((AbstractUpgrade)selection);
	}
}
