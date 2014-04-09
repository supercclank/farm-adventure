package com.aa_software.farm_adventure.presenter.selection_state;

import com.aa_software.farm_adventure.model.Inventory;
import com.aa_software.farm_adventure.model.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.plot.Plot;

public class SpellSelectionState extends AbstractSelectionState {
	public SpellSelectionState(AbstractSpell selection) {
		this.selection = selection;
	}

	@Override
	public SpellSelectionState update(AbstractCrop crop) {
		selection.update(crop);
		return new SpellSelectionState((AbstractSpell) selection);
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
	public SpellSelectionState update(AbstractWorker worker) {
		selection.update(worker);
		return new SpellSelectionState((AbstractSpell) selection);
	}

	@Override
	public SpellSelectionState update(Plot plot, Inventory inventory) {
		selection.update(plot, inventory);
		return new SpellSelectionState((AbstractSpell) selection);
	}

}
