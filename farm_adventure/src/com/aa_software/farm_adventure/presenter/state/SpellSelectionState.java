package com.aa_software.farm_adventure.presenter.state;

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
	public SpellSelectionState update(Plot plot) {
		selection.update(plot);
		return new SpellSelectionState((AbstractSpell)selection);
	}
	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState((AbstractSpell)spell);
	}
	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState((AbstractUpgrade)upgrade);
	}
	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState((AbstractTool)tool);
	}
	public SpellSelectionState update(AbstractWorker worker) {
		selection.update(worker);
		return new SpellSelectionState((AbstractSpell)selection);
	}
	public SpellSelectionState update(AbstractCrop crop) {
		selection.update(crop);
		return new SpellSelectionState((AbstractSpell)selection);
	}
}
