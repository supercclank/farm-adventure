package com.aa_software.farm_adventure.presenter.state;

import com.aa_software.farm_adventure.model.selectable.item.crop.AbstractCrop;
import com.aa_software.farm_adventure.model.selectable.item.spell.AbstractSpell;
import com.aa_software.farm_adventure.model.selectable.item.tool.AbstractTool;
import com.aa_software.farm_adventure.model.selectable.item.upgrade.AbstractUpgrade;
import com.aa_software.farm_adventure.model.selectable.item.worker.AbstractWorker;
import com.aa_software.farm_adventure.model.selectable.plot.Plot;

public class DefaultSelectionState extends AbstractSelectionState {
	public DefaultSelectionState() {
	}

	public DefaultSelectionState update() {
		return new DefaultSelectionState();
	}

	public DefaultSelectionState update(Plot plot) {
		return new DefaultSelectionState();
	}

	public SpellSelectionState update(AbstractSpell spell) {
		return new SpellSelectionState(spell);
	}

	public UpgradeSelectionState update(AbstractUpgrade upgrade) {
		return new UpgradeSelectionState(upgrade);
	}

	public ToolSelectionState update(AbstractTool tool) {
		return new ToolSelectionState(tool);
	}

	public DefaultSelectionState update(AbstractWorker worker) {
		return new DefaultSelectionState();
	}

	public DefaultSelectionState update(AbstractCrop crop) {
		return new DefaultSelectionState();
	}
}
