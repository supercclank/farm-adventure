package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class UpgradeSelectionState extends AbstractSelectionState {
	public UpgradeSelectionState(AbstractUpgrade selection) {
		this.selection = selection;
	}

	@Override
	public UpgradeSelectionState update(AbstractCrop crop) {
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	@Override
	public UpgradeSelectionState update(AbstractSpell spell) {
		selection.update(spell);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	@Override
	public UpgradeSelectionState update(AbstractTool tool) {
		selection.update(tool);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	@Override
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState(upgrade);
	}

	@Override
	public UpgradeSelectionState update(AbstractWorker worker) {
		selection.update(worker);
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}

	@Override
	public UpgradeSelectionState update(Plot plot) {
		return new UpgradeSelectionState((AbstractUpgrade) selection);
	}
}
