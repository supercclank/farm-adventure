package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class ToolSelectionState extends AbstractSelectionState {

	public ToolSelectionState(AbstractTool selection) {
		this.selection = selection;
	}

	@Override
	public ToolSelectionState update(AbstractCrop crop) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	@Override
	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState(spell);
	}

	@Override
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState(tool);
	}

	@Override
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState(upgrade);
	}

	@Override
	public ToolSelectionState update(AbstractWorker worker) {
		return new ToolSelectionState((AbstractTool) selection);
	}

	@Override
	public ToolSelectionState update(Plot plot, AbstractWorker worker) {
		selection.update(plot, worker);
		worker.setBusy(true);
		return new ToolSelectionState((AbstractTool) selection);
	}
}
