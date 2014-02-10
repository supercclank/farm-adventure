package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public class UpgradeSelectionState extends AbstractSelectionState {
	public UpgradeSelectionState(AbstractUpgrade selection) {
		this.selection = selection;
	}

	public UpgradeSelectionState update(Plot plot) {
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	public UpgradeSelectionState update(AbstractSpell spell) {
		selection.update(spell);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState((AbstractUpgrade) upgrade);
	}

	public UpgradeSelectionState update(AbstractTool tool) {
		selection.update(tool);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	public UpgradeSelectionState update(AbstractWorker worker) {
		selection.update(worker);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	@Override
	public UpgradeSelectionState update(AbstractCrop crop) {
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}
}
